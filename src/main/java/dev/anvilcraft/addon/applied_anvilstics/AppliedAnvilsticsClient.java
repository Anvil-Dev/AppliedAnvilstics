package dev.anvilcraft.addon.applied_anvilstics;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

@Mod(value = AppliedAnvilstics.MODID, dist = Dist.CLIENT)
public class AppliedAnvilsticsClient {
    public AppliedAnvilsticsClient(ModContainer container) {
        container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
    }
}
