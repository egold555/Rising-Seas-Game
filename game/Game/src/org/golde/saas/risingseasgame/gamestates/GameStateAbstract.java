package org.golde.saas.risingseasgame.gamestates;

import java.util.ArrayList;
import java.util.List;

import org.golde.saas.risingseasgame.objects.GameObject;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public abstract class GameStateAbstract extends BasicGameState {

	protected final List<GameObject> gameObjects = new ArrayList<GameObject>();
	
	private GameContainer container;
	private StateBasedGame stateBasedGame;

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

	public final GameContainer getGameContainer() {
		return container;
	}

	public final StateBasedGame getStateBasedGame() {
		return stateBasedGame;
	}

}
