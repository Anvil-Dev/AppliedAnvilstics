package dev.anvilcraft.addon.applied_anvilstics;

import com.mojang.logging.LogUtils;
import dev.anvilcraft.lib.v2.registrum.Registrum;
import guideme.Guide;
import dev.anvilcraft.addon.applied_anvilstics.all.AACreativeTabs;
import dev.anvilcraft.addon.applied_anvilstics.all.AAItems;
import dev.anvilcraft.addon.applied_anvilstics.config.AAConfig;
import dev.anvilcraft.addon.applied_anvilstics.data.AADataGen;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import org.slf4j.Logger;

@Mod(AppliedAnvilstics.MODID)
public class AppliedAnvilstics {
    public static final String MODID = "applied_anvilstics";
    public static final Logger LOGGER = LogUtils.getLogger();
    public static final Registrum REGISTRUM = Registrum.create(MODID);

    public AppliedAnvilstics(IEventBus modEventBus, ModContainer modContainer) {
        modContainer.registerConfig(ModConfig.Type.COMMON, AAConfig.SPEC);

        setupRegistration(modEventBus);
        AADataGen.setupDataGeneration(REGISTRUM);
    }

    public static void setupRegistration(IEventBus modBus) {
        AACreativeTabs.DR.register(modBus);
        AAItems.setupRegistration();
        Guide.builder(location("guide"))
            .folder("aaguide")
            .register(true)
            .build();
    }

    public static ResourceLocation location(String path) {
        return ResourceLocation.fromNamespaceAndPath(MODID, path);
    }
}