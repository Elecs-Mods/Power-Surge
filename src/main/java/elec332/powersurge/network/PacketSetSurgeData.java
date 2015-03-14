package elec332.powersurge.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import elec332.core.network.AbstractPacket;
import elec332.powersurge.main.PowerSurge;
import elec332.powersurge.surge.SurgeData;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.NBTTagCompound;

/**
 * Created by Elec332 on 13-3-2015.
 */
public class PacketSetSurgeData extends AbstractPacket {

    public PacketSetSurgeData(){
        super();
    }

    public PacketSetSurgeData(NBTTagCompound tag){
        super(tag);
    }

    @Override
    public IMessage onMessage(AbstractPacket message, MessageContext ctx) {
        int[] d = message.networkPackageObject.getIntArray("data");
        if (d.length >= 1) {
            SurgeData.get(Minecraft.getMinecraft().thePlayer).setCharge(d[0]);
            PowerSurge.instance.info("Set surge to: "+ d[0]);
        }
        if (d.length >= 2) {
            PowerSurge.max_Charge = d[1];
            PowerSurge.instance.info("Received data from server: "+ d[1]);
        }
        return null;
    }
}
