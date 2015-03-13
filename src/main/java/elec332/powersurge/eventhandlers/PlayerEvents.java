package elec332.powersurge.eventhandlers;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import elec332.powersurge.main.PowerSurge;
import elec332.powersurge.network.PacketSetSurgeData;
import elec332.powersurge.surge.SurgeData;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.living.LivingEvent;

/**
 * Created by Elec332 on 13-3-2015.
 */
public class PlayerEvents {

    @SubscribeEvent
    public void onPlayerTick(LivingEvent.LivingUpdateEvent event){
        if (event.entity instanceof EntityPlayerMP){
            EntityPlayerMP playerMP = (EntityPlayerMP) event.entity;
            SurgeData data = SurgeData.get(playerMP);
            if (data.getOldCharge() != data.getCharge())
                PowerSurge.networkHandler.getNetworkWrapper().sendTo(new PacketSetSurgeData(data.getCharge()), playerMP);
        }
    }

    @SubscribeEvent
    public void onPlayerConstructing(EntityEvent.EntityConstructing event){
        event.entity.registerExtendedProperties(PowerSurge.ModID, new SurgeData());
    }
}
