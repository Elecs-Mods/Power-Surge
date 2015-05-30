package elec332.powersurge.abilities;

import elec332.powersurge.api.AbstractAbility;
import elec332.powersurge.api.IAbility;
import elec332.powersurge.surge.SurgeData;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

/**
 * Created by Elec332 on 17-3-2015.
 */
public class PowerStrike extends AbstractAbility {

    @Override
    public String getName() {
        return "powerstrike";
    }

    @Override
    public int getBaseCost() {
        return 500;
    }

    @Override
    public void onActivated(EntityPlayerMP player) {
        player.addPotionEffect(new PotionEffect(Potion.damageBoost.getId(), 400, 99));
        SurgeData.get(player).deActivateAbility();
    }

    @Override
    public void onDeActivated(EntityPlayerMP player) {
    }

}
