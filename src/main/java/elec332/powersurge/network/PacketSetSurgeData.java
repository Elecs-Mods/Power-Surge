package elec332.powersurge.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import elec332.core.network.AbstractPacket;
import elec332.powersurge.surge.SurgeData;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;

/**
 * Created by Elec332 on 13-3-2015.
 */
public class PacketSetSurgeData extends AbstractPacket {

    public PacketSetSurgeData(){
    }

    public PacketSetSurgeData(int i){
        this.networkPackageObject = 1;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.networkPackageObject = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt((Integer) networkPackageObject);
    }

    @Override
    public IMessage onMessage(AbstractPacket message, MessageContext ctx) {
        SurgeData.get(Minecraft.getMinecraft().thePlayer).setCharge((Integer) networkPackageObject);
        return null;
    }
}
