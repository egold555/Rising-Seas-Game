package org.golde.saas.risingseasgame.client.event.events;

import org.golde.saas.risingseasgame.client.event.Event;
import org.newdawn.slick.GameContainer;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class EventUpdate extends Event {

	private GameContainer gameContainer;
	private int delta;
	
}
