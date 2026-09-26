package dev.anvilcraft.addon.applied_anvilstics.data.recipe;

import appeng.core.definitions.AEBlocks;
import appeng.core.definitions.AEItems;
import dev.dubhe.anvilcraft.recipe.anvil.wrap.SolidLiquidRecipe;
import dev.anvilcraft.addon.applied_anvilstics.AppliedAnvilstics;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;

public final class AASolidLiquidRecipeLoader {
    private AASolidLiquidRecipeLoader() {
    }

    public static void setupRecipes(RecipeOutput provider) {
        // 回收赛特斯石英粉
        transform(provider, "certus_quartz_crystals", AEItems.CERTUS_QUARTZ_CRYSTAL.asItem(), 2,
            AEItems.CERTUS_QUARTZ_CRYSTAL_CHARGED.asItem(), AEItems.CERTUS_QUARTZ_DUST.asItem());
        // 福鲁伊克斯粉回收
        transform(provider, "fluix_crystal", AEItems.FLUIX_CRYSTAL.asItem(), 1,
            AEItems.CERTUS_QUARTZ_CRYSTAL_CHARGED.asItem(), AEItems.FLUIX_DUST.asItem());
        // 直接合成福鲁伊克斯水晶
        transform(provider, "fluix_crystals", AEItems.FLUIX_CRYSTAL.asItem(), 2,
            AEItems.CERTUS_QUARTZ_CRYSTAL_CHARGED.asItem(), Items.REDSTONE, Items.QUARTZ);

        // 修复赛特斯石英母岩
        transform(provider, "damaged_budding_quartz", AEBlocks.DAMAGED_BUDDING_QUARTZ.asItem(), 1,
            AEItems.CERTUS_QUARTZ_CRYSTAL_CHARGED.asItem(), AEBlocks.QUARTZ_BLOCK.asItem());
        transform(provider, "chipped_budding_quartz", AEBlocks.CHIPPED_BUDDING_QUARTZ.asItem(), 1,
            AEItems.CERTUS_QUARTZ_CRYSTAL_CHARGED.asItem(), AEBlocks.DAMAGED_BUDDING_QUARTZ.asItem());
        transform(provider, "flawed_budding_quartz", AEBlocks.FLAWED_BUDDING_QUARTZ.asItem(), 1,
            AEItems.CERTUS_QUARTZ_CRYSTAL_CHARGED.asItem(), AEBlocks.CHIPPED_BUDDING_QUARTZ.asItem());
    }

    private static void transform(RecipeOutput provider, String id, Item result, int count, Item... inputs) {
        SolidLiquidRecipe.Builder builder = SolidLiquidRecipe.builder()
            .cauldron(Blocks.WATER_CAULDRON);
        for (Item input : inputs) {
            builder.requires(input);
        }
        builder.result(result, count)
            .save(provider, AppliedAnvilstics.location("ae2/transform/" + id));
    }
}