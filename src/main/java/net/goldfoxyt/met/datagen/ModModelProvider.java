package net.goldfoxyt.met.datagen;

import net.goldfoxyt.met.Met;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;

import net.minecraftforge.client.model.generators.ModelBuilder;
import net.minecraftforge.client.model.generators.ModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.function.Function;

public class ModModelProvider extends ModelProvider {
    public ModModelProvider(PackOutput output, Function factory, ExistingFileHelper existingFileHelper) {
        super(output, Met.MOD_ID, "block", factory, existingFileHelper);
    }

    @Override
    protected void registerModels() {

    }

    @Override
    public String getName() {
        return "";
    }

    @Override
    public ModelBuilder cube(String name, ResourceLocation down, ResourceLocation up, ResourceLocation north, ResourceLocation south, ResourceLocation east, ResourceLocation west) {
        return super.cube(name, down, up, north, south, east, west);
    }
}
