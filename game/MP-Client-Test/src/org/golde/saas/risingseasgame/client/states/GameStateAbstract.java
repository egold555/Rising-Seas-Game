package org.golde.saas.risingseasgame.client.states;

import java.util.ArrayList;
import java.util.List;

import org.golde.saas.risingseasgame.client.ConstantsClient;
import org.golde.saas.risingseasgame.client.MainClient;
import org.golde.saas.risingseasgame.client.Network;
import org.golde.saas.risingseasgame.client.objects.GameObject;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import com.esotericsoftware.kryonet.Connection;

public abstract class GameStateAbstract extends BasicGameState {

	public final List<GameObject> gameObjects = new ArrayList<GameObject>();
	
	private GameContainer container;
	private StateBasedGame stateBasedGame;
	
	@Override
	public abstract void init(GameContainer gc, StateBasedGame sbg) throws SlickException;

	public void scaleRenderer(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		g.scale(ConstantsClient.WINDOW_WIDTH / ConstantsClient.COORD_SYS_WIDTH,  ConstantsClient.WINDOW_HEIGHT / ConstantsClient.COORD_SYS_HEIGHT);
	}
	
	public void scaleInput(GameContainer gc, StateBasedGame sbg, int delta) {
		gc.getInput().setScale(ConstantsClient.COORD_SYS_WIDTH / ConstantsClient.WINDOW_WIDTH, ConstantsClient.COORD_SYS_HEIGHT / ConstantsClient.WINDOW_HEIGHT);
	}
	
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		for(GameObject obj : gameObjects) {
			obj.render(gc, sbg, g);
		}
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		for(GameObject obj : gameObjects) {
			obj.update(gc, sbg, delta);
		}
	}

	@Override
	public void enter(GameContainer gc, StateBasedGame sbg) throws SlickException {
		this.container = gc;
		this.stateBasedGame = sbg;
		for(GameObject obj : gameObjects) {
			obj.enter(gc, sbg);
		}
	}

	@Override
	public void leave(GameContainer container, StateBasedGame game) throws SlickException {
		this.container = null;
		this.stateBasedGame = null;
		for(GameObject obj : gameObjects) {
			obj.leave(container, game);
		}
	}

	@Override
	public void keyPressed(int key, char c) {
		for(GameObject obj : gameObjects) {
			obj.keyPressed(key, c);
		}
	}

	@Override
	public void keyReleased(int key, char c) {
		for(GameObject obj : gameObjects) {
			obj.keyReleased(key, c);
		}
	}

	@Override
	public void mouseClicked(int button, int x, int y, int clickCount) {
		for(GameObject obj : gameObjects) {
			obj.mouseClicked(button, x, y, clickCount);
		}
	}

	@Override
	public void mouseWheelMoved(int newValue) {
		for(GameObject obj : gameObjects) {
			obj.mouseWheelMoved(newValue);
		}
	}

	@Override
	public void mouseDragged(int oldx, int oldy, int newx, int newy) {
		for(GameObject obj : gameObjects) {
			obj.mouseDragged(oldx, oldy, newx, newy);
		}
	}

	@Override
	public void mouseMoved(int oldx, int oldy, int newx, int newy) {
		for(GameObject obj : gameObjects) {
			obj.mouseMoved(oldx, oldy, newx, newy);
		}
	}

	@Override
	public void mousePressed(int button, int x, int y) {
		for(GameObject obj : gameObjects) {
			obj.mousePressed(button, x, y);
		}
	}

	@Override
	public void mouseReleased(int button, int x, int y) {
		for(GameObject obj : gameObjects) {
			obj.mouseReleased(button, x, y);
		}
	}
	
	public void received(Connection c, Object o) {
		
	}

	public final GameContainer getGameContainer() {
		return container;
	}

	public final StateBasedGame getStateBasedGame() {
		return stateBasedGame;
	}
	
	public Network getNetwork() {
		return MainClient.getInstance().getNetwork();
	}

}

