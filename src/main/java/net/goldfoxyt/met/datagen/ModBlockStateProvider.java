package net.goldfoxyt.met.datagen;

import net.goldfoxyt.met.Met;
import net.minecraft.core.Direction;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.*;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static net.goldfoxyt.met.block.ModBlocks.*;

public class ModBlockStateProvider extends BlockStateProvider {


    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, Met.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        createCustomEnchantingTable(WHITE_ENCHANTING_TABLE);
        createCustomEnchantingTable(ORANGE_ENCHANTING_TABLE);
        createCustomEnchantingTable(MAGENTA_ENCHANTING_TABLE);
        createCustomEnchantingTable(LIGHT_BLUE_ENCHANTING_TABLE);
        createCustomEnchantingTable(YELLOW_ENCHANTING_TABLE);
        createCustomEnchantingTable(LIME_ENCHANTING_TABLE);
        createCustomEnchantingTable(PINK_ENCHANTING_TABLE);
        createCustomEnchantingTable(BLUE_ENCHANTING_TABLE);
        createCustomEnchantingTable(GREEN_ENCHANTING_TABLE);
    }
    private void createCustomEnchantingTable(RegistryObject<Block> registryObject) {
        Block block = registryObject.get();
        String name = ForgeRegistries.BLOCKS.getKey(block).getPath();

        BlockModelBuilder model = models().withExistingParent(name, "block/block")
                .texture("particle", mcLoc("block/enchanting_table_bottom"))
                .texture("bottom", mcLoc("block/enchanting_table_bottom"))
                .texture("top", modLoc("block/" + name + "_top"))
                .texture("side", modLoc("block/" + name + "_side"));

        model.element()
                .from(0, 0, 0)
                .to(16, 12, 16)
                .face(Direction.DOWN).uvs(0, 0, 16, 16).texture("#bottom").cullface(Direction.DOWN).end()
                .face(Direction.UP).uvs(0, 0, 16, 16).texture("#top").end()
                .face(Direction.NORTH).uvs(0, 4, 16, 16).texture("#side").cullface(Direction.NORTH).end()
                .face(Direction.SOUTH).uvs(0, 4, 16, 16).texture("#side").cullface(Direction.SOUTH).end()
                .face(Direction.WEST).uvs(0, 4, 16, 16).texture("#side").cullface(Direction.WEST).end()
                .face(Direction.EAST).uvs(0, 4, 16, 16).texture("#side").cullface(Direction.EAST).end();

        simpleBlockWithItem(block, model);
    }
}
