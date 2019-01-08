package org.golde.saas.risingseasgame.client.impl;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public interface GameStateObjectSharedFunctions {

	public default void render(GameContainer gc, Graphics g) throws SlickException {}
	public default void update(GameContainer gc, int delta) throws SlickException {}
	public default void keyPressed(int key, char c) {}
	public default void keyReleased(int key, char c) {}
	public default void mouseClicked(int button, int x, int y, int clickCount) {}
	public default void mouseWheelMoved(int newValue) {}
	public default void mouseDragged(int oldx, int oldy, int newx, int newy) {}
	public default void mouseMoved(int oldx, int oldy, int newx, int newy) {}
	public default void mousePressed(int button, int x, int y) {}
	public default void mouseReleased(int button, int x, int y) {}
	
}
