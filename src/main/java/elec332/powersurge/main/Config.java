package elec332.powersurge.main;

import elec332.core.config.Configurable;

/**
 * Created by Elec332 on 29-5-2015.
 */
public class Config {
    protected Config(){
    }

    @Configurable(comment = "Sets the max amount of surge a player can have", minValue = 20, maxValue = 100000)
    public static int max_Charge = 20000;

    @Configurable(comment = "Sets how much surge the player will get from a mob, so if a mob has 6HP, and this factor is 50, the player will get 300 surge", minValue = 1, maxValue = 1000)
    public static int surge_health_factor = 60;

    @Configurable(comment = "Sets how much surge the player loses every second if there is no active ability", minValue = 0, maxValue = 100)
    public static int tick_decrease_factor = 20;
}
