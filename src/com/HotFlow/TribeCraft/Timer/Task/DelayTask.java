package com.HotFlow.TribeCraft.Timer.Task;

/**
 * @author HotFlow
 */
public abstract class DelayTask
{
    private int time;
    private final String description;

    public DelayTask(int time)
    {
        this.time = time;
        this.description = null;
    }

    public DelayTask(int time, String description)
    {
        this.time = time;
        this.description = description;
    }

    /**
     * 执行代码
     */
    public void run()
    {
    }

    /**
     * 获取时间
     *
     * @return
     */
    public int getTime()
    {
        return this.time;
    }

    /**
     * 设置时间
     *
     * @param time
     */
    public void setTime(int time)
    {
        this.time = time;
    }

    /**
     * 获取介绍
     *
     * @return
     */
    public String getDescription()
    {
        return this.description;
    }
}
