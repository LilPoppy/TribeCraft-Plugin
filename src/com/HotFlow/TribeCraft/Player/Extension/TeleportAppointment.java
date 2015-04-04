package com.HotFlow.TribeCraft.Player.Extension;

import org.bukkit.Location;

/**
 * @author HotFlow
 */
public class TeleportAppointment extends Appointment
{
    private Location location;
    
    public TeleportAppointment(int time,Location location)
    {
        super(time);
        this.location = location;
    }
    
    /**
     * 获取坐标
     * @return 
     */
    public Location getLocation()
    {
        return this.location;
    }
    
    /**
     * 设置坐标
     * @param loc 
     */
    public void setLocation(Location loc)
    {
        this.location = loc;
    }
}
