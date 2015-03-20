package elec332.powersurge.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import elec332.core.network.AbstractPacket;
import elec332.powersurge.api.NetworkAbility;
import elec332.powersurge.surge.SurgeRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.NBTTagCompound;

/**
 * Created by Elec332 on 18-3-2015.
 */
public class PacketNetworkAbility extends AbstractPacket {

    public PacketNetworkAbility(){
        super();
    }

    public PacketNetworkAbility(String s, boolean t){
        NBTTagCompound tag = new NBTTagCompound();
        tag.setString("name", s);
        tag.setBoolean("activate", t);
        this.networkPackageObject = tag;
    }

    @Override
    public IMessage onMessage(AbstractPacket message, MessageContext ctx) {
        boolean activate = message.networkPackageObject.getBoolean("activate");
        NetworkAbility networkAbility = SurgeRegistry.getNetworkAbilityForName(message.networkPackageObject.getString("name"));
        if (activate){
            networkAbility.onActivated(Minecraft.getMinecraft().thePlayer);
        } else {
            networkAbility.onDeActivated(Minecraft.getMinecraft().thePlayer);
        }
        return null;
    }
}
