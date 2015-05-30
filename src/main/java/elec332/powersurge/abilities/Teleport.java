package elec332.powersurge.abilities;

import elec332.powersurge.api.AbstractAbility;
import elec332.powersurge.api.IAbility;
import elec332.powersurge.surge.SurgeData;
import net.minecraft.entity.item.EntityEnderPearl;
import net.minecraft.entity.player.EntityPlayerMP;

/**
 * Created by Elec332 on 20-3-2015.
 */
public class Teleport extends AbstractAbility {

    @Override
    public String getName() {
        return "teleport";
    }

    @Override
    public int getBaseCost() {
        return 1500;
    }

    @Override
    public void onActivated(EntityPlayerMP player) {
        EntityEnderPearl pearl = new EntityEnderPearl(player.worldObj, player);
        ++pearl.posY;
        pearl.addVelocity(-0.1, -0.1, -0.1);
        player.worldObj.spawnEntityInWorld(pearl);
        SurgeData.get(player).deActivateAbility();
    }

    @Override
    public void onDeActivated(EntityPlayerMP player) {
    }

}
