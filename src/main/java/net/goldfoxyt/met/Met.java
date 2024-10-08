package net.goldfoxyt.met;

import com.mojang.logging.LogUtils;
import net.goldfoxyt.met.block.blockentity.ColoredEnchantingTableRenderer;
import net.goldfoxyt.met.block.blockentity.ModBlockEntities;
import net.goldfoxyt.met.block.ModBlocks;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Met.MOD_ID)
public class Met {
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "met";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    public Met() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        ModBlocks.registerBlocks(modEventBus);
        ModBlocks.registerItems(modEventBus);
        ModBlockEntities.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);

        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {

    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event){
        if (event.getTabKey() == CreativeModeTabs.FUNCTIONAL_BLOCKS){
            event.accept(ModBlocks.WHITE_ENCHANTING_TABLE);
            event.accept(ModBlocks.ORANGE_ENCHANTING_TABLE);
            event.accept(ModBlocks.MAGENTA_ENCHANTING_TABLE);
            event.accept(ModBlocks.LIGHT_BLUE_ENCHANTING_TABLE);
            event.accept(ModBlocks.YELLOW_ENCHANTING_TABLE);
            event.accept(ModBlocks.LIME_ENCHANTING_TABLE);
            event.accept(ModBlocks.PINK_ENCHANTING_TABLE);
            event.accept(ModBlocks.GRAY_ENCHANTING_TABLE);
            event.accept(ModBlocks.LIGHT_GRAY_ENCHANTING_TABLE);
            event.accept(ModBlocks.CYAN_ENCHANTING_TABLE);
            event.accept(ModBlocks.PURPLE_ENCHANTING_TABLE);
            event.accept(ModBlocks.BLUE_ENCHANTING_TABLE);
            event.accept(ModBlocks.BROWN_ENCHANTING_TABLE);
            event.accept(ModBlocks.GREEN_ENCHANTING_TABLE);
            event.accept(ModBlocks.BLACK_ENCHANTING_TABLE);
        }
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            event.enqueueWork(() -> BlockEntityRenderers.register(ModBlockEntities.COLORED_ENCHANTING_TABLE.get(), ColoredEnchantingTableRenderer::new));
        }
    }
    @SubscribeEvent
    public static void registerRenderers(final EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(ModBlockEntities.COLORED_ENCHANTING_TABLE.get(), ColoredEnchantingTableRenderer::new);
    }
}
