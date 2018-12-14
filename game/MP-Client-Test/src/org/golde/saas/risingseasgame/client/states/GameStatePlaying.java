package org.golde.saas.risingseasgame.client.states;

import java.util.ArrayList;
import java.util.List;

import org.golde.saas.risingseasgame.shared.Logger;
import org.golde.saas.risingseasgame.shared.packets.PacketAddPlayer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import com.esotericsoftware.kryonet.Connection;

public class GameStatePlaying extends GameStateAbstract {
	
	List<Integer> connectedClients = new ArrayList<Integer>();
	
	@Override
	public void received(Connection c, Object o) {
		Logger.info("I got it! " + o.getClass().getSimpleName());
		if(o instanceof PacketAddPlayer) {
			PacketAddPlayer packet = (PacketAddPlayer)o;
			connectedClients.add(packet.id);
			Logger.info("Yeah I added it to ze list");
		}
	}
	
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		g.drawString("PLAYING!", 30, 30);
		
		g.drawString("Connected Ids: " + connectedClients.size(), 30, 50);
		int idCountX = 0;
		for(int id : connectedClients) {
			g.drawString(String.valueOf(id), 30 + idCountX, 60);
			idCountX += 10;
		} 

		super.render(gc, sbg, g);
	}

	@Override
	public int getID() {
		return GameStates.PLAYING;
	}

}
