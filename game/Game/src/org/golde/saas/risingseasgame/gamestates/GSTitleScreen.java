package org.golde.saas.risingseasgame.gamestates;

import org.golde.saas.risingseasgame.constants.GameStates;
import org.golde.saas.risingseasgame.objects.MenuButton;
import org.golde.saas.risingseasgame.objects.Sprite;
import org.lwjgl.input.Keyboard;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class GSTitleScreen extends GameStateAbstract {
	
	Sprite exampleImage;
	Sprite movingImage;
	
	MenuButton testButton;
	
	@Override //Used to initalise every game object
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		exampleImage = new Sprite("swordandshield");
		exampleImage.setXY(10, 10);
		movingImage = new Sprite("toast");
		
		testButton = new MenuButton("Test Button", 200, 200, 200, 20);
	}

	@Override //Used to render every element to the screen
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		g.drawString("Game State: TITLE_SCREEN", 30, 30);
		exampleImage.draw(g);
		movingImage.draw(g);
		testButton.draw(g);
	}

	@Override //Used to update game logic
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		
		//Testing moving a image on a screen
		movingImage.setXY((float) (movingImage.getX() + 0.1), (float) (movingImage.getY() + 0.1));
		
	}

	@Override //This should always be GameStates
	public int getID() {
		return GameStates.TITLE_SCREEN;
	}
	
	@Override
	public void keyPressed(int key, char c) {
		
		if(key == Keyboard.KEY_ESCAPE) {
			System.exit(0);
		}
		else if(key == Keyboard.KEY_A) {
			getStateBasedGame().enterState(GameStates.PLAY);
		}

	}
	
	@Override
	public void mouseMoved(int oldx, int oldy, int newx, int newy) {
		testButton.mouseMoved(newx, newy);
	}
	
	@Override
	public void mouseClicked(int button, int x, int y, int clickCount) {
		testButton.mouseClicked(button, x, y);
	}

}
