package net.gamerk_2.tetra_re_enlarged;

import com.mojang.logging.LogUtils;
import net.gamerk_2.tetra_re_enlarged.item.ModularLargeBladedItem;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

import javax.annotation.ParametersAreNonnullByDefault;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(TetraReEnlarged.MODID)
public class TetraReEnlarged
{
    public static final String MODID = "tetra_re_enlarged";
    private static final Logger LOGGER = LogUtils.getLogger();

    public TetraReEnlarged()
    {
        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(this);

        TetraReEnlargedRegistries.init(FMLJavaModLoadingContext.get().getModEventBus());

        modEventBus.addListener(this::onBuildContents);
    }

    private void onBuildContents(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.COMBAT) {
            event.accept(ModularLargeBladedItem.setupGreatSword());
        }
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        // null space
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
        // Do something when the server starts
        LOGGER.info("HELLO from server starting");
    }
}
