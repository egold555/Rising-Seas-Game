package org.golde.saas.risingseasgame.client.objects;

import org.golde.saas.risingseasgame.client.impl.GameObject;
import org.golde.saas.risingseasgame.client.impl.IGameObjectMoveable;
import org.newdawn.slick.geom.Rectangle;

@SuppressWarnings("serial")
public abstract class GameObjectClickable extends Rectangle implements GameObject, IGameObjectMoveable {
	
	boolean inside = false;

	public GameObjectClickable(float x, float y, float width, float height) {
		super(x, y, width, height);
	}

	@Override
	public void setX(int x) {
		this.x = x;
	}

	@Override
	public void setY(int y) {
		this.y = y;
	}

	@Override
	public void setXY(int x, int y) {
		this.setX(x);
		this.setY(y);
	}
	
	@Override
    public void mouseMoved(int oldx, int oldy, int newx, int newy) {
    	inside = this.contains(newx, newy);
    }

	public final boolean isMouseInside() {
		return inside;
	}
	
	
}
