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

    /**
     * 设置物品掉落机率
     *
     * @param chance
     */
    public void setItemDropChance(double chance);

    /**
     * 设置装备掉落机率
     *
     * @param chance
     */
    public void setArmorDropChance(double chance);

    /**
     * 设置经验掉落机率
     *
     * @param chance
     */
    public void setExpDropPercentage(double chance);
}
