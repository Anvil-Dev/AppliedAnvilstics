package dev.anvilcraft.addon.applied_anvilstics.compat.ae2;

import appeng.core.definitions.AEItems;
import appeng.recipes.transform.TransformCircumstance;
import appeng.recipes.transform.TransformRecipe;
import com.google.gson.JsonElement;
import dev.anvilcraft.lib.v2.recipe.InWorldRecipe;
import dev.anvilcraft.lib.v2.util.predicate.ItemIngredientPredicate;
import dev.dubhe.anvilcraft.recipe.anvil.wrap.SolidLiquidRecipe;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeHolder;

import java.util.Optional;

final class AE2TransformAdapter {
    private AE2TransformAdapter() {
    }

    static Optional<RecipeHolder<InWorldRecipe>> adapt(
        ResourceLocation sourceId,
        TransformRecipe recipe
    ) {
        TransformCircumstance circumstance = recipe.getCircumstance();
        if (!circumstance.isFluid()) return Optional.empty();

        ItemStack result = recipe.getResultItem().copy();
        if (result.isEmpty() || AEItems.QUANTUM_ENTANGLED_SINGULARITY.is(result)) {
            return Optional.empty();
        }

        JsonElement tagElement = circumstance.toJson().get("tag");
        if (tagElement == null || !tagElement.isJsonPrimitive()) return Optional.empty();

        ResourceLocation tagId;
        try {
            tagId = ResourceLocation.parse(tagElement.getAsString());
        } catch (RuntimeException exception) {
            return Optional.empty();
        }

        SolidLiquidRecipe.Builder builder = SolidLiquidRecipe.builder()
            .cauldron(TagKey.create(Registries.FLUID, tagId));
        for (Ingredient ingredient : recipe.getIngredients()) {
            Optional<ItemIngredientPredicate> predicate = AE2IngredientAdapter.toPredicate(ingredient);
            if (predicate.isEmpty()) return Optional.empty();
            builder.requires(predicate.get());
        }
        builder.result(result);

        return Optional.of(new RecipeHolder<>(
            AE2RecipeRuntimeAdapter.generatedId("transform", sourceId),
            builder.buildRecipe()
        ));
    }
}
