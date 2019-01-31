package org.golde.saas.risingseasgame.client.objects;

import org.golde.saas.risingseasgame.client.event.EventTarget;
import org.golde.saas.risingseasgame.client.event.events.mouse.EventMouseMoved;
import org.golde.saas.risingseasgame.client.impl.GameObject;
import org.golde.saas.risingseasgame.client.impl.IGameObjectMoveable;
import org.golde.saas.risingseasgame.client.objects.graphics.SolidFill;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.RoundedRectangle;

@SuppressWarnings("serial")
public abstract class GameObjectClickable extends RoundedRectangle implements GameObject, IGameObjectMoveable {

	boolean inside = false;

	private boolean visable = true;

	public GameObjectClickable(float x, float y, float width, float height) {
		super(x, y, width, height, 4);
	}
	
	@Override
	public GameObject init(GameContainer gc) throws SlickException {
		checkPoints(); //Not sure if I need to call this but the constructor does so Ill do it to
		return this;
	}

	@Override
	public void setX(int x) {
		createPoints();
		super.setX(x);
	}

	@Override
	public void setY(int y) {
		createPoints();
		super.setY(y);
	}
	
	@EventTarget
	public void mouseMoved(EventMouseMoved event) {
		inside = this.contains(event.getNewX(), event.getNewY());
	}

	public final boolean isMouseInside() {
		return inside;
	}

	public final void drawDebugHitbox(Graphics g) {
		g.draw(this, new SolidFill(isMouseInside() ? Color.green : Color.blue));
	}

	@Override
	public boolean isVisable() {
		return this.visable;
	}
	
	@Override
	public void setVisable(boolean visable) {
		this.visable = visable;
	}
	
}
