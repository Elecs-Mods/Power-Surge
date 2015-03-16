package elec332.powersurge.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import elec332.core.network.AbstractPacket;
import elec332.powersurge.main.PowerSurge;
import elec332.powersurge.surge.SurgeData;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.NBTTagCompound;

/**
 * Created by Elec332 on 16-3-2015.
 */
public class PacketCompleteSync extends AbstractPacket {

    public PacketCompleteSync(){
        super();
    }

    public PacketCompleteSync(NBTTagCompound tag){
        super(tag);
    }

    @Override
    public IMessage onMessage(AbstractPacket message, MessageContext ctx) {
        SurgeData.get(Minecraft.getMinecraft().thePlayer).loadNBTData(message.networkPackageObject);
        return null;
    }
}
