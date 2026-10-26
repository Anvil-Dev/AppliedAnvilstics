package dev.anvilcraft.addon.applied_anvilstics.mixins;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.MultimapBuilder;
import dev.anvilcraft.addon.applied_anvilstics.api.AARecipeManagerExtension;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Mixin(RecipeManager.class)
public abstract class AARecipeManagerMixin implements AARecipeManagerExtension {
    @Shadow
    private Map<ResourceLocation, RecipeHolder<?>> byName;

    @Shadow
    private Multimap<RecipeType<?>, RecipeHolder<?>> byType;

    @Override
    public void appliedanvilstics$addRecipes(List<RecipeHolder<?>> recipes) {
        ImmutableMap.Builder<ResourceLocation, RecipeHolder<?>> byNameBuilder = ImmutableMap.builder();
        Multimap<RecipeType<?>, RecipeHolder<?>> byTypeBuilder =
            MultimapBuilder.hashKeys().<RecipeHolder<?>>treeSetValues(
                Comparator.comparing(RecipeHolder::id)
            ).build();
        Set<ResourceLocation> keys = new HashSet<>();

        this.byName.forEach((key, value) -> {
            if (key == null || value == null || !keys.add(key)) return;
            byNameBuilder.put(key, value);
        });
        this.byType.forEach((key, value) -> {
            if (key == null || value == null) return;
            byTypeBuilder.put(key, value);
        });

        recipes.forEach(recipe -> {
            if (!keys.add(recipe.id())) return;
            byNameBuilder.put(recipe.id(), recipe);
            byTypeBuilder.put(recipe.value().getType(), recipe);
        });

        this.byName = byNameBuilder.build();
        this.byType = ImmutableMultimap.copyOf(byTypeBuilder);
    }
}
