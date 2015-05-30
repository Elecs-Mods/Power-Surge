package elec332.powersurge.abilities;

import elec332.powersurge.api.AbstractAbility;
import elec332.powersurge.surge.SurgeData;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.util.Vec3;

/**
 * Created by Elec332 on 17-3-2015.
 */
public class FireCharge extends AbstractAbility {

    @Override
    public String getName() {
        return "firecharge";
    }

    @Override
    public int getBaseCost() {
        return 300;
    }

    @Override
    public int getCoolDownTime() {
        return 20;
    }

    @Override
    public void onActivated(EntityPlayerMP player) {
        Vec3 lookVec = player.getLookVec();
        EntityLargeFireball charge = new EntityLargeFireball(player.getServerForPlayer(), player, lookVec.xCoord * 20.0D, lookVec.yCoord * 20.0D, lookVec.zCoord * 20.0D);
        ++charge.posY;
        charge.field_92057_e = 1;
        player.worldObj.spawnEntityInWorld(charge);
        SurgeData.get(player).deActivateAbility();
    }

    @Override
    public void onDeActivated(EntityPlayerMP player) {
    }

}
