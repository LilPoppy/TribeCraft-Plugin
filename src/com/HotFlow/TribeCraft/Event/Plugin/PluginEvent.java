package com.HotFlow.TribeCraft.Event.Plugin;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.Plugin;

/**
 * @author HotFlow
 */
public class PluginEvent extends Event {
	private static final HandlerList handlers = new HandlerList();
	private final Plugin plugin;

	public PluginEvent(Plugin plugin) {
		this.plugin = plugin;
	}

	/**
	 * 获取插件
	 * 
	 * @return
	 */
	public Plugin getPlugin() {
		return this.plugin;
	}

	/**
	 * 获取处理者
	 * 
	 * @return
	 */
	public HandlerList getHandlers() {
		return handlers;
	}

	/**
	 * 获取处理者列表
	 * 
	 * @return
	 */
	public static HandlerList getHandlerList() {
		return handlers;
	}
}
