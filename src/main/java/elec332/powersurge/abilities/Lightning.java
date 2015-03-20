package elec332.powersurge.abilities;

import elec332.core.player.PlayerHelper;
import elec332.core.world.WorldHelper;
import elec332.powersurge.api.NetworkAbility;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MovingObjectPosition;

/**
 * Created by Elec332 on 20-3-2015.
 */
public class Lightning extends NetworkAbility {
    @Override
    public String getName() {
        return "lightning";
    }

    @Override
    public int getCost() {
        return 1200;
    }

    @Override
    public void onActivated(EntityPlayer player) {
        MovingObjectPosition position = PlayerHelper.getPosPlayerIsLookingAt(player, 64D);
        WorldHelper.spawnLightningAt(player.worldObj, position.blockX, position.blockY, position.blockZ);
        deActivateAbility(player);
    }

    @Override
    public void onDeActivated(EntityPlayer player) {

    }
}
