package com.HotFlow.TribeCraft.Manager;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;

import com.HotFlow.TribeCraft.PortalGate.PortalGate;
import com.HotFlow.TribeCraft.World.Area;

/**
 * @author HotFlow
 */
public class PortalGateManager {
	private final List<PortalGate> portalGates = new ArrayList<PortalGate>();

	/**
	 * 获取传送门
	 * 
	 * @param name
	 * @return
	 */
	public PortalGate getPortalGate(String name) {
		for (PortalGate gate : portalGates) {
			if (gate.getName().equalsIgnoreCase(name)) {
				return gate;
			}
		}
		return null;
	}

	/**
	 * 获取传送门
	 * 
	 * @param location
	 * @return
	 */
	public PortalGate getPortalGate(Location location) {
		for (PortalGate gate : portalGates) {
			if (Area.isAreaContainLocation(gate.getFrom(), location)) {
				return gate;
			}
		}

		return null;
	}

	/**
	 * 获取传送门
	 * 
	 * @param area
	 * @return
	 */
	public PortalGate getPortalGate(Area area) {
		for (PortalGate gate : portalGates) {
			if (gate.getFrom().equals(area)) {
				return gate;
			}
		}
		return null;
	}

	/**
	 * 添加传送门
	 * 
	 * @param gate
	 */
	public void addPortalGate(PortalGate gate) {
		this.portalGates.add(gate);
	}

	/**
	 * 移除传送门
	 * 
	 * @param gate
	 * @return
	 */
	public Boolean removePortalGate(PortalGate gate) {
		return this.portalGates.remove(gate);
	}

	/**
	 * 获取传送门列表
	 * 
	 * @return
	 */
	public List<PortalGate> getPortalGates() {
		return this.portalGates;
	}
}
