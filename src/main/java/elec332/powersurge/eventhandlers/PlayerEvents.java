package elec332.powersurge.eventhandlers;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import elec332.powersurge.main.Config;
import elec332.powersurge.main.PowerSurge;
import elec332.powersurge.network.PacketSetSurgeData;
import elec332.powersurge.surge.SurgeData;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.entity.EntityEvent;

/**
 * Created by Elec332 on 13-3-2015.
 */
public class PlayerEvents {

    @SubscribeEvent
    public void onPlayerConstructing(EntityEvent.EntityConstructing event){
        if (event.entity instanceof EntityPlayerMP || event.entity instanceof EntityClientPlayerMP)
            event.entity.registerExtendedProperties(PowerSurge.ModID, new SurgeData());
    }

    @SubscribeEvent
    public void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event){
        if (event.player instanceof EntityPlayerMP) {
            SurgeData data = SurgeData.get(event.player);
            NBTTagCompound nbt = new NBTTagCompound();
            nbt.setIntArray("data", new int[]{data.getCharge(), Config.max_Charge});
            PowerSurge.networkHandler.getNetworkWrapper().sendTo(new PacketSetSurgeData(nbt), (EntityPlayerMP) event.player);
            data.syncFully();
            if (data.getSelectedAbility() != null && data.isAbilityActive()){
                data.activateAbility();
            }
        }
    }
}
