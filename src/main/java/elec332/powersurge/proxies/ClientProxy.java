package elec332.powersurge.proxies;

import elec332.powersurge.client.ClientRenderEvents;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;

/**
 * Created by Elec332 on 24-2-2015.
 */
public class ClientProxy extends CommonProxy{

    Minecraft minecraft = Minecraft.getMinecraft();

    @Override
    public void registerRenders() {
        MinecraftForge.EVENT_BUS.register(new ClientRenderEvents(minecraft));
    }
}
