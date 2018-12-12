package org.golde.saas.risingseasgame.objects;

/*
 * This interface is for every game object. 
 */
public interface GameObject {

	public float getX();
	public float getY();
	
	public void setXY(float x, float y);
	public void setX(float x);
	public void setY(float y);
	
	public void draw();
	
}
