package dev.anvilcraft.addon.applied_anvilstics.data;

import dev.anvilcraft.lib.v2.registrum.Registrum;
import dev.anvilcraft.lib.v2.registrum.providers.ProviderType;

public final class AADataGen {
    private AADataGen() {
    }

    public static void setupDataGeneration(Registrum registrum) {
        registrum.addDataGenerator(ProviderType.RECIPE, AARecipes::setupRecipes);
        registrum.addDataGenerator(ProviderType.ITEM_TAGS, AAItemTags::setupItemTags);
    }
}
