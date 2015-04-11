package com.HotFlow.TribeCraft.Event.Player;

import com.HotFlow.TribeCraft.Player.TribePlayer;
import org.bukkit.event.Cancellable;

/**
 * @author HotFlow
 */
public class PlayerStoreInventoryEvent extends PlayerEvent implements Cancellable
{
    private Boolean cancelled = false;
    
    public PlayerStoreInventoryEvent(TribePlayer player)
    {
        super(player);
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
