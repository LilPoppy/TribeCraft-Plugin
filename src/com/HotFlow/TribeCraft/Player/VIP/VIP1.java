package com.HotFlow.TribeCraft.Player.VIP;

/**
 * @author HotFlow
 */
public class VIP1 implements VIP {
	public int getLevel() {
		return 1;
	}

	public Boolean canFly() {
		return false;
	}

	public double getChanceOfDrops() {
		return 0.15;
	}
}
