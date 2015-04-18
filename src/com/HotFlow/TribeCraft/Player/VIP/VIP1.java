package com.HotFlow.TribeCraft.Player.VIP;

import com.HotFlow.TribeCraft.Main;

/**
 * @author HotFlow
 */
public class VIP1 implements VIP
{
    public int getLevel()
    {
        return 1;
    }

    public double getItemDropChance()
    {
        return Main.config.getDouble("全局配置.死亡保护." + this.getClass().getName() + ".物品掉落机率");
    }

    public double getArmorDropChance()
    {
        return Main.config.getDouble("全局配置.死亡保护." + this.getClass().getName() + ".装备掉落机率");
    }

    public double getExpDropPercentage()
    {
        return Main.config.getDouble("全局配置.死亡保护." + this.getClass().getName() + ".经验掉落百分比");
    }
}
