package dev.anvilcraft.addon.applied_anvilstics.api;

import net.minecraft.world.item.crafting.RecipeHolder;

import java.util.List;

/**
 * 允许在配方加载完成后注入任意类型的运行时配方。
 */
public interface AARecipeManagerExtension {
    void appliedanvilstics$addRecipes(List<RecipeHolder<?>> recipes);
}
