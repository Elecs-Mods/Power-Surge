package elec332.powersurge.abilities;

import com.google.common.collect.Lists;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import elec332.core.player.PlayerHelper;
import elec332.powersurge.api.IEventHandler;
import elec332.powersurge.api.NetworkAbility;
import elec332.powersurge.surge.AbilityHelper;
import net.minecraft.entity.player.EntityPlayer;

import java.util.List;
import java.util.UUID;

/**
 * Created by Elec332 on 31-5-2015.
 */
public class Climb extends NetworkAbility implements IEventHandler{

    @Override
    public String getName() {
        return "climb";
    }

    @Override
    public int getBaseCost() {
        return 6;
    }

    @Override
    public void onActivated(EntityPlayer player) {
       // players.add(PlayerHelper.getPlayerUUID(player));
    }

    @Override
    public void onDeActivated(EntityPlayer player) {
        //players.remove(PlayerHelper.getPlayerUUID(player));
    }

   // private List<UUID> players = Lists.newArrayList();

    @SubscribeEvent
    public void makeClimb(TickEvent.PlayerTickEvent event){
        if (AbilityHelper.doesPlayerHaveAbility(event.player, this) && event.player.isCollidedHorizontally){
            event.player.motionY = 0.1176D;
            event.player.fallDistance = 0.0f;
        }
    }

}
