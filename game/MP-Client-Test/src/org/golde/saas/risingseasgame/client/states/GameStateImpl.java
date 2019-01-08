package org.golde.saas.risingseasgame.client.states;

import org.golde.saas.risingseasgame.client.impl.GameStateObjectSharedFunctions;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

import com.esotericsoftware.kryonet.Connection;

public interface GameStateImpl extends GameStateObjectSharedFunctions {

	public void init(GameContainer gc) throws SlickException;
	public void recievedPacket(Connection c, Object o);
	
}
