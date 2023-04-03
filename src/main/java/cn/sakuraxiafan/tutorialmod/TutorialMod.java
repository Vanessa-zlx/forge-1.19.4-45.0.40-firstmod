package cn.sakuraxiafan.tutorialmod;

import cn.sakuraxiafan.tutorialmod.block.ModBlocks;
import cn.sakuraxiafan.tutorialmod.item.ModCreativeModeTabs;
import cn.sakuraxiafan.tutorialmod.item.ModItems;
import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.world.item.*;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(TutorialMod.MOD_ID)
public class TutorialMod
{
    //a comment
    public static final String MOD_ID = "tutorialmod";
    private static final Logger LOGGER = LogUtils.getLogger();
    public TutorialMod()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        modEventBus.addListener(this::commonSetup);
        MinecraftForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::addCreative);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        LOGGER.info("HELLO FROM COMMON SETUP");
    }
    private void addCreative(CreativeModeTabEvent.BuildContents event)
    {
        if (event.getTab() == CreativeModeTabs.INGREDIENTS){
            event.accept(ModItems.ZIRCON);
            event.accept(ModItems.RAW_ZIRCON);
        }
        if (event.getTab() == ModCreativeModeTabs.TUTORIAL_TAB){
            event.accept(ModItems.ZIRCON);
            event.accept(ModItems.RAW_ZIRCON);
            event.accept(ModBlocks.ZIRCON_BLOCK);
            event.accept(ModBlocks.DEEPSLATE_ZIRCON_ORE);
            event.accept(ModBlocks.ENDSTONE_ZIRCON_ORE);
            event.accept(ModBlocks.NETHERRACK_ZIRCON_ORE);
            event.accept(ModBlocks.ZIRCON_ORE);
        }
        if (event.getTab() == CreativeModeTabs.BUILDING_BLOCKS){
            event.accept(ModBlocks.ZIRCON_BLOCK);
        }
        if (event.getTab() == CreativeModeTabs.NATURAL_BLOCKS){
            event.accept(ModBlocks.DEEPSLATE_ZIRCON_ORE);
            event.accept(ModBlocks.ENDSTONE_ZIRCON_ORE);
            event.accept(ModBlocks.NETHERRACK_ZIRCON_ORE);
            event.accept(ModBlocks.ZIRCON_ORE);
        }
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            // Some client setup code
            LOGGER.info("HELLO FROM CLIENT SETUP");
            LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
        }
    }
}
