package elec332.powersurge.abilities;

import elec332.core.player.PlayerHelper;
import elec332.powersurge.api.IAbility;
import elec332.powersurge.surge.SurgeData;
import net.minecraft.entity.item.EntityEnderPearl;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;

/**
 * Created by Elec332 on 20-3-2015.
 */
public class Teleport implements IAbility {
    @Override
    public String getName() {
        return "teleport";
    }

    @Override
    public int getCost() {
        return 1500;
    }

    @Override
    public void onActivated(EntityPlayerMP player) {
        MovingObjectPosition position = PlayerHelper.getPosPlayerIsLookingAt(player, 20.0);
        Vec3 lookVec = player.getLookVec();
        EntityEnderPearl pearl = new EntityEnderPearl(player.worldObj, player);
        ++pearl.posY;
        player.worldObj.spawnEntityInWorld(pearl);
        SurgeData.get(player).deActivateAbility();

    }

    @Override
    public void onDeActivated(EntityPlayerMP player) {

    }
}
