package elec332.powersurge.abilities;

import elec332.powersurge.api.IAbility;
import elec332.powersurge.surge.SurgeData;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

/**
 * Created by Elec332 on 14-3-2015.
 */
public class Jump implements IAbility {
    @Override
    public String getName() {
        return "jump";
    }

    @Override
    public int getCost() {
        return 200;
    }

    @Override
    public int getCoolDownTime() {
        return 0;
    }

    @Override
    public void onActivated(EntityPlayerMP player) {
        player.addPotionEffect(new PotionEffect(Potion.jump.getId(), 400, 5));
        SurgeData.get(player).deActivateAbility();
    }

    @Override
    public void onDeActivated(EntityPlayerMP player) {
        //Nope
    }
}
