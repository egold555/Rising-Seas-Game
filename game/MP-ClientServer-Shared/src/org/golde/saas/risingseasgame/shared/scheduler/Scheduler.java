package org.golde.saas.risingseasgame.shared.scheduler;

import java.util.PriorityQueue;

import org.golde.saas.risingseasgame.shared.Logger;

public class Scheduler {

	private PriorityQueue<ScheduledTask> queue = new PriorityQueue<ScheduledTask>();

	public synchronized ScheduledTask runTaskLater(long when, Runnable what)
	{
		ScheduledTask task = new ScheduledTask(when + getCurrentTime(), false, 0, what);
		queue.add(task);
		return task;
	}

	public synchronized ScheduledTask runRepeatingTask(long when, long repeatInterval, Runnable what)
	{
		ScheduledTask task = new ScheduledTask(when + getCurrentTime(), true, repeatInterval, what);
		queue.add(task);
		return task;
	}

	public synchronized void cancelTask(ScheduledTask task)
	{
		queue.remove(task);
	}

	public synchronized void update()
	{
		
		long currentTime = getCurrentTime();

		while (true) {
			ScheduledTask next = queue.peek();
			if (next != null && next.getTimeToFire() <= currentTime) {
				queue.remove();
				runTask(next);
			}
			else {
				break;
			}
		}
	}

	private synchronized void runTask(ScheduledTask task)
	{
		task.run();

		if (task.isRepeating()) {
			task.delayRepeatTime();
			queue.add(task);
		}
	}

	private synchronized long getCurrentTime()
	{
		return System.currentTimeMillis();
	}

}
