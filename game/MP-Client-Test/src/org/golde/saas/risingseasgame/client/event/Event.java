package org.golde.saas.risingseasgame.client.event;

import java.util.ArrayList;

import org.golde.saas.risingseasgame.client.event.EventManager.EventData;

public abstract class Event {
	public Event call() {
		call(this);
		return this;
	}

	public static class EventPriority {

		public static final byte FIRST = 0, SECOND = 1, THIRD = 2, FOURTH = 3, FIFTH = 4;

		public static final byte[] VALUE_ARRAY;

		static {
			VALUE_ARRAY = new byte[]{0, 1, 2, 3, 4};
		}

	}

	private static final void call(final Event event) {
		//HLog.debug("Calling event: " + event.getClass().getName());
		final ArrayList<EventData> dataList = EventManager.get(event.getClass());

		if (dataList != null) {
			for (int i = 0; i < dataList.size(); ++i) {
				EventData data = dataList.get(i);
				
				try {
					data.target.invoke(data.source, event);
				}
				catch (Exception e) {
					e.printStackTrace();
				} 
			}
		}
	}
}
