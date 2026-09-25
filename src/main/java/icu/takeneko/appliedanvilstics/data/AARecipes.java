package icu.takeneko.appliedanvilstics.data;

import com.tterrag.registrate.providers.RegistrateRecipeProvider;
import icu.takeneko.appliedanvilstics.data.recipe.AAChargerChargingRecipeLoader;
import icu.takeneko.appliedanvilstics.data.recipe.AAItemCrushRecipeLoader;
import icu.takeneko.appliedanvilstics.data.recipe.AASolidLiquidRecipeLoader;
import icu.takeneko.appliedanvilstics.data.recipe.AAStampingRecipeLoader;

public final class AARecipes {
    private AARecipes() {
    }

    public static void setupRecipes(RegistrateRecipeProvider provider) {
        AAItemCrushRecipeLoader.setupRecipes(provider);
        AAStampingRecipeLoader.setupRecipes(provider);
        AAChargerChargingRecipeLoader.setupRecipes(provider);
        AASolidLiquidRecipeLoader.setupRecipes(provider);
    }
}
