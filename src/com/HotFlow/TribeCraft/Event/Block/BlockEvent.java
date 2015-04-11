package com.HotFlow.TribeCraft.Event.Block;

import org.bukkit.block.Block;
import org.bukkit.event.HandlerList;

/**
 * @author HotFlow
 */
public class BlockEvent extends org.bukkit.event.block.BlockEvent
{
    private static final HandlerList handlers = new HandlerList();

    public BlockEvent(Block theBlock)
    {
        super(theBlock);
    }

    @Override
    public HandlerList getHandlers()
    {
        return handlers;
    }

}
