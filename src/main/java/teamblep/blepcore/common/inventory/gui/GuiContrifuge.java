package teamblep.blepcore.common.inventory.gui;

import teamblep.blepcore.common.inventory.container.ContainerCentrifuge;
import teamblep.blepcore.common.tileentity.machine.TileEntityCentrifuge;

/**
 * @author Kelan
 */

public class GuiContrifuge extends GuiMachine<TileEntityCentrifuge, ContainerCentrifuge>
{
    public GuiContrifuge(TileEntityCentrifuge tileEntity, ContainerCentrifuge container)
    {
        super(tileEntity, container, 0, 0);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
    {

    }
}
