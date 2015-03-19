package elec332.powersurge.abilities;

import elec332.powersurge.api.IAbility;
import net.minecraft.entity.player.EntityPlayerMP;

/**
 * Created by Elec332 on 17-3-2015.
 */
public class Flight implements IAbility{
    @Override
    public String getName() {
        return "flight";
    }

    @Override
    public int getCost() {
        return 9;
    }

    @Override
    public void onActivated(EntityPlayerMP player) {
        player.capabilities.allowFlying = true;
        player.sendPlayerAbilities();
    }

    @Override
    public void onDeActivated(EntityPlayerMP player) {
        player.capabilities.allowFlying = false;
        if (player.capabilities.isFlying)
            player.capabilities.isFlying = false;
        player.sendPlayerAbilities();
    }
}
