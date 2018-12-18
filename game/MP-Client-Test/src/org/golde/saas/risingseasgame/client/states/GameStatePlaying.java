package org.golde.saas.risingseasgame.client.states;

import java.util.ArrayList;
import java.util.List;

import org.golde.saas.risingseasgame.client.objects.Card;
import org.golde.saas.risingseasgame.shared.Logger;
import org.golde.saas.risingseasgame.shared.cards.EnumCircumstanceCards;
import org.golde.saas.risingseasgame.shared.packets.PacketAddPlayer;
import org.lwjgl.input.Keyboard;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import com.esotericsoftware.kryonet.Connection;

public class GameStatePlaying extends GameStateAbstract {
	
	List<Integer> connectedClients = new ArrayList<Integer>();
	
	Card<EnumCircumstanceCards> cardTest = new Card<EnumCircumstanceCards>(EnumCircumstanceCards.ACID_OCEAN);
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		gameObjects.add(cardTest.init(gc, sbg));
	}
	
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

		cardTest.setY(670);
		
		super.render(gc, sbg, g);
	}

	@Override
	public int getID() {
		return GameStates.PLAYING;
	}
	
	@Override
	public void keyPressed(int key, char c) {
		super.keyPressed(key, c);
		if(key == Keyboard.KEY_ESCAPE) {
			System.exit(0);
		}
	}

}
