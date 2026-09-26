package dev.anvilcraft.addon.applied_anvilstics.data;

import dev.anvilcraft.lib.v2.config.ConfigData;
import dev.anvilcraft.lib.v2.registrum.Registrum;
import dev.anvilcraft.lib.v2.registrum.providers.ProviderType;
import dev.anvilcraft.addon.applied_anvilstics.config.AAConfig;

public final class AADataGen {
    private AADataGen() {
    }

    public static void setupDataGeneration(Registrum registrum) {
        registrum.addDataGenerator(ProviderType.RECIPE, AARecipes::setupRecipes);
        registrum.addDataGenerator(ProviderType.ITEM_TAGS, AAItemTags::setupItemTags);
        // 为配置项生成翻译键（含 @Comment 生成的 tooltip）。
        registrum.addDataGenerator(ProviderType.LANG, prov -> ConfigData.readConfigClass(prov, AAConfig.class));
    }
}
