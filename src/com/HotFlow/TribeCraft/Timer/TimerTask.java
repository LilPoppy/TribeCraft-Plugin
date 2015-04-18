package com.HotFlow.TribeCraft.Timer;

import com.HotFlow.TribeCraft.Event.Plugin.PluginTimeChangeEvent;
import com.HotFlow.TribeCraft.Player.Extension.DelayTask;
import com.HotFlow.TribeCraft.Player.TribePlayer;
import com.HotFlow.TribeCraft.Main;
import static org.bukkit.Bukkit.getServer;

import org.bukkit.plugin.Plugin;

/**
 * @author HotFlow
 */
public final class TimerTask
{
    private final int taskID;
    private TaskState taskState;

    public TimerTask(final Plugin plugin, final ServerTimer timer)
    {
        this.taskState = TaskState.Suspending;

        @SuppressWarnings("deprecation")
        int taskID = getServer().getScheduler().scheduleAsyncRepeatingTask(plugin, new Runnable()
        {
            @Override
            public void run()
            {
                if (taskState.equals(TaskState.Running))
                {
                    PluginTimeChangeEvent event = new PluginTimeChangeEvent(plugin, timer.getTime() + 1);
                    getServer().getPluginManager().callEvent(event);

                    if (event.isCancelled())
                    {
                        return;
                    }

                    timer.setTime(event.getTime());

                    for (TribePlayer player : Main.getPlayerManager().getPlayers())
                    {
                        for (int i = 0; i < player.getDelayTaskList().size(); i++)
                        {
                            DelayTask task = player.getDelayTaskList().get(i);

                            if (task.getTime() > 0)
                            {
                                task.setTime(task.getTime() - 1);
                            }
                            else
                            {
                                task.run();
                                player.removeDelayTask(task);
                            }
                        }

                    }
                }
                else
                {
                    return;
                }
            }

        }, 0L, 20L);

        this.taskID = taskID;
    }

    /**
     * 开始计时
     */
    public void start()
    {
        this.taskState = TaskState.Running;
    }

    /**
     * 暂停计时
     */
    public void suspend()
    {
        this.taskState = TaskState.Suspending;
    }

    /**
     * 停止计时 停止后不能再使用start恢复
     */
    public void stop()
    {
        getServer().getScheduler().cancelTask(this.taskID);
    }

    /**
     * 获取执行器当前状态
     *
     * @return
     */
    public TaskState getCurrentState()
    {
        return this.taskState;
    }

    /**
     * 获取时间执行器ID
     *
     * @return
     */
    public int getTaskID()
    {
        return this.taskID;
    }
}
