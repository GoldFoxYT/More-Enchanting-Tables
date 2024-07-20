package net.goldfoxyt.met.block;

import net.goldfoxyt.met.Met;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.EnchantingTableBlock;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;


public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, Met.MOD_ID);
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Met.MOD_ID);

    public static final RegistryObject<Block> WHITE_ENCHANTING_TABLE = registerBlock("white_enchanting_table",
            () -> new ColoredEnchantingTable(Block.Properties.ofFullCopy(Blocks.ENCHANTING_TABLE).mapColor(DyeColor.WHITE).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> ORANGE_ENCHANTING_TABLE = registerBlock("orange_enchanting_table",
            () -> new ColoredEnchantingTable(Block.Properties.ofFullCopy(Blocks.ENCHANTING_TABLE).mapColor(DyeColor.ORANGE).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> MAGENTA_ENCHANTING_TABLE = registerBlock("magenta_enchanting_table",
            () -> new ColoredEnchantingTable(Block.Properties.ofFullCopy(Blocks.ENCHANTING_TABLE).mapColor(DyeColor.MAGENTA).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> LIGHT_BLUE_ENCHANTING_TABLE = registerBlock("light_blue_enchanting_table",
            () -> new ColoredEnchantingTable(Block.Properties.ofFullCopy(Blocks.ENCHANTING_TABLE).mapColor(DyeColor.LIGHT_BLUE).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> YELLOW_ENCHANTING_TABLE = registerBlock("yellow_enchanting_table",
            () -> new ColoredEnchantingTable(Block.Properties.ofFullCopy(Blocks.ENCHANTING_TABLE).mapColor(DyeColor.YELLOW).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> LIME_ENCHANTING_TABLE = registerBlock("lime_enchanting_table",
            () -> new ColoredEnchantingTable(Block.Properties.ofFullCopy(Blocks.ENCHANTING_TABLE).mapColor(DyeColor.LIME).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> PINK_ENCHANTING_TABLE = registerBlock("pink_enchanting_table",
            () -> new ColoredEnchantingTable(Block.Properties.ofFullCopy(Blocks.ENCHANTING_TABLE).mapColor(DyeColor.PINK).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> GRAY_ENCHANTING_TABLE = registerBlock("gray_enchanting_table",
            () -> new ColoredEnchantingTable(Block.Properties.ofFullCopy(Blocks.ENCHANTING_TABLE).mapColor(DyeColor.GRAY).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> LIGHT_GRAY_ENCHANTING_TABLE = registerBlock("light_gray_enchanting_table",
            () -> new ColoredEnchantingTable(Block.Properties.ofFullCopy(Blocks.ENCHANTING_TABLE).mapColor(DyeColor.LIGHT_GRAY).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> CYAN_ENCHANTING_TABLE = registerBlock("cyan_enchanting_table",
            () -> new ColoredEnchantingTable(Block.Properties.ofFullCopy(Blocks.ENCHANTING_TABLE).mapColor(DyeColor.CYAN).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> PURPLE_ENCHANTING_TABLE = registerBlock("purple_enchanting_table",
            () -> new ColoredEnchantingTable(Block.Properties.ofFullCopy(Blocks.ENCHANTING_TABLE).mapColor(DyeColor.PURPLE).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> BLUE_ENCHANTING_TABLE = registerBlock("blue_enchanting_table",
            () -> new ColoredEnchantingTable(Block.Properties.ofFullCopy(Blocks.ENCHANTING_TABLE).mapColor(DyeColor.BLUE).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> BROWN_ENCHANTING_TABLE = registerBlock("brown_enchanting_table",
            () -> new ColoredEnchantingTable(Block.Properties.ofFullCopy(Blocks.ENCHANTING_TABLE).mapColor(DyeColor.BROWN).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> GREEN_ENCHANTING_TABLE = registerBlock("green_enchanting_table",
            () -> new ColoredEnchantingTable(Block.Properties.ofFullCopy(Blocks.ENCHANTING_TABLE).mapColor(DyeColor.GREEN).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> BLACK_ENCHANTING_TABLE = registerBlock("black_enchanting_table",
            () -> new ColoredEnchantingTable(Block.Properties.ofFullCopy(Blocks.ENCHANTING_TABLE).mapColor(DyeColor.BLACK).requiresCorrectToolForDrops()));

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }
    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return ModBlocks.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void registerBlocks(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
    public static void registerItems(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}