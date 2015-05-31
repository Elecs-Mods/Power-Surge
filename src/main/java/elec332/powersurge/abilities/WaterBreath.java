package elec332.powersurge.abilities;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import elec332.powersurge.api.AbstractAbility;
import elec332.powersurge.api.IEventHandler;
import elec332.powersurge.surge.AbilityHelper;
import net.minecraft.entity.player.EntityPlayerMP;

/**
 * Created by Elec332 on 31-5-2015.
 */
public class WaterBreath extends AbstractAbility implements IEventHandler {

    @Override
    public String getName() {
        return "waterbreath";
    }

    @Override
    public int getBaseCost() {
        return 5;
    }

    @Override
    public void onActivated(EntityPlayerMP player) {
    }

    @Override
    public void onDeActivated(EntityPlayerMP player) {
    }

    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent event){
        if (event.player instanceof EntityPlayerMP && AbilityHelper.doesPlayerHaveAbility(event.player, this) && event.player.getAir() <= 300){
            event.player.setAir(300);
        }
    }
}
