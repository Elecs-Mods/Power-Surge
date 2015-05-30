package elec332.powersurge.main;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import elec332.core.config.ConfigWrapper;
import elec332.core.helper.FileHelper;
import elec332.core.helper.MCModInfo;
import elec332.core.helper.ModInfoHelper;
import elec332.core.modBaseUtils.ModInfo;
import elec332.core.network.NetworkHandler;
import elec332.core.util.EventHelper;
import elec332.powersurge.abilities.*;
import elec332.powersurge.eventhandlers.PlayerEvents;
import elec332.powersurge.eventhandlers.SurgeDropsHandler;
import elec332.powersurge.eventhandlers.SurgeHandler;
import elec332.powersurge.init.BlockRegister;
import elec332.powersurge.init.CommandRegister;
import elec332.powersurge.init.ItemRegister;
import elec332.powersurge.network.PacketCompleteSync;
import elec332.powersurge.network.PacketHandleKeyInput;
import elec332.powersurge.network.PacketNetworkAbility;
import elec332.powersurge.network.PacketSetSurgeData;
import elec332.powersurge.proxies.CommonProxy;
import elec332.powersurge.surge.SurgeRegistry;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraftforge.common.config.Configuration;
import org.apache.logging.log4j.Logger;

import java.util.Random;

/**
 * Created by Elec332 on 24-2-2015.
 */
@Mod(modid = PowerSurge.ModID, name = PowerSurge.ModName, dependencies = ModInfo.DEPENDENCIES+"@[#ELECCORE_VER#,)",
        acceptedMinecraftVersions = ModInfo.ACCEPTEDMCVERSIONS, useMetadata = true, canBeDeactivated = true)
public class PowerSurge{

    public static final String ModName = "Power Surge"; //Human readable name
    public static final String ModID = "PowerSurge";  //modid

    @SidedProxy(clientSide = "elec332.powersurge.proxies.ClientProxy", serverSide = "elec332.powersurge.proxies.CommonProxy")
    public static CommonProxy proxy;

    @Mod.Instance(ModID)
    public static PowerSurge instance;
    public static NetworkHandler networkHandler;
    public static ConfigWrapper configWrapper;
    public static Logger logger;
    public static Configuration surgeConfig;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        configWrapper = new ConfigWrapper(new Configuration(FileHelper.getConfigFileElec(event)));
        logger = event.getModLog();
        networkHandler = new NetworkHandler(ModInfoHelper.getModID(event));
        networkHandler.registerClientPacket(PacketSetSurgeData.class);
        networkHandler.registerServerPacket(PacketHandleKeyInput.class);
        networkHandler.registerClientPacket(PacketCompleteSync.class);
        networkHandler.registerClientPacket(PacketNetworkAbility.class);
        ItemRegister.instance.preInit(event);
        BlockRegister.instance.preInit(event);
        configWrapper.registerConfig(new Config());
        surgeConfig = ConfigWrapper.wrapCategoryAsConfig(configWrapper.getConfiguration(), "powersurge abilities");
        registerAbilities();
        configWrapper.refresh();
        //setting up mod stuff

        MCModInfo.CreateMCModInfo(event, "Created by Elec332",
                "Power Surge -insert description-",
                "Loading URL...", "logo.png",
                new String[]{"Elec332"});
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        ItemRegister.instance.init(event);
        BlockRegister.instance.init(event);
        proxy.registerRenders();
        proxy.registerKeys();
        EventHelper.registerHandler(EventHelper.Handler.BOTH, new PlayerEvents());
        EventHelper.registerHandlerForge(new SurgeHandler());
        EventHelper.registerHandlerForge(new SurgeDropsHandler());
        configWrapper.refresh();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event){
        configWrapper.refresh();
        //Mod compat stuff

    }

    @Mod.EventHandler
    public void serverStarting(FMLServerStartingEvent event) {
        CommandRegister.instance.init(event);
    }


    private void registerAbilities(){
        SurgeRegistry.registerAbility(new Jump());
        SurgeRegistry.registerAbility(new Invisibility());
        SurgeRegistry.registerAbility(new Flight());
        SurgeRegistry.registerAbility(new FireCharge());
        SurgeRegistry.registerAbility(new PowerStrike());
        SurgeRegistry.registerAbility(new Leap());
        SurgeRegistry.registerAbility(new QuickStrike());
        SurgeRegistry.registerAbility(new Lightning());
        SurgeRegistry.registerAbility(new Teleport());
        SurgeDropsHandler.registerDrop(EntityBlaze.class, "firecharge", 0.05f);
        SurgeDropsHandler.registerDrop(EntityWither.class, "flight", 0.23f);
        SurgeDropsHandler.registerDrop(EntityGhast.class, "invisibility", 0.12f);
        SurgeDropsHandler.registerDrop(EntityPigZombie.class, "jump", 0.08f);
        SurgeDropsHandler.registerDrop(EntityGhast.class, "leap", 0.09f);
        SurgeDropsHandler.registerDrop(SurgeDropsHandler.getIdentifierClassForChargedCreeper(), "lightning", 0.3f);
        SurgeDropsHandler.registerDrop(SurgeDropsHandler.getIdentifierClassForWitherSkeleton(), "powerstrike", 0.2f);
        SurgeDropsHandler.registerDrop(EntityWither.class, "quickstrike", 0.17f);
        SurgeDropsHandler.registerDrop(EntityEnderman.class, "teleport", 0.04f);
    }
}
