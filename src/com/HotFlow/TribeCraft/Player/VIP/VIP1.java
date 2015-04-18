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
        return Main.getPluginConfig().getVIPInfo().getRateOfDrop().getVIP(this.getLevel()).RateOfItem;

    }

    public double getArmorDropChance()
    {
        return Main.getPluginConfig().getVIPInfo().getRateOfDrop().getVIP(this.getLevel()).RateOfEquipment;
    }

    public double getExpDropPercentage()
    {
        return Main.getPluginConfig().getVIPInfo().getRateOfDrop().getVIP(this.getLevel()).RateofExp;
    }
}
