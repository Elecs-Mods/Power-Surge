package elec332.powersurge.client;

import elec332.core.client.KeyHandlerBase;
import elec332.powersurge.lib.EnumKeyType;
import elec332.powersurge.main.PowerSurge;
import elec332.powersurge.network.PacketHandleKeyInput;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.nbt.NBTTagCompound;
import org.lwjgl.input.Keyboard;

/**
 * Created by Elec332 on 15-3-2015.
 */
public class KeyHandler extends KeyHandlerBase {

    public KeyHandler(){
        super();
    }

    public KeyBinding key1 = makeKeyBinding("Activate", Keyboard.KEY_M);
    public KeyBinding key2 = makeKeyBinding("Previous", Keyboard.KEY_LBRACKET);
    public KeyBinding key3 = makeKeyBinding("next", Keyboard.KEY_RBRACKET);

    public void init(){
        registerKeyBinding(key1);
        registerKeyBinding(key2);
        registerKeyBinding(key3);
    }

    @Override
    public void performAction(KeyBinding key) {
        if (EnumKeyType.valueOf(key.getKeyDescription().toUpperCase()) != null) {
            NBTTagCompound compound = new NBTTagCompound();
            compound.setInteger("type", EnumKeyType.valueOf(key.getKeyDescription().toUpperCase()).ordinal());
            PowerSurge.networkHandler.getNetworkWrapper().sendToServer(new PacketHandleKeyInput(compound));
        }
    }

    public KeyBinding makeKeyBinding(String name, int kb){
        return new KeyBinding(name, kb, "PowerSurge.keys");
    }
}
