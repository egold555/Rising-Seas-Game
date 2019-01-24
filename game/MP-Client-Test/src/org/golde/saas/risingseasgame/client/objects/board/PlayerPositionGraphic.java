package org.golde.saas.risingseasgame.client.objects.board;

import java.util.ArrayList;
import java.util.List;

import org.golde.saas.risingseasgame.client.impl.GameObject;
import org.golde.saas.risingseasgame.client.objects.GameObjectMoveable;
import org.golde.saas.risingseasgame.client.objects.graphics.SolidFill;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
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
	
	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		try {
			PlaceToMove reference = placesToMove.get(pos);
			this.setXY((int)reference.getX() + PlaceToMove.OFFSET, (int)reference.getY() + PlaceToMove.OFFSET);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		g.fill(new Circle(this.getX(), this.getY(), 8), new SolidFill(Color.white));
	}
	
}
