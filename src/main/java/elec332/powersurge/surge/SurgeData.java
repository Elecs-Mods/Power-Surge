package elec332.powersurge.surge;

import elec332.powersurge.main.PowerSurge;
import elec332.powersurge.network.PacketSetSurgeData;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

/**
 * Created by Elec332 on 12-3-2015.
 */
public class SurgeData implements IExtendedEntityProperties{

    Entity entity;
    int charge;

    private int tickCounter = 0;

    public SurgeData(){
        this.charge = 0;
    }

    public static SurgeData get(EntityLivingBase player){
        return (SurgeData) player.getExtendedProperties(PowerSurge.ModID);
    }

    public void tick(){
        if (this.tickCounter >= 10) {
            if (this.charge > 10)
                this.addCharge(-10);
            else if (this.charge != 0)
                this.setCharge(0);
            this.tickCounter = 0;
        }
        this.tickCounter++;
    }

    public int getCharge() {
        return this.charge;
    }

    public void setCharge(int charge) {
        if (charge <= PowerSurge.max_Charge)
            this.charge = charge;
        else
            this.charge = PowerSurge.max_Charge;
        this.sendData();
    }

    public void addCharge(int toAdd){
        int newCharge  = this.charge+toAdd;
        this.setCharge(newCharge);
    }

    private void sendData(){
        if (entity instanceof EntityPlayerMP) {
            NBTTagCompound nbt = new NBTTagCompound();
            nbt.setIntArray("data", new int[]{this.getCharge()});
            PowerSurge.networkHandler.getNetworkWrapper().sendTo(new PacketSetSurgeData(nbt), (EntityPlayerMP) entity);
        }
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
    }
}
