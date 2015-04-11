package com.HotFlow.TribeCraft.Event.Player;

import com.HotFlow.TribeCraft.Player.TribePlayer;
import org.bukkit.event.Cancellable;

/**
 * @author HotFlow
 */
public class PlayerStoreExperienceEvent extends PlayerEvent implements Cancellable
{
    private Boolean cancelled = false;

    public PlayerStoreExperienceEvent(TribePlayer player)
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
