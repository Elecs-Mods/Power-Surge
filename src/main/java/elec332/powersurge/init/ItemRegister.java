package elec332.powersurge.init;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import elec332.powersurge.items.AbilityItems;

/**
 * Created by Elec332 on 24-2-2015.
 */
public class ItemRegister {
    public static final ItemRegister instance = new ItemRegister();

    public void preInit(FMLPreInitializationEvent event){
        //Early item registering
    }

    public void init(FMLInitializationEvent event){
        //Normal item registering
    }
}
