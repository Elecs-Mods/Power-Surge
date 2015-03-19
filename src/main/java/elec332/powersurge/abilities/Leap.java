package elec332.powersurge.abilities;

import elec332.powersurge.api.IAbility;
import elec332.powersurge.api.NetworkAbility;
import elec332.powersurge.main.PowerSurge;
import elec332.powersurge.network.PacketNetworkAbility;
import elec332.powersurge.surge.SurgeData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.MathHelper;

/**
 * Created by Elec332 on 18-3-2015.
 */
public class Leap extends NetworkAbility {
    @Override
    public String getName() {
        return "leap";
    }

    @Override
    public int getCost() {
        return 400;
    }

    @Override
    public void onActivated(EntityPlayer player) {
        player.setSprinting(true);
        player.motionY += 0.56f;
        float speed = 0.925f;
        player.motionX = (double) (-MathHelper.sin(player.rotationYaw / 180.0F * (float) Math.PI) * MathHelper.cos(player.rotationPitch / 180.0F * (float) Math.PI) * speed);
        player.motionZ = (double) (MathHelper.cos(player.rotationYaw / 180.0F * (float) Math.PI) * MathHelper.cos(player.rotationPitch / 180.0F * (float) Math.PI) * speed);
        deActivateAbility(player);
    }

    @Override
    public void onDeActivated(EntityPlayer player) {
    }
}
