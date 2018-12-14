package org.golde.saas.risingseasgame.client.objects;

/*
 * A game object that can move
 */

public interface GameObjectMoveable extends GameObject {

	public float getX();
	public float getY();
	
	public void setXY(float x, float y);
	public void setX(float x);
	public void setY(float y);
	
}
