package org.golde.saas.risingseasgame.client;

import java.awt.Dimension;
import java.awt.Toolkit;

import org.golde.saas.risingseasgame.client.states.GameStateConnecting;
import org.golde.saas.risingseasgame.client.states.GameStatePlaying;
import org.golde.saas.risingseasgame.shared.Logger;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class MainClient extends StateBasedGame {

	private Network network = new Network();
	
	private static MainClient INSTANCE;
	
	private static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	
	public MainClient() {
		super("MultiPlayer Client test");
		INSTANCE = this;
	}
	
	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		addState(new GameStateConnecting());
		addState(new GameStatePlaying());
		network.connect();
	}
	
	public static void main(String[] args) {
		try {
			AppGameContainer appgc;
			//appgc = new AppGameContainer(new GameS());
			appgc = new AppGameContainer(new MainClient());
			appgc.setDisplayMode(ConstantsClient.WINDOW_WIDTH, ConstantsClient.WINDOW_HEIGHT, true); //width, height, fullscreen
			appgc.setTargetFrameRate(ConstantsClient.MAX_FPS);
			appgc.setAlwaysRender(true);
			appgc.start();
		}
		catch (SlickException ex) {
			Logger.error(ex, "Failed to create game instance");
		}
	}
	
	public static MainClient getInstance() {
		return INSTANCE;
	}
	
	public Network getNetwork() {
		return network;
	}

}
