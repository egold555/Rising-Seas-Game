package org.golde.saas.risingseasgame.client.event.events.keyboard;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class EventKeyReleased {

	private int key;
	private char character;
	
}
