package elec332.powersurge.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import elec332.core.network.AbstractPacket;
import elec332.powersurge.lib.EnumKeyType;
import elec332.powersurge.surge.SurgeData;
import net.minecraft.nbt.NBTTagCompound;

/**
 * Created by Elec332 on 15-3-2015.
 */
public class PacketHandleKeyInput extends AbstractPacket {

    public PacketHandleKeyInput(){
        super();
    }

    public PacketHandleKeyInput(NBTTagCompound tag){
        super(tag);
    }

    @Override
    public IMessage onMessage(AbstractPacket message, MessageContext ctx) {
        SurgeData.get(ctx.getServerHandler().playerEntity).pressKey(EnumKeyType.values()[message.networkPackageObject.getInteger("type")]);
        return null;
    }
}
