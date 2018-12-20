package org.golde.saas.risingseasgame.client.objects;

public abstract class GameObjectMoveable implements GameObject {

	private int posX, posY;

	public final int getX() {
		return posX;
	}
	
	public final int getY() {
		return posY;
	}
	
	public final void setX(int x) {
		this.posX = x;
	}
	
	public final void setY(int y) {
		this.posY = y;
	}
	
	public final void setXY(int x, int y) {
		setX(x);
		setY(y);
	}
}
