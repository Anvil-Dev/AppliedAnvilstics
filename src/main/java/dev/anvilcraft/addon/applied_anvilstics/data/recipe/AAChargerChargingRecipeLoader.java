package dev.anvilcraft.addon.applied_anvilstics.data.recipe;

import appeng.core.definitions.AEItems;
import dev.dubhe.anvilcraft.recipe.ChargerChargingRecipe;
import dev.anvilcraft.addon.applied_anvilstics.AppliedAnvilstics;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

public final class AAChargerChargingRecipeLoader {
    private static final int CHARGER_POWER_KW = -8;
    private static final int AE_PER_KW_TICK = 50;
    private static final int AE_CHARGER_ENERGY_PER_OPERATION = 1600;

    private AAChargerChargingRecipeLoader() {
    }

    public static void setupRecipes(RecipeOutput provider) {
        charger(
            provider, "charged_certus_quartz_crystal",
            AEItems.CERTUS_QUARTZ_CRYSTAL.asItem(), AEItems.CERTUS_QUARTZ_CRYSTAL_CHARGED.asItem()
        );
        charger(provider, "meteorite_compass", Items.COMPASS, AEItems.METEORITE_COMPASS.asItem());
        charger(provider, "guide", Items.BOOK, AEItems.TABLET.asItem());
    }

    private static void charger(RecipeOutput provider, String id, Item input, Item result) {
        charger(provider, id, input, result, AE_CHARGER_ENERGY_PER_OPERATION);
    }

    private static void charger(RecipeOutput provider, String id, Item input, Item result, int power) {
        int time = Math.ceilDiv(AE_CHARGER_ENERGY_PER_OPERATION, -CHARGER_POWER_KW * AE_PER_KW_TICK);
        ChargerChargingRecipe.builder()
            .requires(input)
            .result(result)
            .power(CHARGER_POWER_KW)
            .time(time)
            .save(provider, AppliedAnvilstics.location("ae2/charger/" + id));
    }
}