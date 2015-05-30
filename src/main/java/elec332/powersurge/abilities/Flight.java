package elec332.powersurge.abilities;

import elec332.core.player.PlayerHelper;
import elec332.powersurge.api.AbstractAbility;
import net.minecraft.entity.player.EntityPlayerMP;

/**
 * Created by Elec332 on 17-3-2015.
 */
public class Flight extends AbstractAbility {

    @Override
    public String getName() {
        return "flight";
    }

    @Override
    public int getBaseCost() {
        return 9;
    }

    @Override
    public void onActivated(EntityPlayerMP player) {
        PlayerHelper.activateFlight(player);
    }

    @Override
    public void onDeActivated(EntityPlayerMP player) {
        PlayerHelper.deactivateFlight(player);
    }

}
