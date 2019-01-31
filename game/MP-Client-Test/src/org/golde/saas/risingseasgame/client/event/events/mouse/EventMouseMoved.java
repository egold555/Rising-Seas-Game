package org.golde.saas.risingseasgame.client.event.events.mouse;

import org.golde.saas.risingseasgame.client.event.Event;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class EventMouseMoved extends Event {

	private int oldX;
	private int oldY;
	private int newX;
	private int newY;
	
}
