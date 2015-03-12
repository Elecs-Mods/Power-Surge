package elec332.powersurge.surge;

import elec332.powersurge.main.PowerSurge;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

/**
 * Created by Elec332 on 12-3-2015.
 */
public class SurgeData implements IExtendedEntityProperties{

    Entity entity;
    int charge;

    public static SurgeData get(Entity player){
        return (SurgeData) player.getExtendedProperties(PowerSurge.ModID);
    }

    public void tick(){
        if (this.charge > 0)
            this.charge--;
    }

    public int getCharge() {
        return charge;
    }

    @Override
    public void saveNBTData(NBTTagCompound compound) {
        compound.setInteger("Charge", this.charge);
    }

    @Override
    public void loadNBTData(NBTTagCompound compound) {
        if (compound != null){
            this.charge = compound.getInteger("Charge");
        }
    }

    @Override
    public void init(Entity entity, World world) {
        this.entity = entity;
        this.charge = 1;
    }
}
