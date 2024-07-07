package net.goldfoxyt.met.datagen.loot;

import net.goldfoxyt.met.block.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashSet;
import java.util.Set;

public class ModBlockLootTables extends BlockLootSubProvider{
    private static final Set<Item> EXPLOSION_RESISTANT = new HashSet<>();

    public ModBlockLootTables(HolderLookup.Provider provider) {
        super(EXPLOSION_RESISTANT, FeatureFlags.REGISTRY.allFlags(), provider);
    }

    @Override
    protected void generate() {
        this.add(ModBlocks.BLUE_ENCHANTING_TABLE.get(), (block) -> this.createNameableBlockEntityTable(block));
        this.add(ModBlocks.GREEN_ENCHANTING_TABLE.get(), (block) -> this.createNameableBlockEntityTable(block));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}