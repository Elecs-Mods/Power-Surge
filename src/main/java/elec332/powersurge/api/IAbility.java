package elec332.powersurge.api;

import net.minecraft.entity.player.EntityPlayerMP;

/**
 * Created by Elec332 on 14-3-2015.
 */
public interface IAbility {

    /**
     *
     * @return Name of this ability
     */
    public abstract String getName();

    /**
     * When this ability is registered, and this returns false, the ability will not exist ingame, use this for eg disabling via configs.
     *
     * @return If the ability should actually be registered
     */
    public abstract boolean enabled();

    /**
     *
     * @return the surge cost per tick
     */
    public abstract int getCost();

    /**
     *
     * @return time needed (in ticks) before any ability can be used after activating this ability
     */
    public abstract int getCoolDownTime();

    /**
     * This gets run whenever a player activated this ability
     *
     * @param player The player which activated the ability
     */
    public abstract void onActivated(EntityPlayerMP player);

    /**
     * This gets run whenever a player deactivates this ability
     *
     * @param player The player which activated the ability
     */
    public abstract void onDeActivated(EntityPlayerMP player);

}
