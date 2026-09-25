package icu.takeneko.appliedanvilstics;

import com.mojang.logging.LogUtils;
import com.tterrag.registrate.Registrate;
import guideme.Guide;
import icu.takeneko.appliedanvilstics.all.AACreativeTabs;
import icu.takeneko.appliedanvilstics.all.AAItems;
import icu.takeneko.appliedanvilstics.config.AAConfig;
import icu.takeneko.appliedanvilstics.data.AADataGen;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import org.slf4j.Logger;

@Mod(AppliedAnvilstics.MODID)
public class AppliedAnvilstics {
    public static final String MODID = "appliedanvilstics";
    public static final Logger LOGGER = LogUtils.getLogger();
    public static final Registrate REGISTRATE = Registrate.create(MODID);

    public AppliedAnvilstics(IEventBus modEventBus, ModContainer modContainer) {
        modContainer.registerConfig(ModConfig.Type.COMMON, AAConfig.SPEC);

        setupRegistration(modEventBus);
        AADataGen.setupDataGeneration(REGISTRATE);
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