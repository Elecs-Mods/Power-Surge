package elec332.powersurge.client;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import elec332.powersurge.main.PowerSurge;
import elec332.powersurge.surge.SurgeData;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraftforge.client.GuiIngameForge;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import org.lwjgl.opengl.GL11;

/**
 * Created by Elec332 on 12-3-2015.
 */
public class ClientRenderEvents extends GuiIngameForge{

    public ClientRenderEvents(Minecraft minecraft){
        super(minecraft);
        //mc = minecraft;
    }

    //private static Minecraft mc;

    @SubscribeEvent
    public void eventHandler(RenderGameOverlayEvent event){
        if(event.isCancelable() || event.type != RenderGameOverlayEvent.ElementType.EXPERIENCE) {
            return;
        }
        ScaledResolution res = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
        int width = res.getScaledWidth();
        int height = res.getScaledHeight();
        mc.getTextureManager().bindTexture(icons);
        GL11.glColor4f(0.04456778F, 0.96788767F, 0.745644F, 0.7F);
        GL11.glDisable(GL11.GL_BLEND);

        if (mc.playerController.gameIsSurvivalOrAdventure()) {
            short barWidth = 182;
            int charge = SurgeData.get(mc.thePlayer).getCharge();
            int filled = (int)((charge / PowerSurge.max_Charge) * (float)(barWidth + 1));
            int top = height - 32 + 3;
            drawTexturedModalRect(3, top, 0, 64, barWidth, 5);
            if (filled > 0) {
                drawTexturedModalRect(0, top, 0, 69, filled, 5);
            }
        }
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F); //*/
    }
}
