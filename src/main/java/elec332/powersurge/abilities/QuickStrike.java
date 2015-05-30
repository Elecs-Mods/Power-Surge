package elec332.powersurge.abilities;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import elec332.powersurge.api.IAbility;
import elec332.powersurge.api.IEventHandler;
import elec332.powersurge.surge.AbilityHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

/**
 * Created by Elec332 on 19-3-2015.
 */
public class QuickStrike implements IAbility, IEventHandler{
    @Override
    public String getName() {
        return "quickstrike";
    }

    @Override
    public int getCost() {
        return 8;
    }

    @Override
    public int getCoolDownTime() {
        return 0;
    }

    @Override
    public void onActivated(EntityPlayerMP player) {
    }

    @Override
    public void onDeActivated(EntityPlayerMP player) {
    }

    @SubscribeEvent
    public void onHit(LivingHurtEvent event) {
        if (event.source.getEntity() instanceof EntityPlayer && AbilityHelper.doesPlayerHaveAbility((EntityPlayerMP) event.source.getEntity(), this)) {
            if (event.entityLiving.hurtResistantTime > 15)
                event.entityLiving.hurtResistantTime -= 13;
        }
    }
}
