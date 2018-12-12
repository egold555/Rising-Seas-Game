package org.golde.saas.risingseasgame;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.golde.saas.risingseasgame.constants.Constants;
import org.golde.saas.risingseasgame.gamestates.GSPlayGame;
import org.golde.saas.risingseasgame.gamestates.GSTitleScreen;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class RisingSeasGame extends StateBasedGame {

	public RisingSeasGame() {
		super("Rising Seas");
	}

	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		addState(new GSTitleScreen());
		addState(new GSPlayGame());
	}
	
	public static void main(String[] args) {
		try
		{
			AppGameContainer appgc;
			//appgc = new AppGameContainer(new GameS());
			appgc = new AppGameContainer(new RisingSeasGame());
			appgc.setDisplayMode(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT, false); //width, height, fullscreen
			appgc.setTargetFrameRate(Constants.MAX_FPS);
			appgc.start();
		}
		catch (SlickException ex)
		{
			Logger.getLogger(RisingSeasGame.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

}
