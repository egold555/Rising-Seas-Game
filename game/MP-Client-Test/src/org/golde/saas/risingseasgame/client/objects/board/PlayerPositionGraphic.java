package org.golde.saas.risingseasgame.client.objects.board;

import java.util.ArrayList;
import java.util.List;

import org.golde.saas.risingseasgame.client.event.EventTarget;
import org.golde.saas.risingseasgame.client.event.events.EventRender;
import org.golde.saas.risingseasgame.client.event.events.EventUpdate;
import org.golde.saas.risingseasgame.client.impl.GameObject;
import org.golde.saas.risingseasgame.client.objects.GameObjectMoveable;
import org.golde.saas.risingseasgame.client.objects.graphics.SolidFill;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;

public class PlayerPositionGraphic extends GameObjectMoveable {

	private int pos = 0;
	private List<PlaceToMove> placesToMove = new ArrayList<PlaceToMove>();
	
	public PlayerPositionGraphic(List<PlaceToMove> placesToMove) {
		this.placesToMove = placesToMove;
	}
	
	public final void setPosition(int pos) {
		this.pos = pos;
	}
	
	@Override
	public GameObject init(GameContainer gc) throws SlickException {
		return this;
	}
	
	@EventTarget
	public void update(EventUpdate event) throws SlickException {
		try {
			PlaceToMove reference = placesToMove.get(pos);
			this.setXY((int)reference.getX() + PlaceToMove.OFFSET, (int)reference.getY() + PlaceToMove.OFFSET);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@EventTarget
	public void render(EventRender event) throws SlickException {
		event.getGraphics().fill(new Circle(this.getX(), this.getY(), 8), new SolidFill(Color.white));
	}
	
}
