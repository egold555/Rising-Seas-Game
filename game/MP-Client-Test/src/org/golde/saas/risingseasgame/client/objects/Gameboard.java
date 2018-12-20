package org.golde.saas.risingseasgame.client.objects;

import java.util.ArrayList;
import java.util.List;

import org.golde.saas.risingseasgame.client.MainClient;
import org.golde.saas.risingseasgame.client.objects.graphics.SolidFill;
import org.golde.saas.risingseasgame.client.states.GameStatePlaying;
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

	public List<GameObjectMoveable> initPlacesToMove(){
		List<GameObjectMoveable> toReturn = new ArrayList<GameObjectMoveable>();

		toReturn.add(new PlaceToMove(728, 406));
		toReturn.add(new PlaceToMove(651, 379));
		toReturn.add(new PlaceToMove(597, 321));
		toReturn.add(new PlaceToMove(580, 235));
		toReturn.add(new PlaceToMove(600, 172));
		toReturn.add(new PlaceToMove(650, 118));
		
		toReturn.add(new PlaceToMove(726, 92));
		
		toReturn.add(new PlaceToMove(821, 119));
		toReturn.add(new PlaceToMove(872, 166));
		toReturn.add(new PlaceToMove(887, 235));
		toReturn.add(new PlaceToMove(865, 301));
		toReturn.add(new PlaceToMove(803, 350));
		
		toReturn.add(new PlaceToMove(733, 365));
		
		toReturn.add(new PlaceToMove(669, 342));
		toReturn.add(new PlaceToMove(630, 297));
		toReturn.add(new PlaceToMove(619, 236));
		toReturn.add(new PlaceToMove(653, 163));
		toReturn.add(new PlaceToMove(727, 129));
		toReturn.add(new PlaceToMove(814, 158));
		toReturn.add(new PlaceToMove(847, 234));
		toReturn.add(new PlaceToMove(814, 296));
		
		toReturn.add(new PlaceToMove(733, 322));
		
		toReturn.add(new PlaceToMove(673, 293));
		toReturn.add(new PlaceToMove(656, 236));
		toReturn.add(new PlaceToMove(675, 188));
		toReturn.add(new PlaceToMove(730, 163));
		toReturn.add(new PlaceToMove(796, 186));
		toReturn.add(new PlaceToMove(808, 235));
		toReturn.add(new PlaceToMove(780, 267));
		toReturn.add(new PlaceToMove(730, 281));
		
		List<GameObjectMoveable> toReturnTemp = new ArrayList<GameObjectMoveable>();
		for(GameObjectMoveable temp : toReturn) {
			toReturnTemp.add(new RandomActionOutline(temp.getX(), temp.getY()));
		}
		
		//ConcurrentModification fix
		for(GameObjectMoveable temp : toReturnTemp) {
			if(MainClient.RANDOM.nextInt(5) == 0) {
				toReturn.add(temp);
			}
			
		}
		
		return toReturn;	
	}

	@Override
	public float getScaleOfImage() {
		return 0.2f;
	}

	@Override
	public void mousePressed(int button, int x, int y) {
		if(button == 0) {
			GameStatePlaying.INSTANCE.tempGameObject.add(new PlaceToMove(x, y));
		}
	}
	
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		super.render(gc, sbg, g); //draw bg
		
		//draw debug mouse
		//g.fill(new Circle(mouseX, mouseY, 4), new SolidFill(Color.green));
		//g.drawString("X " + mouseX + " Y " + mouseY, mouseX, mouseY);
		
		
	}
	
	private class RandomActionOutline extends GameObjectMoveable {
		
		public RandomActionOutline(int x, int y) {
			setXY(x, y);
		}

		@Override
		public GameObject init(GameContainer gc, StateBasedGame sbg) throws SlickException {
			return this;
		}

		@Override
		public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
			g.setLineWidth(3);
			g.draw(new Circle(getX(), getY(), 8), new SolidFill(Color.black));
			g.resetLineWidth();
		}
	}

	private class PlaceToMove extends GameObjectMoveable {

		public PlaceToMove(int x, int y) {
			setXY(x, y);
		}

		@Override
		public GameObject init(GameContainer gc, StateBasedGame sbg) throws SlickException {
			return this;
		}

		@Override
		public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
			g.fill(new Circle(getX(), getY(), 4), new SolidFill(Color.red));
		}

	}

}
