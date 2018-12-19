package org.golde.saas.risingseasgame.client.objects;

import java.util.ArrayList;
import java.util.List;

import org.golde.saas.risingseasgame.client.objects.graphics.SolidFill;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.state.StateBasedGame;

public class Gameboard extends Sprite {

	public Gameboard() {
		super("Gameboard3");
	}

	public List<PlaceToMove> initPlacesToMove(){
		List<PlaceToMove> toReturn = new ArrayList<PlaceToMove>();
		
		toReturn.add(new PlaceToMove(200,60));
		
		return toReturn;	
	}

	@Override
	public float getScaleOfImage() {
		return 0.2f;
	}

	private class PlaceToMove extends GameObjectMoveable {

		int mouseX = 0, mouseY = 0;
		
		public PlaceToMove(int x, int y) {
			setXY(x, y);
		}
		
		@Override
		public GameObject init(GameContainer gc, StateBasedGame sbg) throws SlickException {
			return this;
		}

		@Override
		public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
			g.fill(new Circle(mouseX, mouseY, 4), new SolidFill(Color.green));
			g.drawString("X " + mouseX + " Y " + mouseY, mouseX, mouseY);
			g.fill(new Circle(getX(), getY(), 4), new SolidFill(Color.red));
			super.render(gc, sbg, g);
		}
		
		@Override
		public void mouseMoved(int oldx, int oldy, int newx, int newy) {
			mouseX = newx;
			mouseY = newy;
		}

	}

}
