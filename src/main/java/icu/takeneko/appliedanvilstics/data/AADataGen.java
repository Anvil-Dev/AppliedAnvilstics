package icu.takeneko.appliedanvilstics.data;

import com.tterrag.registrate.Registrate;
import com.tterrag.registrate.providers.ProviderType;

public class AADataGen {
    public static void setupDataGeneration(Registrate registrate) {
        registrate.addDataGenerator(ProviderType.RECIPE, AARecipes::setupRecipes);
    }
}
