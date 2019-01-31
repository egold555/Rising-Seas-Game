package org.golde.saas.risingseasgame.client.event.events;

import org.golde.saas.risingseasgame.client.event.Event;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class EventRender extends Event {

	private GameContainer gameContainer;
	private Graphics graphics;
	
}
