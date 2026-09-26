package icu.takeneko.appliedanvilstics.data;

import appeng.core.definitions.AEItems;
import dev.anvilcraft.lib.v2.registrum.providers.RegistrumTagsProvider;
import dev.dubhe.anvilcraft.init.item.ModItemTags;
import net.minecraft.world.item.Item;

public final class AAItemTags {
    private AAItemTags() {
    }

    public static void setupItemTags(RegistrumTagsProvider<Item> provider) {
        provider.addTag(ModItemTags.TEMPLATES)
            .add(AEItems.FLUIX_UPGRADE_SMITHING_TEMPLATE.holder().getKey());
    }
}
