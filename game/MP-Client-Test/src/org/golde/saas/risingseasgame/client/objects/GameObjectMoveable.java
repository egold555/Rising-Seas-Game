package org.golde.saas.risingseasgame.client.objects;

import org.golde.saas.risingseasgame.client.impl.GameObject;
import org.golde.saas.risingseasgame.client.impl.IGameObjectMoveable;

public abstract class GameObjectMoveable implements GameObject, IGameObjectMoveable {

	private int posX, posY;
	
	private boolean visable = true;

	public final float getX() {
		return posX;
	}
	
	public final float getY() {
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
	
	@Override
	public boolean isVisable() {
		return visable;
	}

	@Override
	public void setVisable(boolean visable) {
		this.visable = visable;
	}
}
