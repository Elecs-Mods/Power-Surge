package elec332.powersurge.surge;

import elec332.powersurge.api.IAbility;
import elec332.powersurge.items.AbilityItems;
import net.minecraft.crash.CrashReport;
import net.minecraft.util.ReportedException;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Elec332 on 14-3-2015.
 */
public class SurgeRegistry {

    private static HashMap<String, IAbility> registry = new HashMap<String, IAbility>();

    public static void registerAbility(IAbility ability){
        if (ability.getName().equals(ability.getName().toLowerCase())) {
            registry.put(ability.getName(), ability);
            new AbilityItems(ability.getName());
        } else {
            CrashReport report = new CrashReport("Registering Ability", new Exception());
            report.makeCategory("Encountered an error white trying to register ability: "+ability.getName()+"  The ability name isn't lowercase!");
            throw new ReportedException(report);
        }
    }

    public static IAbility getAbilityForName(String i){
        return registry.get(i);
    }

    public static ArrayList<String> abilityArrayToString(ArrayList<IAbility> abilities){
        ArrayList<String> ret = new ArrayList<String>();
        for (IAbility ability : abilities){
            ret.add(ability.getName());
        }
        return ret;
    }
}
