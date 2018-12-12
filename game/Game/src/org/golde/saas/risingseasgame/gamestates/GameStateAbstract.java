package org.golde.saas.risingseasgame.gamestates;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public abstract class GameStateAbstract extends BasicGameState {

	private GameContainer container;
	private StateBasedGame stateBasedGame;
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		this.container = gc;
		this.stateBasedGame = sbg;
	}
	
	public final GameContainer getContainer() {
		return container;
	}
	
	public final StateBasedGame getStateBasedGame() {
		return stateBasedGame;
	}

}
