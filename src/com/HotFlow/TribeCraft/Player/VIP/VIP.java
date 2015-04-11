package com.HotFlow.TribeCraft.Player.VIP;

/**
 * @author HotFlow
 */
public interface VIP
{
    /**
     * 获取VIP等级
     *
     * @return
     */
    public int getLevel();

    /**
     * 获取物品掉落机率
     *
     * @return
     */
    public double getItemDropChance();

    /**
     * 获取装备掉落机率
     *
     * @return
     */
    public double getArmorDropChance();

    /**
     * 获取经验掉落机率
     *
     * @return
     */
    public double getExpDropPercentage();
}
