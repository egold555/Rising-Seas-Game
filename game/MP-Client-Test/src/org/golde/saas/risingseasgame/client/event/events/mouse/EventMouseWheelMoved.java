package org.golde.saas.risingseasgame.client.event.events.mouse;

import org.golde.saas.risingseasgame.client.event.Event;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class EventMouseWheelMoved extends Event {

	private int newValue;
	
}
