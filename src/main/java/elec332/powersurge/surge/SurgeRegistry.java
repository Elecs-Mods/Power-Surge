package elec332.powersurge.surge;

import elec332.powersurge.api.IAbility;

import java.util.HashMap;

/**
 * Created by Elec332 on 14-3-2015.
 */
public class SurgeRegistry {

    private static HashMap<String, IAbility> registry = new HashMap<String, IAbility>();

    public static void registerAbility(IAbility ability){
        registry.put(ability.getName(), ability);
    }

    public static IAbility getAbilityForName(String i){
        return registry.get(i);
    }
}
