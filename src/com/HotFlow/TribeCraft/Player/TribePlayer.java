package com.HotFlow.TribeCraft.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import com.HotFlow.TribeCraft.Inventory.DeathInventory;
import com.HotFlow.TribeCraft.Player.VIP.VIP;

/**
 * @author HotFlow
 */
public class TribePlayer {
	private final Player player;
	private Location gateLoc1;
	private Location gateLoc2;
	private final List<VIP> vips = new ArrayList<VIP>();
	private DeathInventory deathInventory = new DeathInventory();
	private int teleportingTime = 10;
	public int teleportingTask;

	public TribePlayer(Player player) {
		this.player = player;
	}

	/**
	 * 设置传送倒计时剩余时间
	 * 
	 * @param second
	 */
	public void setTeleportingTime(int second) {
		this.teleportingTime = second;
	}

	/**
	 * 获取传送倒计时剩余时间
	 * 
	 * @return
	 */
	public int getTeleportingTime() {
		return this.teleportingTime;
	}

	/**
	 * 返回Player
	 * 
	 * @return
	 */
	public Player getCraftPlayer() {
		return this.player;
	}

	/**
	 * 获取传送门选择点1
	 * 
	 * @return
	 */
	public Location getGateSelectedLocation1() {
		return this.gateLoc1;
	}

	/**
	 * 设置传送门选择点1
	 * 
	 * @param loc
	 */
	public void setGateSelectedLocation1(Location loc) {
		this.gateLoc1 = loc;
	}

	/**
	 * 获取传送门选择点2
	 * 
	 * @return
	 */
	public Location getGateSelectedLocation2() {
		return this.gateLoc2;
	}

	/**
	 * 设置传送门选择点2
	 * 
	 * @param loc
	 */
	public void setGateSelectedLocation2(Location loc) {
		this.gateLoc2 = loc;
	}

	/**
	 * 获取VIP列表
	 * 
	 * @return
	 */
	public List<VIP> getVIPList() {
		Collections.sort(this.vips, new Comparator<VIP>() {
			@Override
			public int compare(VIP vip1, VIP vip2) {
				return vip2.getLevel() - vip1.getLevel();
			}
		});

		return this.vips;
	}

	/**
	 * 设置死亡时保护物品
	 * 
	 * @param inventory
	 */
	public void setDeathProtectedItems(DeathInventory inventory) {
		this.deathInventory = inventory;
	}

	/**
	 * 获取死亡时保护物品
	 * 
	 * @return
	 */
	public DeathInventory getDeathProtectedItems() {
		return this.deathInventory;
	}

}
