package dev.anvilcraft.addon.applied_anvilstics.item;

import appeng.items.AEBaseItem;
import guideme.GuidesCommon;
import dev.anvilcraft.addon.applied_anvilstics.AppliedAnvilstics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class GuideItem extends AEBaseItem {
    public static final ResourceLocation ID = AppliedAnvilstics.location("guide");

    public GuideItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (level.isClientSide()) {
            GuidesCommon.openGuide(player, ID);
        }
        return new InteractionResultHolder<>(InteractionResult.FAIL, stack);
    }
}
