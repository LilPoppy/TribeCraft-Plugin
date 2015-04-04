package com.HotFlow.TribeCraft.PortalGate;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;

import com.HotFlow.TribeCraft.World.Area;

/**
 * @author HotFlow
 */
public class PortalGate {
	private final String name;
	private String message;
	private List<String> commands;
	private PortalGateType type;
	private Area from;
	private Location to;

	public PortalGate(String name) {
		this.name = name;
		this.message = "";
		this.commands = new ArrayList<String>();
		this.type = PortalGateType.Location;
	}

	/**
	 * 获取传送门名称
	 * 
	 * @return
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * 设置传送门类型
	 * 
	 * @param type
	 */
	public void setType(PortalGateType type) {
		this.type = type;
	}

	/**
	 * 获取传送门类型
	 * 
	 * @return
	 */
	public PortalGateType getType() {
		return this.type;
	}

	/**
	 * 设置传送提示
	 * 
	 * @param message
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * 获取传送提示
	 * 
	 * @return
	 */
	public String getMessage() {
		return this.message;
	}

	/**
	 * 设置传送触发命令
	 * 
	 * @param commands
	 */
	public void setCommands(List<String> commands) {
		this.commands = commands;
	}

	/**
	 * 获取传送触发命令
	 * 
	 * @return
	 */
	public List<String> getCommands() {
		return this.commands;
	}

	/**
	 * 获取传送出发点
	 * 
	 * @return
	 */
	public Area getFrom() {
		return this.from;
	}

	/**
	 * 设置传送出发点
	 * 
	 * @param area
	 */
	public void setFrom(Area area) {
		this.from = area;
	}

	/**
	 * 获取传送目的地
	 * 
	 * @return
	 */
	public Location getTo() {
		return this.to;
	}

	/**
	 * 设置传送目的地
	 * 
	 * @param to
	 */
	public void setTo(Location to) {
		this.to = to;
	}

}
