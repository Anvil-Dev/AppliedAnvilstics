package icu.takeneko.appliedanvilstics.all;

import icu.takeneko.appliedanvilstics.AppliedAnvilstics;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class AACreativeTabs {
    public static final DeferredRegister<CreativeModeTab> DR = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, AppliedAnvilstics.MODID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> TAB = DR.register(
        "tab",
        () -> CreativeModeTab.builder()
            .title(AppliedAnvilstics.REGISTRATE.addRawLang("itemGroup.appliedanvilstics.tab", "Applied Anvilstics"))
            .icon(AAItems.GUIDE.asItem()::getDefaultInstance)
            .build()
    );
}
