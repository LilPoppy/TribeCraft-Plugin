package com.HotFlow.TribeCraft.Timer;

import com.HotFlow.TribeCraft.Event.Plugin.PluginDelayTaskRunEvent;
import com.HotFlow.TribeCraft.Event.Plugin.PluginDelayTaskTimeChangeEvent;
import com.HotFlow.TribeCraft.Event.Plugin.PluginTimeChangeEvent;
import com.HotFlow.TribeCraft.Main;
import com.HotFlow.TribeCraft.Timer.Task.DelayTask;
import static org.bukkit.Bukkit.getServer;
import org.bukkit.plugin.Plugin;

/**
 * @author HotFlow
 */
public final class ServerTimerRunnable
{
    private final int taskID;
    private TaskState taskState;

    public ServerTimerRunnable(final Plugin plugin, final ServerTimer timer)
    {
        this.taskState = TaskState.Suspending;

        int taskID = getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable()
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

                    for (int i = 0; i < Main.getDelayTaskManager().getTasks().size(); i++)
                    {
                        DelayTask task = Main.getDelayTaskManager().getTasks().get(i);

                        if (task.getTime() > 0)
                        {
                            PluginDelayTaskTimeChangeEvent event1 = new PluginDelayTaskTimeChangeEvent(plugin, task, timer.getTime());
                            getServer().getPluginManager().callEvent(event1);

                            if (event1.isCancelled())
                            {
                                return;
                            }

                            task.setTime(task.getTime() - 1);
                        }
                        else
                        {
                            PluginDelayTaskRunEvent event1 = new PluginDelayTaskRunEvent(plugin, task, timer.getTime());
                            getServer().getPluginManager().callEvent(event1);
                            
                            if(event1.isCancelled())
                            {
                                return;
                            }
                            
                            task.run();
                            Main.getDelayTaskManager().getTasks().remove(task);
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
