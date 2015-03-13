package elec332.powersurge.surge;

import elec332.powersurge.main.PowerSurge;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

/**
 * Created by Elec332 on 12-3-2015.
 */
public class SurgeData implements IExtendedEntityProperties{

    Entity entity;
    int charge;
    int oldCharge;

    public SurgeData(){
        this.charge = 0;
        this.oldCharge = 0;
    }

    public static SurgeData get(EntityLivingBase player){
        return (SurgeData) player.getExtendedProperties(PowerSurge.ModID);
    }

    public void tick(){
        this.oldCharge = charge;
        if (this.charge > 0)
            this.charge--;
    }

    public int getCharge() {
        return charge;
    }

    public void setCharge(int charge) {
        this.oldCharge = this.charge;
        this.charge = charge;
    }

    public int getOldCharge() {
        return oldCharge;
    }

    @Override
    public void saveNBTData(NBTTagCompound compound) {
        compound.setInteger("Charge", this.charge);
    }

    @Override
    public void loadNBTData(NBTTagCompound compound) {
        if (compound != null){
            int c = compound.getInteger("Charge");
            this.charge = c;
            this.oldCharge = c;
        }
    }

    @Override
    public void init(Entity entity, World world) {
        this.entity = entity;
    }
}
