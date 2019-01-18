package org.golde.saas.risingseasgame.client.states;

import java.util.ArrayList;
import java.util.List;

import org.golde.saas.risingseasgame.client.ConstantsClient;
import org.golde.saas.risingseasgame.client.MainClient;
import org.golde.saas.risingseasgame.client.Network;
import org.golde.saas.risingseasgame.client.impl.GameObject;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import com.esotericsoftware.kryonet.Connection;

public abstract class GameStateAbstract implements GameStateImpl {

	public final List<GameObject> gameObjects = new ArrayList<GameObject>();
	
	private GameContainer container;
	
	public void init(GameContainer gc) throws SlickException {}

	public void scaleRenderer(GameContainer gc, Graphics g) throws SlickException {
		g.scale(ConstantsClient.WINDOW_WIDTH / ConstantsClient.COORD_SYS_WIDTH,  ConstantsClient.WINDOW_HEIGHT / ConstantsClient.COORD_SYS_HEIGHT);
	}
	
	public void scaleInput(GameContainer gc, int delta) {
		gc.getInput().setScale(ConstantsClient.COORD_SYS_WIDTH / ConstantsClient.WINDOW_WIDTH, ConstantsClient.COORD_SYS_HEIGHT / ConstantsClient.WINDOW_HEIGHT);
	}
	
	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		for(GameObject obj : gameObjects) {
			if(obj.isVisable()) { obj.render(gc, g); }
		}
	}
	
	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		for(GameObject obj : gameObjects) {
			obj.update(gc, delta);
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
	
	@Override
	public void recievedPacket(Connection c, Object o) {}

	public final GameContainer getGameContainer() {
		return container;
	}
	
	public final MainClient getMainClient(){
		return MainClient.getInstance();
	}
	
	public Network getNetwork() {
		return MainClient.getInstance().getNetwork();
	}
	
	public abstract EnumGameState getEnumGameState();

}

