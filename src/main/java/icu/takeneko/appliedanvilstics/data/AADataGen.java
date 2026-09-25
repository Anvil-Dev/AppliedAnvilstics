package icu.takeneko.appliedanvilstics.data;

import com.tterrag.registrate.Registrate;
import com.tterrag.registrate.providers.ProviderType;

public final class AADataGen {
    private AADataGen() {
    }

    public static void setupDataGeneration(Registrate registrate) {
        registrate.addDataGenerator(ProviderType.RECIPE, AARecipes::setupRecipes);
        registrate.addDataGenerator(ProviderType.ITEM_TAGS, AAItemTags::setupItemTags);
    }
}
