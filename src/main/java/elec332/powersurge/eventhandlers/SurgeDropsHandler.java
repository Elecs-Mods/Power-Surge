package elec332.powersurge.eventhandlers;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import elec332.powersurge.surge.SurgeRegistry;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDropsEvent;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by Elec332 on 30-5-2015.
 */
public class SurgeDropsHandler {

    private static Random random = new Random();
    private static HashMap<Class, HashMap<String, Float>> dropRegistry = new HashMap<Class, HashMap<String, Float>>();

    @SubscribeEvent
    public void onEntityItemsDropped(LivingDropsEvent event){
        if (!event.drops.isEmpty()) {
            Class clazz = event.entityLiving.getClass();
            if (event.entityLiving instanceof EntityCreeper && event.entityLiving.getDataWatcher().getWatchableObjectByte(17) == 1){
                clazz = ChargedCreeper.class;
            } else if (event.entityLiving instanceof EntitySkeleton && event.entityLiving.getDataWatcher().getWatchableObjectByte(13) == 1){
                clazz = WitherSkeleton.class;
            }
            if (dropRegistry.get(clazz) != null){
                for (Map.Entry<String, Float> entry: dropRegistry.get(clazz).entrySet()){
                    if (random.nextFloat() < entry.getValue()){
                        addAbilityItemToList(entry.getKey(), event.drops);
                    }
                }
            }
        }
    }

    public static void registerDrop(Class clazz, String ability, float chance){
        if (dropRegistry.get(clazz) == null){
            dropRegistry.put(clazz, new HashMap<String, Float>());
        }
        dropRegistry.get(clazz).put(ability, chance);
    }

    private void addAbilityItemToList(String s, List<EntityItem> drops){
        if (SurgeRegistry.getItemForAbility(s) != null)
            addStackToList(new ItemStack(SurgeRegistry.getItemForAbility(s)), drops);
    }

    private void addStackToList(ItemStack stack, List<EntityItem> drops){
        EntityItem data = drops.get(0);
        EntityItem toAdd = new EntityItem(data.worldObj, data.posX, data.posY, data.posZ, stack);
        drops.add(toAdd);
    }

    public static Class getIdentifierClassForChargedCreeper(){
        return ChargedCreeper.class;
    }

    public static Class getIdentifierClassForWitherSkeleton(){
        return WitherSkeleton.class;
    }

    private static class ChargedCreeper{
    }

    private static class WitherSkeleton{
    }
}
