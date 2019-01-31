package org.golde.saas.risingseasgame.client.event.events.mouse;

import org.golde.saas.risingseasgame.client.event.Event;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class EventMouseClicked extends Event {

	private int button;
	private int x;
	private int y;
	private int clickCount;
	
}
