package org.golde.saas.risingseasgame.client.impl;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

/*
 * This interface is for every game object. 
 */
public interface GameObject extends Comparable<GameObject>, GameStateObjectSharedFunctions {
	
	public GameObject init(GameContainer gc) throws SlickException;
	public default void enter(GameContainer gc) throws SlickException {}
	public default void leave(GameContainer container) throws SlickException {}
	
	public default int getZIndex() {
		return 0;
	}
	
	@Override
	public default int compareTo(GameObject o) {
		return Integer.compare(this.getZIndex(), o.getZIndex());
	}
	
}
