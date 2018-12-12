package org.golde.saas.risingseasgame.gamestates;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public abstract class GameStateAbstract extends BasicGameState {

	private GameContainer container;
	private StateBasedGame stateBasedGame;

	@Override
	public void enter(GameContainer gc, StateBasedGame sbg) throws SlickException {
		this.container = gc;
		this.stateBasedGame = sbg;
	}
	
	@Override
	public void leave(GameContainer container, StateBasedGame game) throws SlickException {
		this.container = null;
		this.stateBasedGame = null;
	}

	public final GameContainer getGameContainer() {
		return container;
	}

	public final StateBasedGame getStateBasedGame() {
		return stateBasedGame;
	}

}
