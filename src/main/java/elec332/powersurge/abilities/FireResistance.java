package elec332.powersurge.abilities;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import elec332.powersurge.api.AbstractAbility;
import elec332.powersurge.api.IEventHandler;
import elec332.powersurge.surge.AbilityHelper;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

/**
 * Created by Elec332 on 31-5-2015.
 */
public class FireResistance extends AbstractAbility implements IEventHandler{

    @Override
    public String getName() {
        return "fireresistance";
    }

    @Override
    public int getBaseCost() {
        return 8;
    }

    @Override
    public void onActivated(EntityPlayerMP player) {
    }

    @Override
    public void onDeActivated(EntityPlayerMP player) {
    }

    @SubscribeEvent
    public void onLivingHurt(LivingHurtEvent event){
        if (event.entityLiving instanceof EntityPlayerMP && (event.source == DamageSource.onFire || event.source == DamageSource.inFire) && AbilityHelper.doesPlayerHaveAbility((EntityPlayerMP) event.entityLiving, this))
            event.setCanceled(true);
    }
}
