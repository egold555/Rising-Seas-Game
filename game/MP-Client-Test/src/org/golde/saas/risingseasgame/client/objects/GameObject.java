package org.golde.saas.risingseasgame.client.objects;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/*
 * This interface is for every game object. 
 */
public interface GameObject extends Comparable<GameObject> {
	
	public GameObject init(GameContainer gc, StateBasedGame sbg) throws SlickException;
	public default void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {}
	public default void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {}
	public default void keyPressed(int key, char c) {}
	public default void keyReleased(int key, char c) {}
	public default void mouseClicked(int button, int x, int y, int clickCount) {}
	public default void mouseWheelMoved(int newValue) {}
	public default void mouseDragged(int oldx, int oldy, int newx, int newy) {}
	public default void mouseMoved(int oldx, int oldy, int newx, int newy) {}
	public default void mousePressed(int button, int x, int y) {}
	public default void mouseReleased(int button, int x, int y) {}
	public default void enter(GameContainer gc, StateBasedGame sbg) throws SlickException {}
	public default void leave(GameContainer container, StateBasedGame game) throws SlickException {}
	
	public default int getZIndex() {
		return 0;
	}
	
	@Override
	public default int compareTo(GameObject o) {
		return Integer.compare(this.getZIndex(), o.getZIndex());
	}
	
}
