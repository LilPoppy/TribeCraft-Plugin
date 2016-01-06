package com.HotFlow.TribeCraft.World;

import org.bukkit.Location;

/**
 * @author HotFlow
 */
public class Area
{
    private final Location loc1;
    private final Location loc2;

    public Area(Location loc1, Location loc2)
    {
        this.loc1 = loc1;
        this.loc2 = loc2;
    }

    /**
     * 获取坐标点1
     *
     * @return
     */
    public Location getLocation1()
    {
        return this.loc1;
    }

    /**
     * 获取坐标点2
     *
     * @return
     */
    public Location getLocation2()
    {
        return this.loc2;
    }

    /**
     * 区域是否与区域重叠
     *
     * @param area
     * @return
     */
    public static Boolean isOverlappingArea(Area area)
    {
        if (this.getLocation1().getWorld().getName() == null ? area.getLocation1().getWorld().getName() == null : this.getLocation1().getWorld().getName().equals(area.getLocation1().getWorld().getName())
                && this.getLocation2().getWorld().getName() == null ? area.getLocation1().getWorld().getName() == null : this.getLocation2().getWorld().getName().equals(area.getLocation1().getWorld().getName()))
        {
            if ((this.getLocation1().getBlockX() >= area.getLocation1().getBlockX() && this.getLocation1().getBlockX() <= area.getLocation2().getBlockX()) || (this.getLocation1().getBlockX() <= area.getLocation1().getBlockX() && this.getLocation1().getBlockX() >= area.getLocation2().getBlockX())
                    || (this.getLocation2().getBlockX() >= area.getLocation1().getBlockX() && this.getLocation2().getBlockX() <= area.getLocation2().getBlockX()) || (this.getLocation2().getBlockX() <= area.getLocation1().getBlockX() && this.getLocation2().getBlockX() >= area.getLocation2().getBlockX()))
            {
                if ((this.getLocation1().getBlockZ() >= area.getLocation1().getBlockZ() && this.getLocation1().getBlockZ() <= area.getLocation2().getBlockZ()) || (this.getLocation1().getBlockZ() <= area.getLocation1().getBlockZ() && this.getLocation1().getBlockZ() >= area.getLocation2().getBlockZ())
                        || (this.getLocation2().getBlockZ() >= area.getLocation1().getBlockZ() && this.getLocation2().getBlockZ() <= area.getLocation2().getBlockZ()) || (this.getLocation2().getBlockZ() <= area.getLocation1().getBlockZ() && this.getLocation2().getBlockZ() >= area.getLocation2().getBlockZ()))
                {
                    if ((this.getLocation1().getBlockY() >= area.getLocation1().getBlockY() && this.getLocation1().getBlockY() <= area.getLocation2().getBlockY()) || (this.getLocation1().getBlockY() <= area.getLocation1().getBlockY() && this.getLocation1().getBlockY() >= area.getLocation2().getBlockY())
                            || (this.getLocation2().getBlockY() >= area.getLocation1().getBlockY() && this.getLocation2().getBlockY() <= area.getLocation2().getBlockY()) || (this.getLocation2().getBlockY() <= area.getLocation1().getBlockY() && this.getLocation2().getBlockY() >= area.getLocation2().getBlockY()))
                    {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    /**
     * 区域是否包含坐标
     *
     * @param location
     * @return
     */
    public static Boolean isContainLocation(Location location)
    {
        if (location.getWorld().getName() == null ? this.getLocation1().getWorld().getName() == null : location.getWorld().getName().equals(this.getLocation1().getWorld().getName()))
        {
            if ((location.getBlockX() >= this.getLocation1().getBlockX() && location.getBlockX() <= this.getLocation2().getBlockX()) || (location.getBlockX() <= this.getLocation1().getBlockX() && location.getBlockX() >= this.getLocation2().getBlockX()))
            {
                if ((location.getBlockZ() >= this.getLocation1().getBlockZ() && location.getBlockZ() <= this.getLocation2().getBlockZ()) || (location.getBlockZ() <= this.getLocation1().getBlockZ() && location.getBlockZ() >= this.getLocation2().getBlockZ()))
                {
                    if ((location.getBlockY() >= this.getLocation1().getBlockY() && location.getBlockY() <= this.getLocation2().getBlockY()) || (location.getBlockY() <= this.getLocation1().getBlockY() && location.getBlockY() >= this.getLocation2().getBlockY()))
                    {
                        return true;
                    }
                }
            }
        }

        return false;
    }
}
