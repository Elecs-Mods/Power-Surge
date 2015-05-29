package elec332.powersurge.eventhandlers;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import elec332.powersurge.main.Config;
import elec332.powersurge.surge.SurgeData;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent;

/**
 * Created by Elec332 on 13-3-2015.
 */
public class SurgeHandler {

    @SubscribeEvent
    public void onEntityDead(LivingDeathEvent event){
        if (event.source.getEntity() instanceof EntityPlayerMP){
            EntityPlayerMP player = (EntityPlayerMP) event.source.getEntity();
            SurgeData data = SurgeData.get(player);
            data.addCharge((int) event.entityLiving.getMaxHealth()* Config.surge_health_factor);
        }
    }

    @SubscribeEvent
    public void onPlayerTick(LivingEvent.LivingUpdateEvent event){
        if (event.entity instanceof EntityPlayerMP){
            EntityPlayerMP player = (EntityPlayerMP) event.entity;
            SurgeData data = SurgeData.get(player);
            data.tick();
        }
    }
}
