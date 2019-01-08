package org.golde.saas.risingseasgame.client.objects;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.golde.saas.risingseasgame.client.MainClient;
import org.golde.saas.risingseasgame.client.objects.graphics.SolidFill;
import org.golde.saas.risingseasgame.client.states.GameStatePlaying;
import org.golde.saas.risingseasgame.shared.packets.PacketInitalizeGameboard;
import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;

public class Gameboard extends Sprite {

	public int waterLevel = 0;

	public Gameboard() {
		super("Gameboard3");
	}

	public List<PlaceToMove> initPlacesToMove(){
		List<PlaceToMove> toReturn = new ArrayList<PlaceToMove>();

		//30
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

		//		List<GameObjectMoveable> toReturnTemp = new ArrayList<GameObjectMoveable>();
		//		for(GameObjectMoveable temp : toReturn) {
		//			toReturnTemp.add(new RandomActionOutline(temp.getX(), temp.getY()));
		//		}
		//		
		//		//ConcurrentModification fix
		//		for(GameObjectMoveable temp : toReturnTemp) {
		//			if(MainClient.RANDOM.nextInt(5) == 0) {
		//				toReturn.add(temp);
		//			}
		//			
		//		}

//		for(PlaceToMove ptm : toReturn) {
//			ptm.isAction = MainClient.RANDOM.nextBoolean();
//		}

		return toReturn;	
	}

	public void initalizeGameboard(PacketInitalizeGameboard p) {
		int count = 0;
		
		int eventSpacesCount = 0;
		
		boolean[] eventSpaces = new boolean[30];
		
		System.out.println("Am I Even being called/");
		for(Field f : PacketInitalizeGameboard.class.getDeclaredFields()) {
			
			if(f.getName().startsWith("eventSpace") && f.getType() == boolean.class) {
				//found boolean field
				try {
					eventSpaces[eventSpacesCount] = f.getBoolean(p);
					eventSpacesCount++;
					System.out.println("Set " + eventSpacesCount + " to " + eventSpaces[eventSpacesCount]);
					
				}
				catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		
		for(GameObject go : GameStatePlaying.INSTANCE.gameObjects) {
			if(go instanceof PlaceToMove) {
				((PlaceToMove)go).isAction = eventSpaces[count];
				count++;
			}
		}
	}

	@Override
	public float getScaleOfImage() {
		return 0.2f;
	}

	@Override
	public void mousePressed(int button, int x, int y) {
		//		if(button == 0) {
		//			GameStatePlaying.INSTANCE.tempGameObject.add(new PlaceToMove(x, y));
		//		}
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		super.render(gc, g); //draw bg
		g.drawString("Water Level: " + waterLevel, 10, 50);
		//draw debug mouse
		//g.fill(new Circle(mouseX, mouseY, 4), new SolidFill(Color.green));
		//g.drawString("X " + mouseX + " Y " + mouseY, mouseX, mouseY);

	}

	//	private class RandomActionOutline extends GameObjectMoveable {
	//		
	//		public RandomActionOutline(int x, int y) {
	//			setXY(x, y);
	//		}
	//
	//		@Override
	//		public GameObject init(GameContainer gc, StateBasedGame sbg) throws SlickException {
	//			return this;
	//		}
	//
	//		@Override
	//		public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
	//			g.setLineWidth(3);
	//			g.draw(new Circle(getX(), getY(), 8), new SolidFill(Color.black));
	//			g.resetLineWidth();
	//		}
	//	}

	public class PlaceToMove extends GameObjectMoveable {

		private boolean isAction = false;

		public PlaceToMove(int x, int y) {
			setXY(x, y);
		}

		//		public void setIsAction(boolean isAction) {
		//			this.isAction = isAction;
		//		}

		public boolean isAction() {
			return isAction;
		}

		@Override
		public GameObject init(GameContainer gc) throws SlickException {
			return this;
		}

		@Override
		public void keyPressed(int key, char c) {
			if(key == Keyboard.KEY_R) {
				for(GameObject go : GameStatePlaying.INSTANCE.gameObjects) {
					if(go instanceof PlaceToMove) {
						((PlaceToMove)go).isAction = false;
					}
				}
				for(GameObject go : GameStatePlaying.INSTANCE.gameObjects) {
					if(go instanceof PlaceToMove) {
						((PlaceToMove)go).isAction = MainClient.RANDOM.nextBoolean();
					}
				}
			}
		}

		@Override
		public void render(GameContainer gc, Graphics g) throws SlickException {
			g.fill(new Circle(getX(), getY(), 4), new SolidFill(Color.red));
			if(isAction) {
				g.setLineWidth(3);
				g.draw(new Circle(getX(), getY(), 8), new SolidFill(Color.black));
				g.resetLineWidth();
			}
		}

	}

}
