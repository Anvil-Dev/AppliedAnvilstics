package icu.takeneko.appliedanvilstics.mixins;

import dev.dubhe.anvilcraft.block.TransparentCraftingTableBlock;
import guideme.internal.GuideME;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(TransparentCraftingTableBlock.class)
public class TransparentCraftingTableBlockMixin {
    @Unique
    private static final Class<?> FakeForwardingServerLevel;

    static {
        try {
            FakeForwardingServerLevel = GuideME.class.getClassLoader().loadClass("guideme.scene.element.FakeForwardingServerLevel");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Inject(
        method = "updateShape",
        at = @At("HEAD"),
        cancellable = true
    )
    void guideMeCompat(
        BlockState state,
        Direction direction,
        BlockState neighborState,
        LevelAccessor level,
        BlockPos pos,
        BlockPos neighborPos,
        CallbackInfoReturnable<BlockState> cir
    ) {
        if (FakeForwardingServerLevel.isInstance(level)) {
            cir.setReturnValue(state);
            cir.cancel();
        }
    }
}
