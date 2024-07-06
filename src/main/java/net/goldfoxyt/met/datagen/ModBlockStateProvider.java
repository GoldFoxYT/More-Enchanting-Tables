package net.goldfoxyt.met.datagen;

import net.goldfoxyt.met.Met;
import net.goldfoxyt.met.block.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.*;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.function.Function;

public class ModBlockStateProvider extends ModModelProvider {

    public ModBlockStateProvider(PackOutput output, Function factory, ExistingFileHelper existingFileHelper) {
        super(output, factory, existingFileHelper);
    }

    @Override
    public ModelBuilder cube(String name, ResourceLocation down, ResourceLocation up, ResourceLocation north, ResourceLocation south, ResourceLocation east, ResourceLocation west) {
        return super.cube(name, down, up, north, south, east, west);
    }

    @Override
    protected void registerModels() {
        this.cube("blue_enchanting_table",
                ResourceLocation.parse("block/blue_enchanting_table_bottom"),
                ResourceLocation.parse("block/blue_enchanting_table_top"),
                ResourceLocation.parse("block/blue_enchanting_table_side"),
                ResourceLocation.parse("block/blue_enchanting_table_side"),
                ResourceLocation.parse("block/blue_enchanting_table_side"),
                ResourceLocation.parse("block/blue_enchanting_table_side")
        );
    }
}
