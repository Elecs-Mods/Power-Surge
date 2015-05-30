package elec332.powersurge.abilities;

import elec332.powersurge.api.IAbility;
import elec332.powersurge.surge.SurgeData;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

/**
 * Created by Elec332 on 16-3-2015.
 */
public class Invisibility implements IAbility {
    @Override
    public String getName() {
        return "invisibility";
    }

    @Override
    public int getCost() {
        return 2000;
    }

    @Override
    public int getCoolDownTime() {
        return 0;
    }

    @Override
    public void onActivated(EntityPlayerMP player) {
        player.addPotionEffect(new PotionEffect(Potion.invisibility.getId(), 200, 5));
        SurgeData.get(player).deActivateAbility();
    }

    @Override
    public void onDeActivated(EntityPlayerMP player) {
    }
}
