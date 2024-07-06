package net.goldfoxyt.met.datagen;

import net.goldfoxyt.met.block.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider  extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> future) {
        super(packOutput, future);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModBlocks.BLUE_ENCHANTING_TABLE.get())
                .pattern(" B ")
                .pattern("DCD")
                .pattern("DOD")
                .define('B', Items.BOOK)
                .define('D', Items.DIAMOND)
                .define('C', Items.BLUE_CARPET)
                .define('O', Items.OBSIDIAN)
                .unlockedBy("has_obsidian", has(Blocks.OBSIDIAN))
                .save(recipeOutput);
    }
}