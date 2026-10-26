package dev.anvilcraft.addon.applied_anvilstics.compat.ae2;

import appeng.recipes.handlers.InscriberProcessType;
import appeng.recipes.handlers.InscriberRecipe;
import dev.anvilcraft.lib.v2.recipe.InWorldRecipe;
import dev.anvilcraft.lib.v2.util.predicate.ItemIngredientPredicate;
import dev.dubhe.anvilcraft.recipe.anvil.wrap.ItemCrushRecipe;
import dev.dubhe.anvilcraft.recipe.anvil.wrap.StampingRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeHolder;

import java.util.Optional;

final class AE2InscriberAdapter {
    private AE2InscriberAdapter() {
    }

    /**
     * 将 AE2 压印配方转换为铁砧工艺的粉碎或冲压配方。
     * AE2 压印机的中间槽是主要原料，顶部和底部槽是可选的模具：
     * 没有模具时映射为粉碎；
     * 存在模具时映射为冲压，AE2 不会消耗顶部和底部模具，因此这里需要把模具作为额外产物输出。
     *
     * @param sourceId 原始 AE2 配方 ID，用于生成稳定的铁砧工艺配方 ID
     * @param recipe   AE2 压印配方
     * @return 转换后的配方；无法准确表达输入或模具保留语义时返回空
     */
    static Optional<RecipeHolder<InWorldRecipe>> adapt(
        ResourceLocation sourceId,
        InscriberRecipe recipe
    ) {
        ItemStack result = recipe.getResultItem().copy();
        if (result.isEmpty()) return Optional.empty();

        // 中间输入是压印的核心原料
        Optional<ItemIngredientPredicate> middle = AE2IngredientAdapter.toPredicate(recipe.getMiddleInput());
        if (middle.isEmpty()) return Optional.empty();

        Ingredient top = recipe.getTopOptional();
        Ingredient bottom = recipe.getBottomOptional();
        if (top.isEmpty() && bottom.isEmpty()) {
            // 没有模具时只处理中间输入，等价于把原料粉碎成产物。
            ItemCrushRecipe crush = ItemCrushRecipe.builder()
                .requires(middle.get())
                .result(result)
                .buildRecipe();
            return Optional.of(new RecipeHolder<>(
                AE2RecipeRuntimeAdapter.generatedId("item_crush", sourceId),
                crush
            ));
        }

        // 存在模具时改为冲压配方：中间输入必选，顶部和底部模具按需加入。
        Optional<ItemIngredientPredicate> topPredicate = Optional.empty();
        if (!top.isEmpty()) {
            topPredicate = AE2IngredientAdapter.toPredicate(top);
            if (topPredicate.isEmpty()) return Optional.empty();
        }

        Optional<ItemIngredientPredicate> bottomPredicate = Optional.empty();
        if (!bottom.isEmpty()) {
            bottomPredicate = AE2IngredientAdapter.toPredicate(bottom);
            if (bottomPredicate.isEmpty()) return Optional.empty();
        }

        StampingRecipe.Builder builder = StampingRecipe.builder().requires(middle.get());
        topPredicate.ifPresent(builder::requires);
        bottomPredicate.ifPresent(builder::requires);

        if (recipe.getProcessType() == InscriberProcessType.INSCRIBE) {
            // INSCRIBE 不消耗模具，因此把模具重新加入产物，模拟 AE2 的保留行为。
            Optional<ItemStack> preservedTop = top.isEmpty()
                ? Optional.empty()
                : AE2IngredientAdapter.toPreservedStack(top);
            Optional<ItemStack> preservedBottom = bottom.isEmpty()
                ? Optional.empty()
                : AE2IngredientAdapter.toPreservedStack(bottom);
            // 标签或多候选模具无法确定实际保留哪个物品，放弃转换以避免产出错误。
            if (!top.isEmpty() && preservedTop.isEmpty()) return Optional.empty();
            if (!bottom.isEmpty() && preservedBottom.isEmpty()) return Optional.empty();
            preservedTop.ifPresent(builder::result);
            preservedBottom.ifPresent(builder::result);
        }

        // PRESS 模式会消耗模具，因此这里不输出顶部和底部模具。
        builder.result(result);
        return Optional.of(new RecipeHolder<>(
            AE2RecipeRuntimeAdapter.generatedId("stamping", sourceId),
            builder.buildRecipe()
        ));
    }
}
