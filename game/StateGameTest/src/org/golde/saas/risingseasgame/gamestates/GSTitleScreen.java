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

	Sprite exampleImage = new Sprite("swordandshield");
	Sprite movingImage = new Sprite("toast");

	MenuButton testButton = new MenuButton("Test Button", 200, 200, 200, 20);

	@Override
	public int getID() {
		return GameStates.TITLE_SCREEN;
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		gameObjects.add(exampleImage.init(gc, sbg));
		exampleImage.setXY(10, 10);
		gameObjects.add(movingImage.init(gc, sbg));
		gameObjects.add(testButton.init(gc, sbg));
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		super.render(gc, sbg, g);
		g.drawString("Game State: TITLE_SCREEN", 30, 30);

	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		super.update(gc, sbg, delta);

		//Testing moving a image on a screen
		movingImage.setXY((float) (movingImage.getX() + 0.1), (float) (movingImage.getY() + 0.1));

	}

	@Override
	public void keyPressed(int key, char c) {
		super.keyPressed(key, c);

		if(key == Keyboard.KEY_ESCAPE) {
			System.exit(0);
		}
		else if(key == Keyboard.KEY_A) {
			getStateBasedGame().enterState(GameStates.PLAY);
		}

	}

}
