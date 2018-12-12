package org.golde.saas.risingseasgame.gamestates;

import org.golde.saas.risingseasgame.constants.GameStates;
import org.lwjgl.input.Keyboard;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class GSPlayGame extends GameStateAbstract {

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		super.init(gc, sbg);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		g.drawString("Game State: PLAY", 30, 30);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		
	}

	@Override
	public int getID() {
		return GameStates.PLAY;
	}
	
	@Override
	public void keyPressed(int key, char c) {
		if(key == Keyboard.KEY_A) {
			getStateBasedGame().enterState(GameStates.TITLE_SCREEN);
		}
	}

}
