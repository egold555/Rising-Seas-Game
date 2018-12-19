package org.golde.saas.risingseasgame.client.objects;

public abstract class GameObjectMoveable implements GameObject {

	private float posX, posY;

	public final float getX() {
		return posX;
	}
	
	public final float getY() {
		return posY;
	}
	
	public final void setX(float x) {
		this.posX = x;
	}
	
	public final void setY(float y) {
		this.posY = y;
	}
	
	public final void setXY(float x, float y) {
		setX(x);
		setY(y);
	}
}
