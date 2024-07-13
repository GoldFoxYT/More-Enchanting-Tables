package net.goldfoxyt.met.datagen;

import net.goldfoxyt.met.Met;
import net.goldfoxyt.met.block.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
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

        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, ModBlocks.BLUE_ENCHANTING_TABLE.get())
                .requires(Blocks.ENCHANTING_TABLE)
                .requires(Items.BLUE_DYE)
                .unlockedBy("has_obsidian", has(Blocks.OBSIDIAN))
                .save(recipeOutput, Met.MOD_ID + ":blue_enchanting_table_from_blue_dye");

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModBlocks.GREEN_ENCHANTING_TABLE.get())
                .pattern(" B ")
                .pattern("DCD")
                .pattern("DOD")
                .define('B', Items.BOOK)
                .define('D', Items.DIAMOND)
                .define('C', Items.GREEN_CARPET)
                .define('O', Items.OBSIDIAN)
                .unlockedBy("has_obsidian", has(Blocks.OBSIDIAN))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, ModBlocks.GREEN_ENCHANTING_TABLE.get())
                .requires(Blocks.ENCHANTING_TABLE)
                .requires(Items.GREEN_DYE)
                .unlockedBy("has_obsidian", has(Blocks.OBSIDIAN))
                .save(recipeOutput, Met.MOD_ID + ":green_enchanting_table_from_green_dye");

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModBlocks.WHITE_ENCHANTING_TABLE.get())
                .pattern(" B ")
                .pattern("DCD")
                .pattern("DOD")
                .define('B', Items.BOOK)
                .define('D', Items.DIAMOND)
                .define('C', Items.WHITE_CARPET)
                .define('O', Items.OBSIDIAN)
                .unlockedBy("has_obsidian", has(Blocks.OBSIDIAN))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, ModBlocks.WHITE_ENCHANTING_TABLE.get())
                .requires(Blocks.ENCHANTING_TABLE)
                .requires(Items.WHITE_DYE)
                .unlockedBy("has_obsidian", has(Blocks.OBSIDIAN))
                .save(recipeOutput, Met.MOD_ID + ":white_enchanting_table_from_white_dye");

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModBlocks.ORANGE_ENCHANTING_TABLE.get())
                .pattern(" B ")
                .pattern("DCD")
                .pattern("DOD")
                .define('B', Items.BOOK)
                .define('D', Items.DIAMOND)
                .define('C', Items.ORANGE_CARPET)
                .define('O', Items.OBSIDIAN)
                .unlockedBy("has_obsidian", has(Blocks.OBSIDIAN))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, ModBlocks.ORANGE_ENCHANTING_TABLE.get())
                .requires(Blocks.ENCHANTING_TABLE)
                .requires(Items.ORANGE_DYE)
                .unlockedBy("has_obsidian", has(Blocks.OBSIDIAN))
                .save(recipeOutput, Met.MOD_ID + ":orange_enchanting_table_from_orange_dye");
    }
}