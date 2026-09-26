package icu.takeneko.appliedanvilstics.all;

import dev.anvilcraft.lib.v2.registrum.util.entry.ItemEntry;
import dev.dubhe.anvilcraft.AnvilCraft;
import icu.takeneko.appliedanvilstics.AppliedAnvilstics;
import icu.takeneko.appliedanvilstics.item.GuideItem;
import net.neoforged.neoforge.client.model.generators.ModelFile;

public class AAItems {
    static {
        AppliedAnvilstics.REGISTRUM.defaultCreativeTab(AACreativeTabs.TAB.getKey());
    }

    public static final ItemEntry<GuideItem> GUIDE = AppliedAnvilstics.REGISTRUM
        .item("guide", GuideItem::new)
        .model((ctx, prov) ->
            prov.getBuilder(ctx.getName())
                .parent(new ModelFile.UncheckedModelFile(AnvilCraft.of("item/guide_book")))
        )
        .register();


    public static void setupRegistration() {
    }

}
