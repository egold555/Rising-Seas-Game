package org.golde.saas.risingseasgame.client.objects.board;

import java.util.ArrayList;
import java.util.List;

import org.golde.saas.risingseasgame.client.helper.BetterTrueTypeFont;
import org.golde.saas.risingseasgame.client.helper.FontManager;
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
	private final int id;
	private final Color drawColor;
	
	public PlayerPositionGraphic(List<PlaceToMove> placesToMove, int id) {
		this.placesToMove = placesToMove;
		this.id = id;
		this.drawColor = getColor(id);
	}
	
	public final void setPosition(int pos) {
		this.pos = pos;
	}
	
	public int getId() {
		return id;
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
		BetterTrueTypeFont ttf = FontManager.getOrCreateFont(14);
		g.fill(new Circle(this.getX(), this.getY(), 8), new SolidFill(drawColor));
		ttf.drawString(this.getX()- PlaceToMove.OFFSET, this.getY() - PlaceToMove.OFFSET, "" + id);
	}
	
	private static Color getColor(int idIn) {
		switch(idIn) {
		case 1: return Color.red;
		case 2: return Color.green;
		case 3: return Color.blue;
		case 4: return Color.magenta;
		default: return Color.white;
		}
	}
	
}
