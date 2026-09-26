package dev.anvilcraft.addon.applied_anvilstics.data.recipe;

import appeng.core.definitions.AEBlocks;
import appeng.core.definitions.AEItems;
import appeng.datagen.providers.tags.ConventionTags;
import dev.dubhe.anvilcraft.recipe.anvil.wrap.ItemCrushRecipe;
import dev.anvilcraft.addon.applied_anvilstics.AppliedAnvilstics;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

public final class AAItemCrushRecipeLoader {
    private AAItemCrushRecipeLoader() {
    }

    public static void setupRecipes(RecipeOutput provider) {
        crush(provider, "fluix_dust", ConventionTags.FLUIX_CRYSTAL, AEItems.FLUIX_DUST.asItem());
        crush(provider, "certus_quartz_dust", ConventionTags.CERTUS_QUARTZ, AEItems.CERTUS_QUARTZ_DUST.asItem());
        crush(provider, "sky_stone_dust", AEBlocks.SKY_STONE_BLOCK.asItem(), AEItems.SKY_DUST.asItem());
        crush(provider, "ender_dust", Items.ENDER_PEARL, AEItems.ENDER_DUST.asItem());
    }

    private static void crush(RecipeOutput provider, String id, TagKey<Item> input, Item result) {
        ItemCrushRecipe.builder()
            .requires(input)
            .result(result)
            .save(provider, AppliedAnvilstics.location("ae2/inscriber/" + id));
    }

    private static void crush(RecipeOutput provider, String id, Item input, Item result) {
        ItemCrushRecipe.builder()
            .requires(input)
            .result(result)
            .save(provider, AppliedAnvilstics.location("ae2/inscriber/" + id));
    }
}