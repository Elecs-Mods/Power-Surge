package elec332.powersurge.abilities;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import elec332.powersurge.api.IEventHandler;
import elec332.powersurge.api.NetworkAbility;
import elec332.powersurge.surge.AbilityHelper;
import net.minecraft.entity.player.EntityPlayer;

/**
 * Created by Elec332 on 31-5-2015.
 */
public class QuickSwimming extends NetworkAbility implements IEventHandler{

    @Override
    public String getName() {
        return "quickswim";
    }

    @Override
    public int getBaseCost() {
        return 6;
    }

    @Override
    public void onActivated(EntityPlayer player) {
    }

    @Override
    public void onDeActivated(EntityPlayer player) {
    }

    @SubscribeEvent
    public void onTick(TickEvent.PlayerTickEvent event){
        if (AbilityHelper.doesPlayerHaveAbility(event.player, this) && event.player.isInWater()){
            event.player.motionX = event.player.motionX *1.08;
            event.player.motionZ = event.player.motionZ *1.08;
        }
    }

}
