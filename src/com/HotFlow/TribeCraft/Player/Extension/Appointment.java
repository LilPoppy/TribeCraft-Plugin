package com.HotFlow.TribeCraft.Player.Extension;

/**
 * @author Jerry
 */
public class Appointment 
{
    private int time;
    
    public Appointment(int time)
    {
        this.time = time;
    }
    
    /**
     * 获取时间
     * @return 
     */
    public int getTime()
    {
        return this.time;
    }
    
    /**
     * 设置时间
     * @param time
     */
    public void setTime(int time)
    {
        this.time = time;
    }
}
