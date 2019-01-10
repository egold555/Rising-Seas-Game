package org.golde.saas.risingseasgame.shared.scheduler;

public class ScheduledTask implements Comparable<ScheduledTask>
{
	private long timeToFire;
	private boolean repeating;
	private long repeatTime;
	private Runnable taskToRun;
	
	ScheduledTask(long timeToFire, boolean repeat, long repeatTime, Runnable taskToRun)
	{
		this.timeToFire = timeToFire;
		this.repeating = repeat;
		this.repeatTime = repeatTime;
		this.taskToRun = taskToRun;
	}
	 
	synchronized long getTimeToFire() {
		return timeToFire;
	}
	
	synchronized long getRepeatTime() {
		return repeatTime;
	}
	
	synchronized boolean isRepeating() {
		return repeating;
	}
	
	synchronized void delayRepeatTime()
	{
		timeToFire += repeatTime;
	}
	
	synchronized Runnable getRunnable()
	{
		return taskToRun;
	}
	
	synchronized void run()
	{
		taskToRun.run();
	}
	
	@Override
	public synchronized int compareTo(ScheduledTask other) {
		if (other.timeToFire > this.timeToFire)
			return -1;
		else if (other.timeToFire < this.timeToFire)
			return 1;
		else
			return 0;
	}
}