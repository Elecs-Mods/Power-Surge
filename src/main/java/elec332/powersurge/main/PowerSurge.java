package elec332.powersurge.main;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import elec332.core.helper.FileHelper;
import elec332.core.helper.MCModInfo;
import elec332.core.helper.ModInfoHelper;
import elec332.core.modBaseUtils.ModBase;
import elec332.core.modBaseUtils.modInfo;
import elec332.core.network.NetworkHandler;
import elec332.powersurge.eventhandlers.PlayerEvents;
import elec332.powersurge.eventhandlers.SurgeHandler;
import elec332.powersurge.init.BlockRegister;
import elec332.powersurge.init.CommandRegister;
import elec332.powersurge.init.ItemRegister;
import elec332.powersurge.network.PacketSetSurgeData;
import elec332.powersurge.proxies.CommonProxy;
import net.minecraftforge.common.MinecraftForge;

import java.io.File;

/**
 * Created by Elec332 on 24-2-2015.
 */
@Mod(modid = PowerSurge.ModID, name = PowerSurge.ModName, dependencies = modInfo.DEPENDENCIES+"@[#ELECCORE_VER#,)",
        acceptedMinecraftVersions = modInfo.ACCEPTEDMCVERSIONS, useMetadata = true, canBeDeactivated = true)
public class PowerSurge extends ModBase {

    public static final String ModName = "Power Surge"; //Human readable name
    public static final String ModID = "PowerSurge";  //modid
    public static int max_Charge = 20000;

    @SidedProxy(clientSide = "elec332.powersurge.proxies.ClientProxy", serverSide = "elec332.powersurge.proxies.CommonProxy")
    public static CommonProxy proxy;

    @Mod.Instance(ModID)
    public static PowerSurge instance;
    public static NetworkHandler networkHandler;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        this.cfg = FileHelper.getConfigFileElec(event);
        loadConfiguration();
        networkHandler = new NetworkHandler(ModInfoHelper.getModID(event));
        networkHandler.registerClientPacket(PacketSetSurgeData.class);
        ItemRegister.instance.preInit(event);
        BlockRegister.instance.preInit(event);
        //setting up mod stuff

        loadConfiguration();
        MCModInfo.CreateMCModInfo(event, "Created by Elec332",
                "Power Surge -insert description-",
                "Loading URL...", "logo.png",
                new String[]{"Elec332"});
        notifyEvent(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        loadConfiguration();
        ItemRegister.instance.init(event);
        BlockRegister.instance.init(event);
        proxy.registerRenders();
        MinecraftForge.EVENT_BUS.register(new PlayerEvents());
        FMLCommonHandler.instance().bus().register(new PlayerEvents());
        MinecraftForge.EVENT_BUS.register(new SurgeHandler());

        notifyEvent(event);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event){
        loadConfiguration();
        //Mod compat stuff

        notifyEvent(event);
    }

    @Mod.EventHandler
    public void serverStarting(FMLServerStartingEvent event) {
        CommandRegister.instance.init(event);
    }

    File cfg;

    @Override
    public File configFile() {
        return cfg;
    }

    @Override
    public String modID(){
        return ModID;
    }
}