package elec332.powersurge.api;

import net.minecraft.entity.player.EntityPlayerMP;

/**
 * Created by Elec332 on 14-3-2015.
 */
public interface IAbility {

    public abstract String getName();

    public abstract int getCost();

    public abstract int getCoolDownTime();

    public abstract void onActivated(EntityPlayerMP player);

    public abstract void onDeActivated(EntityPlayerMP player);

}
