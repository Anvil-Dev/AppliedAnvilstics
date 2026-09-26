package dev.anvilcraft.addon.applied_anvilstics.mixins;

import dev.dubhe.anvilcraft.block.TransparentCraftingTableBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
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
    private static final String[] APPLIEDANVILSTICS$PREVIEW_ACCESSORS = {
        // GuideME 手册的结构预览
        "guideme.scene.element.FakeForwardingServerLevel",
        // Ageratum（铁砧工艺指南）的结构预览
        "dev.anvilcraft.resource.ageratum.client.util.level.DelegatingServerLevelAccessor"
    };

    @Inject(
        method = "updateShape",
        at = @At("HEAD"),
        cancellable = true
    )
    void appliedanvilstics$keepPreviewShape(
        BlockState state,
        Direction direction,
        BlockState neighborState,
        LevelAccessor level,
        BlockPos pos,
        BlockPos neighborPos,
        CallbackInfoReturnable<BlockState> cir
    ) {
        // 手册的结构预览在虚拟的关卡访问器中放置方块，它不是真正的世界。
        // 通透工作台重算矩阵需要一个真正的 Level，此时保留结构中已有的方块状态。
        if (this.appliedanvilstics$isGuidePreview(level)) {
            cir.setReturnValue(state);
        }
    }

    @Unique
    private boolean appliedanvilstics$isGuidePreview(LevelAccessor level) {
        if (level instanceof Level) return false;
        String name = level.getClass().getName();
        for (String previewAccessor : APPLIEDANVILSTICS$PREVIEW_ACCESSORS) {
            if (previewAccessor.equals(name)) return true;
        }
        return false;
    }
}