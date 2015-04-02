package com.HotFlow.TribeCraft.Event.Player;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 *
 * @author Jerry
 */
public class PlayerEvent extends Event
{
    private static final HandlerList handlers = new HandlerList();
    
    /**
     * 获取处理者
     * @return 
     */
    public HandlerList getHandlers()
    {
        return handlers;
    }
    
    /**
     * 获取处理者列表
     * @return 
     */
    public static HandlerList getHandlerList()
    {
        return handlers;
    }

}
