package elec332.powersurge.api;

/**
 * Created by Elec332 on 30-5-2015.
 */
public class PowerSurgeAPIProps {
    public static final String owner = "PowerSurge";
    public static final String APIName = "PowerSurge-API";
    public static final String APIVersion = "1.0.2";

    public static void registerAbility(IAbility ability){
        try {
            Class.forName("elec332.powersurge.surge.SurgeRegistry").getDeclaredMethod("registerAbility", IAbility.class).invoke(null, ability);
        } catch (Exception e){
            System.out.println("[PowerSurge-API] Failed to register ability "+ability.getName()+", the PowerSurge mod probably isn't loaded.");
        }
    }
}