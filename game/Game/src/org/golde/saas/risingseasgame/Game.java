package org.golde.saas.risingseasgame;

import org.golde.saas.risingseasgame.objects.ResImage;
import org.golde.saas.risingseasgame.objects.ResImageMoveable;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class Game extends BasicGame {

	ResImage exampleImage;
	ResImageMoveable movingImage;
	
	//Do not put anything in the constructor, use init()
	public Game(){ super("Rising Seas"); }

	@Override //Used to initalise every game object
	public void init(GameContainer gc) throws SlickException {
		exampleImage = new ResImage("swordandshield");
		movingImage = new ResImageMoveable("toast");
	}

	@Override //Used to update game logic
	public void update(GameContainer gc, int i) throws SlickException {
		
		movingImage.setX((float) (movingImage.getX() + 0.01));
		movingImage.setY((float) (movingImage.getY() + 0.01));
		
	}

	@Override //Used to render every element to the screen
	public void render(GameContainer gc, Graphics g) throws SlickException {
		g.drawString("Howdy!", 30, 30);
		exampleImage.draw(10, 50);
		movingImage.draw();
	}

}
