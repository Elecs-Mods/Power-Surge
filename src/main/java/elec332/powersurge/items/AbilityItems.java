package elec332.powersurge.items;

import elec332.core.baseclasses.item.BaseItem;
import elec332.core.main.ElecCTab;
import elec332.powersurge.main.PowerSurge;
import elec332.powersurge.surge.SurgeData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * Created by Elec332 on 15-3-2015.
 */
public class AbilityItems extends BaseItem {
    public AbilityItems(String abilityName) {
        super(abilityName, ElecCTab.ElecTab, PowerSurge.ModID);
        setTextureName(PowerSurge.ModID+":vial");
        setMaxStackSize(1);
        this.name = abilityName;
    }

    String name;

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        SurgeData.get(player).addAbility(name);
        return player.inventory.decrStackSize(player.inventory.currentItem, 1);
    }
}
