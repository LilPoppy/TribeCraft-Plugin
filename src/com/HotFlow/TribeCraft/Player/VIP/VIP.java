package com.HotFlow.TribeCraft.Player.VIP;

/**
 * @author HotFlow
 */
public interface VIP {
	/**
	 * 获取VIP等级
	 * 
	 * @return
	 */
	public int getLevel();

	/**
	 * 是否可以飞翔
	 * 
	 * @return
	 */
	public Boolean canFly();

	/**
	 * 获取掉落机率
	 * 
	 * @return
	 */
	public double getChanceOfDrops();
}
