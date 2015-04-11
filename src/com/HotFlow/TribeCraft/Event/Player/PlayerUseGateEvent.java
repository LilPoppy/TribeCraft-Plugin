package com.HotFlow.TribeCraft.Event.Player;

import com.HotFlow.TribeCraft.Player.TribePlayer;
import com.HotFlow.TribeCraft.PortalGate.PortalGate;
import org.bukkit.event.Cancellable;

/**
 * @author HotFlow
 */
public class PlayerUseGateEvent extends PlayerEvent implements Cancellable
{
    private final PortalGate gate;
    private Boolean cancelled = false;

    public PlayerUseGateEvent(TribePlayer player, PortalGate gate)
    {
        super(player);
        this.gate = gate;
    }

    /**
     * 获取传送门
     *
     * @return
     */
    public PortalGate getPortalGate()
    {
        return this.gate;
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
