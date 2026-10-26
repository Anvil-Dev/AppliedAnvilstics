package dev.anvilcraft.addon.applied_anvilstics.compat.ae2;

import appeng.recipes.handlers.ChargerRecipe;
import appeng.recipes.handlers.InscriberRecipe;
import appeng.recipes.transform.TransformRecipe;
import dev.anvilcraft.addon.applied_anvilstics.AppliedAnvilstics;
import dev.anvilcraft.addon.applied_anvilstics.api.AARecipeManagerExtension;
import dev.anvilcraft.lib.v2.recipe.InWorldRecipe;
import dev.anvilcraft.lib.v2.recipe.event.InWorldRecipeManagerEvent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * 在最终配方表形成后，把 AE2 及其附属复用 AE2 配方类型的配方转换为铁砧工艺配方。
 */
@EventBusSubscriber(modid = AppliedAnvilstics.MODID)
public final class AE2RecipeRuntimeAdapter {
    private AE2RecipeRuntimeAdapter() {
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onRecipeManagerInit(InWorldRecipeManagerEvent.Init event) {
        RecipeManager manager = event.getRecipeManager();
        List<RecipeHolder<?>> sources = new ArrayList<>(manager.getRecipes());
        sources.sort(Comparator.comparing(RecipeHolder::id));

        Set<ResourceLocation> generatedIds = new HashSet<>();
        List<RecipeHolder<InWorldRecipe>> inWorldRecipes = new ArrayList<>();
        List<RecipeHolder<?>> otherRecipes = new ArrayList<>();

        for (RecipeHolder<?> holder : sources) {
            ResourceLocation sourceId = holder.id();
            Object recipe = holder.value();

            try {
                if (recipe instanceof InscriberRecipe inscriber) {
                    Optional<RecipeHolder<InWorldRecipe>> generated = AE2InscriberAdapter.adapt(sourceId, inscriber);
                    generated.ifPresent(generatedHolder -> addRecipe(
                        generatedHolder,
                        generatedIds,
                        inWorldRecipes,
                        otherRecipes
                    ));
                    continue;
                }

                if (recipe instanceof TransformRecipe transform) {
                    Optional<RecipeHolder<InWorldRecipe>> generated =
                        AE2TransformAdapter.adapt(sourceId, transform);
                    generated.ifPresent(generatedHolder -> addRecipe(
                        generatedHolder,
                        generatedIds,
                        inWorldRecipes,
                        otherRecipes
                    ));
                    continue;
                }

                if (recipe instanceof ChargerRecipe charger) {
                    Optional<RecipeHolder<?>> generated = AE2ChargerAdapter.adapt(sourceId, charger);
                    generated.ifPresent(generatedHolder -> addRecipe(
                        generatedHolder,
                        generatedIds,
                        inWorldRecipes,
                        otherRecipes
                    ));
                }
            } catch (RuntimeException exception) {
                // 第三方配方可能包含异常数据，单个配方失败不应中断整次配方重载。
                AppliedAnvilstics.LOGGER.warn(
                    "Failed to adapt AE2 recipe {} ({})",
                    sourceId,
                    recipe.getClass().getName(),
                    exception
                );
            }
        }

        if (!inWorldRecipes.isEmpty()) {
            manager.anvillib$addRecipes(inWorldRecipes);
        }
        if (!otherRecipes.isEmpty() && manager instanceof AARecipeManagerExtension extension) {
            extension.appliedanvilstics$addRecipes(otherRecipes);
        }

        AppliedAnvilstics.LOGGER.debug(
            "Generated {} AnvilCraft recipes from AE2 recipe types",
            inWorldRecipes.size() + otherRecipes.size()
        );
    }

    static ResourceLocation generatedId(String kind, ResourceLocation sourceId) {
        return AppliedAnvilstics.location(
            "generated/ae2/%s/%s/%s".formatted(
                kind,
                sourceId.getNamespace(),
                sourceId.getPath()
            )
        );
    }

    private static void addRecipe(
        RecipeHolder<?> generated,
        Set<ResourceLocation> generatedIds,
        List<RecipeHolder<InWorldRecipe>> inWorldRecipes,
        List<RecipeHolder<?>> otherRecipes
    ) {
        if (!generatedIds.add(generated.id())) return;
        if (generated.value() instanceof InWorldRecipe) {
            @SuppressWarnings("unchecked")
            RecipeHolder<InWorldRecipe> inWorld = (RecipeHolder<InWorldRecipe>) generated;
            inWorldRecipes.add(inWorld);
        } else {
            otherRecipes.add(generated);
        }
    }
}
