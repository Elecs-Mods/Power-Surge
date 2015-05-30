package elec332.powersurge.api;

import elec332.powersurge.main.PowerSurge;
import elec332.powersurge.network.PacketNetworkAbility;
import elec332.powersurge.surge.SurgeData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

/**
 * Created by Elec332 on 18-3-2015.
 */
public abstract class NetworkAbility extends AbstractAbility {

    @Override
    public void onActivated(EntityPlayerMP player) {
        PowerSurge.networkHandler.getNetworkWrapper().sendTo(new PacketNetworkAbility(this.getName(), true), player);
        onActivated((EntityPlayer) player);
    }

    @Override
    public void onDeActivated(EntityPlayerMP player) {
        PowerSurge.networkHandler.getNetworkWrapper().sendTo(new PacketNetworkAbility(this.getName(), false), player);
        onDeActivated((EntityPlayer) player);
    }

    public abstract void onActivated(EntityPlayer player);

    public abstract void onDeActivated(EntityPlayer player);

    public void deActivateAbility(EntityPlayer player){
        if (player instanceof EntityPlayerMP)
            SurgeData.get(player).deActivateAbility();
    }
}
