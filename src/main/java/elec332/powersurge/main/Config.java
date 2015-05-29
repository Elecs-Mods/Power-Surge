package elec332.powersurge.main;

import elec332.core.config.Configurable;

/**
 * Created by Elec332 on 29-5-2015.
 */
public class Config {
    protected Config(){
    }

    @Configurable(comment = "Sets the max amount of surge a player can have")
    public static int max_Charge = 20000;

    @Configurable(comment = "Sets how much surge the player will get from a mob, so if a mob has 6HP, and this factor is 50, the player will get 300 surge")
    public static int surge_health_factor = 100;
}
