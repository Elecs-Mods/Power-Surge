package elec332.powersurge.surge;

import cpw.mods.fml.common.FMLCommonHandler;
import elec332.powersurge.api.IAbility;
import elec332.powersurge.api.IEventHandler;
import elec332.powersurge.api.NetworkAbility;
import elec332.powersurge.items.AbilityItems;
import net.minecraft.crash.CrashReport;
import net.minecraft.item.Item;
import net.minecraft.util.ReportedException;
import net.minecraftforge.common.MinecraftForge;

import java.util.HashMap;

/**
 * Created by Elec332 on 14-3-2015.
 */
public class SurgeRegistry {

    private static HashMap<String, IAbility> registry = new HashMap<String, IAbility>();
    private static HashMap<String, NetworkAbility> networkRegistry = new HashMap<String, NetworkAbility>();
    private static HashMap<String, Item> itemRegistry = new HashMap<String, Item>();

    public static void registerAbility(IAbility ability){
        String abilityName = ability.getName();
        if (ability.enabled() && abilityName.equals(abilityName.toLowerCase()) || abilityName.contains(" ")) {
            registry.put(abilityName, ability);
            itemRegistry.put(abilityName, new AbilityItems(abilityName));
        } else if (ability.enabled()){
            CrashReport report = new CrashReport("Registering Ability", new Exception());
            report.makeCategory("Encountered an error white trying to register ability: "+abilityName+"  The ability name isn't lowercase!");
            throw new ReportedException(report);
        }
        if (ability instanceof IEventHandler && ability.enabled()){
            MinecraftForge.EVENT_BUS.register(ability);
            FMLCommonHandler.instance().bus().register(ability);
        }
        if (ability instanceof NetworkAbility && ability.enabled()){
            networkRegistry.put(abilityName, (NetworkAbility) ability);
        }
    }

    public static IAbility getAbilityForName(String i){
        return registry.get(i);
    }

    public static NetworkAbility getNetworkAbilityForName(String s){
        return networkRegistry.get(s);
    }

    public static Item getItemForAbility(String s){
        return itemRegistry.get(s);
    }

    /*
    public static ArrayList<String> abilityArrayToString(ArrayList<IAbility> abilities){
        ArrayList<String> ret = new ArrayList<String>();
        for (IAbility ability : abilities){
            ret.add(ability.getName());
        }
        return ret;
    }*/
}
