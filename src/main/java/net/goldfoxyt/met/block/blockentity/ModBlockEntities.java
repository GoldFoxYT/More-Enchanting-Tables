package net.goldfoxyt.met.block.blockentity;

import net.goldfoxyt.met.Met;
import net.goldfoxyt.met.block.ModBlocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, Met.MOD_ID);

    public static final RegistryObject<BlockEntityType<ColoredEnchantingTableBlockEntity>> COLORED_ENCHANTING_TABLE = BLOCK_ENTITIES.register("colored_enchanting_table",
            () -> BlockEntityType.Builder.of(ColoredEnchantingTableBlockEntity::new,
                            ModBlocks.BLUE_ENCHANTING_TABLE.get(),
                            ModBlocks.GREEN_ENCHANTING_TABLE.get())
                    .build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}