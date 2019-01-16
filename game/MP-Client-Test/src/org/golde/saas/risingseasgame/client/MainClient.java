package org.golde.saas.risingseasgame.client;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.golde.saas.risingseasgame.client.states.EnumGameState;
import org.golde.saas.risingseasgame.client.states.GameStateAbstract;
import org.golde.saas.risingseasgame.client.states.GameStateConnecting;
import org.golde.saas.risingseasgame.client.states.GameStateImpl;
import org.golde.saas.risingseasgame.client.states.GameStatePlaying;
import org.golde.saas.risingseasgame.shared.Logger;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Game;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import com.esotericsoftware.kryonet.Connection;

public class MainClient extends BasicGame implements GameStateImpl {

	private Network network = new Network();
	
	private static MainClient INSTANCE;
	
	public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	
	public static final Random RANDOM = new Random();
	
	private EnumGameState GAME_STATE = EnumGameState.TITLE_SCREEN;
	
	private List<GameStateAbstract> everyGameState = new ArrayList<GameStateAbstract>();
	
	public MainClient() {
		super("MultiPlayer Client test");
		INSTANCE = this;
		
		everyGameState.add(new GameStateConnecting());
		everyGameState.add(new GameStatePlaying());
		
		changeState(EnumGameState.TITLE_SCREEN);
		
	}
	
	public static void main(String[] args) {
		try {
			AppGameContainer appgc;
			//appgc = new AppGameContainer(new GameS());
			appgc = new AppGameContainer(new MainClient());
			appgc.setDisplayMode(ConstantsClient.WINDOW_WIDTH, ConstantsClient.WINDOW_HEIGHT, false); //width, height, fullscreen
			appgc.setTargetFrameRate(ConstantsClient.MAX_FPS);
			//appgc.setAlwaysRender(true);
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
	
	public void changeState(EnumGameState newState) {
		Logger.info("Changed game state from " + GAME_STATE.name() + " to " + newState.name());
		this.GAME_STATE = newState;
	}
	
	public GameStateAbstract getGameState(EnumGameState getState) {
		for(GameStateAbstract theGameState : everyGameState) {
			if(theGameState.getEnumGameState() == getState) {
				return theGameState;
			}
		}
		return null;
	}
	
	@Override
	public void init(GameContainer gc) throws SlickException {
		for(GameStateAbstract gs : everyGameState) {
			gs.init(gc);
		}
		network.connect();
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		for(GameStateAbstract gs : everyGameState) {
			if(gs.getEnumGameState() == GAME_STATE) {
				gs.render(gc, g);
			}
		}
	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		for(GameStateAbstract gs : everyGameState) {
			if(gs.getEnumGameState() == GAME_STATE) {
				gs.update(gc, delta);
			}
		}
	}
	
	@Override
	public void keyPressed(int key, char c) {
		for(GameStateAbstract gs : everyGameState) {
			if(gs.getEnumGameState() == GAME_STATE) {
				gs.keyPressed(key, c);
			}
		}
	}
	
	@Override
	public void keyReleased(int key, char c) {
		for(GameStateAbstract gs : everyGameState) {
			if(gs.getEnumGameState() == GAME_STATE) {
				gs.keyReleased(key, c);
			}
		}
	}
	
	@Override
	public void mouseClicked(int button, int x, int y, int clickCount) {
		for(GameStateAbstract gs : everyGameState) {
			if(gs.getEnumGameState() == GAME_STATE) {
				gs.mouseClicked(button, x, y, clickCount);
			}
		}
	}
	
	@Override
	public void mouseDragged(int oldx, int oldy, int newx, int newy) {
		for(GameStateAbstract gs : everyGameState) {
			if(gs.getEnumGameState() == GAME_STATE) {
				gs.mouseDragged(oldx, oldy, newx, newy);
			}
		}
	}
	
	@Override
	public void mouseMoved(int oldx, int oldy, int newx, int newy) {
		for(GameStateAbstract gs : everyGameState) {
			if(gs.getEnumGameState() == GAME_STATE) {
				gs.mouseMoved(oldx, oldy, newx, newy);
			}
		}
	}
	
	@Override
	public void mousePressed(int button, int x, int y) {
		for(GameStateAbstract gs : everyGameState) {
			if(gs.getEnumGameState() == GAME_STATE) {
				gs.mousePressed(button, x, y);
			}
		}
	}
	
	@Override
	public void mouseReleased(int button, int x, int y) {
		for(GameStateAbstract gs : everyGameState) {
			if(gs.getEnumGameState() == GAME_STATE) {
				gs.mouseReleased(button, x, y);
			}
		}
	}
	
	@Override
	public void mouseWheelMoved(int change) {
		for(GameStateAbstract gs : everyGameState) {
			if(gs.getEnumGameState() == GAME_STATE) {
				gs.mouseWheelMoved(change);
			}
		}
	}
	
	@Override
	public void recievedPacket(Connection c, Object o) {
		for(GameStateAbstract gs : everyGameState) {
			if(gs.getEnumGameState() == GAME_STATE) {
				gs.recievedPacket(c, o);
			}
		}
	}

}
