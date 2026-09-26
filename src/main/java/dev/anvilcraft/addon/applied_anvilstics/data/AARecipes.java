package dev.anvilcraft.addon.applied_anvilstics.data;

import dev.anvilcraft.lib.v2.registrum.providers.RegistrumRecipeProvider;
import dev.anvilcraft.addon.applied_anvilstics.data.recipe.AAChargerChargingRecipeLoader;
import dev.anvilcraft.addon.applied_anvilstics.data.recipe.AAItemCrushRecipeLoader;
import dev.anvilcraft.addon.applied_anvilstics.data.recipe.AASolidLiquidRecipeLoader;
import dev.anvilcraft.addon.applied_anvilstics.data.recipe.AAStampingRecipeLoader;

public final class AARecipes {
    private AARecipes() {
    }

    public static void setupRecipes(RegistrumRecipeProvider provider) {
        AAItemCrushRecipeLoader.setupRecipes(provider);
        AAStampingRecipeLoader.setupRecipes(provider);
        AAChargerChargingRecipeLoader.setupRecipes(provider);
        AASolidLiquidRecipeLoader.setupRecipes(provider);
    }
}
