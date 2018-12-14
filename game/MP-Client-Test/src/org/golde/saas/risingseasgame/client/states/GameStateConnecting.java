package org.golde.saas.risingseasgame.client.states;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class GameStateConnecting extends GameStateAbstract {
	
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		
		g.drawString("Connecting.......", 30, 30);
		
		super.render(gc, sbg, g);
	}
	
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		if(getNetwork().isConnected()) {
			sbg.enterState(GameStates.PLAYING);
		}
	}

	@Override
	public int getID() {
		return GameStates.CONNECTING;
	}

}
