package elec332.powersurge.surge;

import elec332.powersurge.api.IAbility;
import net.minecraft.entity.player.EntityPlayer;

/**
 * Created by Elec332 on 20-3-2015.
 */
public class AbilityHelper {

    public static boolean doesPlayerHaveAbility(EntityPlayer player, IAbility ability){
        return areAbilitiesEqual(SurgeData.get(player).getSelectedAbility(), ability) && SurgeData.get(player).isAbilityActive();
    }

    public static boolean areAbilitiesEqual(IAbility ability1, IAbility ability2){
        return !(ability1 == null || ability2 == null) && ability1.getName().equals(ability2.getName());
    }
}
