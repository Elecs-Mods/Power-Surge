package elec332.powersurge.surge;

import elec332.powersurge.api.IAbility;
import elec332.powersurge.lib.EnumKeyType;
import elec332.powersurge.main.PowerSurge;
import elec332.powersurge.network.PacketCompleteSync;
import elec332.powersurge.network.PacketSetSurgeData;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

import java.util.ArrayList;

/**
 * Created by Elec332 on 12-3-2015.
 */
public class SurgeData implements IExtendedEntityProperties{

    Entity entity;
    int charge;
    ArrayList<IAbility> abilities;
    IAbility selectedAbility;
    boolean abilityActive;

    private int tickCounter = 0;

    public SurgeData(){
        this.charge = 0;
        this.abilities = new ArrayList<IAbility>();
        this.abilityActive = false;
    }

    public static SurgeData get(EntityLivingBase player){
        return (SurgeData) player.getExtendedProperties(PowerSurge.ModID);
    }

    public void tick(){
        if (this.tickCounter >= 10) {
            if (abilityActive && this.selectedAbility != null && this.charge >= this.selectedAbility.getCost() * 10)
                this.addCharge(-(selectedAbility.getCost() * 10));
            else if (abilityActive && this.selectedAbility != null)
                deActivateAbility();
            else if (this.charge > 10)
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
        this.syncSurge();
    }

    public void addCharge(int toAdd){
        int newCharge  = this.charge+toAdd;
        this.setCharge(newCharge);
    }

    public void addAbility(String i){
        IAbility toAdd = SurgeRegistry.getAbilityForName(i);
        if (abilities.size() == 0)
            selectedAbility = toAdd;
        if (!abilities.contains(toAdd))
            abilities.add(toAdd);
    }

    public ArrayList<IAbility> getAbilities(){
        return this.abilities;
    }

    public IAbility getSelectedAbility(){
        return this.selectedAbility;
    }

    public boolean isAbilityActive(){
        return this.abilityActive;
    }

    public void activateAbility(){
        if (this.charge >= selectedAbility.getCost()) {
            this.abilityActive = true;
            selectedAbility.onActivated((EntityPlayerMP) entity);
            addCharge(-selectedAbility.getCost());
        }
    }

    public void deActivateAbility(){
        this.abilityActive = false;
        selectedAbility.onDeActivated((EntityPlayerMP) entity);
    }

    public void pressKey(EnumKeyType type){
        if (selectedAbility != null) {
            if (type == EnumKeyType.ACTIVATE) {
                if (!abilityActive)
                    activateAbility();
                else
                    deActivateAbility();
            } else if (type == EnumKeyType.NEXT && !abilityActive) {
                int i = abilities.indexOf(selectedAbility);
                if (i != (abilities.size()-1)){
                    this.selectedAbility = abilities.get(i+1);
                } else {
                    this.selectedAbility = abilities.get(0);
                }
            } else if (type == EnumKeyType.PREVIOUS && !abilityActive) {
                int i = abilities.indexOf(selectedAbility);
                if (i != 0){
                    this.selectedAbility = abilities.get(i-1);
                } else {
                    this.selectedAbility = abilities.get(abilities.size()-1);
                }
            }
            this.syncFully();
        }
    }

    @Override
    public void saveNBTData(NBTTagCompound compound) {
        compound.setInteger("Charge", this.charge);
        NBTTagList list = new NBTTagList();
        for (IAbility ability : this.abilities){
            NBTTagCompound nbtTagCompound = new NBTTagCompound();
            nbtTagCompound.setString("name", ability.getName());
            list.appendTag(nbtTagCompound);
        }
        compound.setTag("abilities_S", list);
        if (this.selectedAbility != null)
            compound.setString("sel", this.selectedAbility.getName());
        compound.setBoolean("active", this.abilityActive);
    }

    @Override
    public void loadNBTData(NBTTagCompound compound) {
        if (compound != null) {
            this.charge = compound.getInteger("Charge");
            NBTTagList abilityList = compound.getTagList("abilities_S", 10);
            this.abilities.clear();
            for (int i = 0; i < abilityList.tagCount(); ++i){
                this.abilities.add(SurgeRegistry.getAbilityForName(abilityList.getCompoundTagAt(i).getString("name")));
            }
            if (this.abilities.size() != 0)
                this.selectedAbility = SurgeRegistry.getAbilityForName(compound.getString("sel"));
            this.abilityActive = compound.getBoolean("active");
        }
    }

    @Override
    public void init(Entity entity, World world) {
        this.entity = entity;
    }

    public void syncFully(){
        if (entity instanceof EntityPlayerMP) {
            NBTTagCompound nbt = new NBTTagCompound();
            this.saveNBTData(nbt);
            PowerSurge.networkHandler.getNetworkWrapper().sendTo(new PacketCompleteSync(nbt), (EntityPlayerMP) this.entity);
        }
    }

    private void syncSurge(){
        if (entity instanceof EntityPlayerMP) {
            NBTTagCompound nbt = new NBTTagCompound();
            nbt.setIntArray("data", new int[]{this.getCharge()});
            PowerSurge.networkHandler.getNetworkWrapper().sendTo(new PacketSetSurgeData(nbt), (EntityPlayerMP) entity);
        }
    }
}
