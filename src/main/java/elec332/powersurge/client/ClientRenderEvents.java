package elec332.powersurge.client;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import elec332.core.java.SmartArrayList;
import elec332.powersurge.api.IAbility;
import elec332.powersurge.main.Config;
import elec332.powersurge.main.PowerSurge;
import elec332.powersurge.surge.SurgeData;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraftforge.client.GuiIngameForge;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;

/**
 * Created by Elec332 on 12-3-2015.
 */
public class ClientRenderEvents extends GuiIngameForge{

    public ClientRenderEvents(Minecraft minecraft) {
        super(minecraft);
    }

    @SubscribeEvent
    public void eventHandler(RenderGameOverlayEvent event){
        if(event.isCancelable() || event.type != RenderGameOverlayEvent.ElementType.EXPERIENCE) {
            return;
        }
        ScaledResolution res = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
        //int width = res.getScaledWidth();
        int height = res.getScaledHeight();
        mc.getTextureManager().bindTexture(icons);
        GL11.glColor4f(0.04456778F, 0.96788767F, 0.745644F, 0.7F);
        //GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glDisable(GL11.GL_BLEND);
        SurgeData data = SurgeData.get(mc.thePlayer);


        if (mc.playerController.gameIsSurvivalOrAdventure()) {
            short barWidth = 182;
            int filled = (int)(((float)data.getCharge() / Config.max_Charge) * (float)(barWidth + 1));
            int top = height - 32 + 3;
            drawTexturedModalRect(5, top, 0, 64, barWidth, 5);
            if (filled > 0) {
                drawTexturedModalRect(5, top, 0, 69, filled, 5);
            }

        }
        SmartArrayList<IAbility> abilities = data.getAbilities();
        if (abilities.isEmpty()){
            return;
        } else {
            RenderAbilityText(abilities.getNext(data.getSelectedAbility()).getName(), data.getSelectedAbility().getName(), abilities.getPrevious(data.getSelectedAbility()).getName());
        }


        /*if (selectedAbilityIndex != 0 && selectedAbilityIndex != (abilities.size()-1)){
            RenderAbilityText(abilities.get(selectedAbilityIndex - 1).getName(), abilities.get(selectedAbilityIndex).getName(), abilities.get(selectedAbilityIndex+1).getName());
        } else if (selectedAbilityIndex == 0 && abilities.size() >= 2){
            RenderAbilityText(abilities.get(abilities.size()-1).getName(), abilities.get(selectedAbilityIndex).getName(), abilities.get(selectedAbilityIndex+1).getName());
        } else if (selectedAbilityIndex == (abilities.size()-1) && abilities.size() >= 2){
            RenderAbilityText(abilities.get(selectedAbilityIndex-1).getName(), abilities.get(selectedAbilityIndex).getName(), abilities.get(0).getName());
        } else if (abilities.size() == 1){
            String name = data.getSelectedAbility().getName();
            RenderAbilityText(name, name, name);
        } else {
            PowerSurge.instance.error("ERROR: COULDN'T RENDER THE NAMES FOR THE ABILITIES");
        }*/

        GL11.glEnable(GL11.GL_BLEND);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F); //*/
    }

    private void RenderAbilityText(String left, String middle, String right){
        int height = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight).getScaledHeight() - 20;
        int colour = 0; //White
        int spaceUsed = 5;
        FontRenderer fontRenderer = mc.fontRenderer;
        fontRenderer.drawString(left, spaceUsed, height, colour);
        spaceUsed = fontRenderer.getStringWidth(left) + 10;
        fontRenderer.drawString(middle, spaceUsed, height, colour);
        spaceUsed = spaceUsed + fontRenderer.getStringWidth(middle) +10;
        fontRenderer.drawString(right, spaceUsed, height, colour);
    }
}

