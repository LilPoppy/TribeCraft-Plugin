package com.HotFlow.TribeCraft.Timer;

public class Timer extends Thread{
  private boolean isWork=false;
  private long time;
  public Timer(){
	  start();
  }
  public void run(){
	  while (isWork){
		  time++;
		  try {
			sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	  }
  }
  /**
   * 启动/暂停 计时器
   * @param isWork 是否运行
   */
  public void setWork(boolean isWork){
	  this.isWork=isWork;
  }
  /**
   * 获取计时器时间
   * @return 计时器时间
   */
  public long getTime(){
	  return this.time;
  }
  /**
   * 设置计时器时间
   * @param time 计时器时间
   */
  public void setTime(long time){
	  this.time=time;
  }
}
