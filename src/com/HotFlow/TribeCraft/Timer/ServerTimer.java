package com.HotFlow.TribeCraft.Timer;

import com.HotFlow.TribeCraft.TribeCraft;

/**
 * @author HotFlow
 */
public final class ServerTimer {
	private final TimerTask task;
	private int time;

	public ServerTimer() {
		this.task = new TimerTask(TribeCraft.plugin, this);
		this.time = 0;
	}

	/**
	 * 获取游戏时间
	 * 
	 * @return
	 */
	public int getTime() {
		return this.time;
	}

	/**
	 * 设置游戏时间
	 * 
	 * @param second
	 */
	public void setTime(int second) {
		this.time = second;
	}

	/**
	 * 获取时间执行器
	 * 
	 * @return
	 */
	public TimerTask getTimerTask() {
		return this.task;
	}

}
