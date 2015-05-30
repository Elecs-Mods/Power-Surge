package elec332.powersurge.api;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.common.config.Configuration;

/**
 * Created by Elec332 on 30-5-2015.
 */
public abstract class AbstractAbility implements IAbility {

    public AbstractAbility(){
        try {
            surgeConfig = (Configuration) Class.forName("elec332.powersurge.main.PowerSurge").getField("surgeConfig").get(null);
        } catch (Exception e){
            PowerSurgeAPI.printInfo("Could not find PowerSurge surgeConfig, PowerSurge mod probably isn't loaded");
        }
    }

    private Configuration surgeConfig;

    /**
     * @return Name of this ability
     */
    @Override
    public abstract String getName();

    /**
     * When this ability is registered, and this returns false, the ability will not exist ingame, use this for eg disabling via configs.
     *
     * @return If the ability should actually be registered
     */
    @Override
    public boolean enabled() {
        return surgeConfig == null? defaultEnabled() : surgeConfig.getBoolean("enabled", getName(), defaultEnabled(), "");
    }

    public boolean defaultEnabled(){
        return true;
    }

    /**
     * @return the surge cost per tick
     */
    @Override
    public int getCost() {
        return surgeConfig == null? getBaseCost() : surgeConfig.getInt("SurgeCost", getName(), getBaseCost(), 1, 10000, "");
    }

    public abstract int getBaseCost();

    @Override
    public int getCoolDownTime(){
        return 0;
    }

    /**
     * This gets run whenever a player activated this ability
     *
     * @param player The player which activated the ability
     */
    @Override
    public abstract void onActivated(EntityPlayerMP player);

    /**
     * This gets run whenever a player deactivates this ability
     *
     * @param player The player which activated the ability
     */
    @Override
    public abstract void onDeActivated(EntityPlayerMP player);
}
