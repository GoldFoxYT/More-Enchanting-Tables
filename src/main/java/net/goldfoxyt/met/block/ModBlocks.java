package net.goldfoxyt.met.block;

import net.goldfoxyt.met.Met;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
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
    public static final RegistryObject<Block> BLUE_ENCHANTING_TABLE = registerBlock("blue_enchanting_table",
            () -> new ColoredEnchantingTable(Block.Properties.ofFullCopy(Blocks.ENCHANTING_TABLE).mapColor(DyeColor.BLUE).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> GREEN_ENCHANTING_TABLE = registerBlock("green_enchanting_table",
            () -> new ColoredEnchantingTable(Block.Properties.ofFullCopy(Blocks.ENCHANTING_TABLE).mapColor(DyeColor.GREEN).requiresCorrectToolForDrops()));

    /*
    LIGHT_BLUE
    YELLOW
    LIME
    PINK
    GRAY
    LIGHT_GRAY
    CYAN
    PURPLE
    BROWN
    BLACK
     */

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