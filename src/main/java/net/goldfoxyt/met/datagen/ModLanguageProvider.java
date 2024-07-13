package net.goldfoxyt.met.datagen;

import net.goldfoxyt.met.Met;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.data.LanguageProvider;

import static net.goldfoxyt.met.block.ModBlocks.*;

public class ModLanguageProvider extends LanguageProvider{
    public ModLanguageProvider(PackOutput output, String locale) {
        super(output, Met.MOD_ID, locale);
    }

    @Override
    protected void addTranslations() {
        addBlock(WHITE_ENCHANTING_TABLE, "White Enchanting Table");
        addBlock(ORANGE_ENCHANTING_TABLE, "Orange Enchanting Table");
        addBlock(BLUE_ENCHANTING_TABLE, "Blue Enchanting Table");
        addBlock(GREEN_ENCHANTING_TABLE, "Green Enchanting Table");
    }
    public void addCreativeModeTab(ResourceLocation key, String name) {
        add("creativetab." + key.getPath(), name);
    }
}
