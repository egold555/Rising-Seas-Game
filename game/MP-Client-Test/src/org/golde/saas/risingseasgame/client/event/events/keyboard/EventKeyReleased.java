package org.golde.saas.risingseasgame.client.event.events.keyboard;

import org.golde.saas.risingseasgame.client.event.Event;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class EventKeyReleased extends Event {

	private int key;
	private char character;
	
}
