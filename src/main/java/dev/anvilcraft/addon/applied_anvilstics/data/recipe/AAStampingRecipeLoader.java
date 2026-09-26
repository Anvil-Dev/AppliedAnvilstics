package dev.anvilcraft.addon.applied_anvilstics.data.recipe;

import appeng.core.definitions.AEItems;
import appeng.datagen.providers.tags.ConventionTags;
import dev.dubhe.anvilcraft.recipe.anvil.wrap.StampingRecipe;
import dev.anvilcraft.addon.applied_anvilstics.AppliedAnvilstics;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

public final class AAStampingRecipeLoader {
    private AAStampingRecipeLoader() {
    }

    public static void setupRecipes(RecipeOutput provider) {
        stampingWithKeptPress(provider, "silicon_press", Items.IRON_BLOCK, AEItems.SILICON_PRESS.asItem(), AEItems.SILICON_PRESS.asItem());
        stampingWithKeptPress(provider, "silicon_print", ConventionTags.SILICON, AEItems.SILICON_PRINT.asItem(), AEItems.SILICON_PRESS.asItem());
        stampingWithKeptPress(provider, "calculation_processor_print",
            AEItems.CERTUS_QUARTZ_CRYSTAL.asItem(), AEItems.CALCULATION_PROCESSOR_PRINT.asItem(),
            AEItems.CALCULATION_PROCESSOR_PRESS.asItem());
        stampingWithKeptPress(provider, "calculation_processor_press",
            Items.IRON_BLOCK, AEItems.CALCULATION_PROCESSOR_PRESS.asItem(),
            AEItems.CALCULATION_PROCESSOR_PRESS.asItem());
        stampingWithKeptPress(provider, "engineering_processor_print",
            ConventionTags.DIAMOND, AEItems.ENGINEERING_PROCESSOR_PRINT.asItem(),
            AEItems.ENGINEERING_PROCESSOR_PRESS.asItem());
        stampingWithKeptPress(provider, "engineering_processor_press",
            Items.IRON_BLOCK, AEItems.ENGINEERING_PROCESSOR_PRESS.asItem(),
            AEItems.ENGINEERING_PROCESSOR_PRESS.asItem());
        stampingWithKeptPress(provider, "logic_processor_print",
            ConventionTags.GOLD_INGOT, AEItems.LOGIC_PROCESSOR_PRINT.asItem(),
            AEItems.LOGIC_PROCESSOR_PRESS.asItem());
        stampingWithKeptPress(provider, "logic_processor_press",
            Items.IRON_BLOCK, AEItems.LOGIC_PROCESSOR_PRESS.asItem(),
            AEItems.LOGIC_PROCESSOR_PRESS.asItem());

        stampingConsumingPress(provider, "calculation_processor",
            Items.REDSTONE, AEItems.CALCULATION_PROCESSOR.asItem(),
            AEItems.CALCULATION_PROCESSOR_PRINT.asItem(), AEItems.SILICON_PRINT.asItem());
        stampingConsumingPress(provider, "engineering_processor",
            Items.REDSTONE, AEItems.ENGINEERING_PROCESSOR.asItem(),
            AEItems.ENGINEERING_PROCESSOR_PRINT.asItem(), AEItems.SILICON_PRINT.asItem());
        stampingConsumingPress(provider, "logic_processor",
            Items.REDSTONE, AEItems.LOGIC_PROCESSOR.asItem(),
            AEItems.LOGIC_PROCESSOR_PRINT.asItem(), AEItems.SILICON_PRINT.asItem());
    }

    private static void stampingWithKeptPress(
        RecipeOutput provider,
        String id,
        TagKey<Item> middle,
        Item result,
        Item press
    ) {
        StampingRecipe.builder()
            .requires(middle)
            .requires(press)
            .result(press)
            .result(result)
            .save(provider, AppliedAnvilstics.location("ae2/inscriber/" + id));
    }

    private static void stampingWithKeptPress(
        RecipeOutput provider,
        String id,
        Item middle,
        Item result,
        Item press
    ) {
        StampingRecipe.builder()
            .requires(middle)
            .requires(press)
            .result(press)
            .result(result)
            .save(provider, AppliedAnvilstics.location("ae2/inscriber/" + id));
    }

    private static void stampingConsumingPress(
        RecipeOutput provider,
        String id,
        Item middle,
        Item result,
        Item top,
        Item bottom
    ) {
        StampingRecipe.builder()
            .requires(middle)
            .requires(top)
            .requires(bottom)
            .result(result)
            .save(provider, AppliedAnvilstics.location("ae2/inscriber/" + id));
    }
}