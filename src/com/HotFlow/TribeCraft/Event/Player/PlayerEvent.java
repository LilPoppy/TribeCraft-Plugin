package com.HotFlow.TribeCraft.Event.Player;

import com.HotFlow.TribeCraft.Player.TribePlayer;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 *
 * @author Jerry
 */
public class PlayerEvent extends Event
{
    private static final HandlerList handlers = new HandlerList();
    private final TribePlayer player;

    public PlayerEvent(TribePlayer player)
    {
        this.player = player;
    }

    /**
     * 获取玩家
     *
     * @return
     */
    public TribePlayer getPlayer()
    {
        return this.player;
    }

    /**
     * 获取处理者
     *
     * @return
     */
    public HandlerList getHandlers()
    {
        return handlers;
    }

    /**
     * 获取处理者列表
     *
     * @return
     */
    public static HandlerList getHandlerList()
    {
        return handlers;
    }

}
