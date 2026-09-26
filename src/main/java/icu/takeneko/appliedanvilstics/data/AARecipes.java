package icu.takeneko.appliedanvilstics.data;

import dev.anvilcraft.lib.v2.registrum.providers.RegistrumRecipeProvider;
import icu.takeneko.appliedanvilstics.data.recipe.AAChargerChargingRecipeLoader;
import icu.takeneko.appliedanvilstics.data.recipe.AAItemCrushRecipeLoader;
import icu.takeneko.appliedanvilstics.data.recipe.AASolidLiquidRecipeLoader;
import icu.takeneko.appliedanvilstics.data.recipe.AAStampingRecipeLoader;

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
