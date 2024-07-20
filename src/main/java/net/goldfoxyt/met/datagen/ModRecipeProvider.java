package net.goldfoxyt.met.datagen;

import net.goldfoxyt.met.Met;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.minecraftforge.registries.RegistryObject;

import java.util.concurrent.CompletableFuture;

import static net.goldfoxyt.met.block.ModBlocks.*;
import static net.minecraft.world.item.Items.*;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {


    public ModRecipeProvider(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pRegistries) {
        super(pOutput, pRegistries);
    }

    @Override
    protected void buildRecipes(RecipeOutput pRecipeOutput) {
        addEnchantingTableRecipes(pRecipeOutput, WHITE_ENCHANTING_TABLE, WHITE_CARPET, WHITE_DYE);
        addEnchantingTableRecipes(pRecipeOutput, ORANGE_ENCHANTING_TABLE, ORANGE_CARPET, ORANGE_DYE);
        addEnchantingTableRecipes(pRecipeOutput, MAGENTA_ENCHANTING_TABLE, MAGENTA_CARPET, MAGENTA_DYE);
        addEnchantingTableRecipes(pRecipeOutput, LIGHT_BLUE_ENCHANTING_TABLE, LIGHT_BLUE_CARPET, LIGHT_BLUE_DYE);
        addEnchantingTableRecipes(pRecipeOutput, YELLOW_ENCHANTING_TABLE, YELLOW_CARPET, YELLOW_DYE);
        addEnchantingTableRecipes(pRecipeOutput, LIME_ENCHANTING_TABLE, LIME_CARPET, LIME_DYE);
        addEnchantingTableRecipes(pRecipeOutput, PINK_ENCHANTING_TABLE, PINK_CARPET, PINK_DYE);
        addEnchantingTableRecipes(pRecipeOutput, BLUE_ENCHANTING_TABLE, BLUE_CARPET, BLUE_DYE);
        addEnchantingTableRecipes(pRecipeOutput, GREEN_ENCHANTING_TABLE, GREEN_CARPET, GREEN_DYE);
    }
    public void addEnchantingTableRecipes(RecipeOutput recipeOutput, RegistryObject<Block> block, Item carpetItem, Item dyeItem) {
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, block.get())
                .pattern(" B ")
                .pattern("DCD")
                .pattern("DOD")
                .define('B', Items.BOOK)
                .define('D', Items.DIAMOND)
                .define('C', carpetItem)
                .define('O', Items.OBSIDIAN)
                .unlockedBy("has_obsidian", has(Blocks.OBSIDIAN))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, block.get())
                .requires(Blocks.ENCHANTING_TABLE)
                .requires(dyeItem)
                .unlockedBy("has_obsidian", has(Blocks.OBSIDIAN))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(Met.MOD_ID, block.getId().getPath() + "_from_" + getItemName(dyeItem)));
    }
}