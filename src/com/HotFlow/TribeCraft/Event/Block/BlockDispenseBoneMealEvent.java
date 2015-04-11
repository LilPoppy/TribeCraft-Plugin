package com.HotFlow.TribeCraft.Event.Block;

import org.bukkit.block.Block;
import org.bukkit.event.Cancellable;

/**
 * @author HotFlow
 */
public class BlockDispenseBoneMealEvent extends BlockEvent implements Cancellable
{
    private Boolean cancelled = true;

    public BlockDispenseBoneMealEvent(Block theBlock)
    {
        super(theBlock);
    }

    public boolean isCancelled()
    {
        return this.cancelled;
    }

    public void setCancelled(boolean bln)
    {
        this.cancelled = bln;
    }

}
