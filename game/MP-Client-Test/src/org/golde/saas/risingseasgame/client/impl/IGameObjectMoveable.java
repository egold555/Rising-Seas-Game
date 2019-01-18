package org.golde.saas.risingseasgame.client.impl;

public interface IGameObjectMoveable {

	public float getX();
	public float getY();
	public void setX(int x);
	public void setY(int y);
	public default void setXY(int x, int y) {
		setX(x);
		setY(y);
	}
	
}
