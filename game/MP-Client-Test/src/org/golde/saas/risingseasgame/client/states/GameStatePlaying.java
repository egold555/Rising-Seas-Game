package org.golde.saas.risingseasgame.client.states;

import java.util.ArrayList;
import java.util.List;

import org.golde.saas.risingseasgame.client.ConstantsClient;
import org.golde.saas.risingseasgame.client.objects.Card;
import org.golde.saas.risingseasgame.client.objects.GameObject;
import org.golde.saas.risingseasgame.client.objects.Gameboard;
import org.golde.saas.risingseasgame.shared.Logger;
import org.golde.saas.risingseasgame.shared.cards.EnumCircumstanceCards;
import org.golde.saas.risingseasgame.shared.packets.PacketAddPlayer;
import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import com.esotericsoftware.kryonet.Connection;

public class GameStatePlaying extends GameStateAbstract {
	
	List<Integer> connectedClients = new ArrayList<Integer>();
	
	List<Card<EnumCircumstanceCards>> listOfTestCards = new ArrayList<Card<EnumCircumstanceCards>>();
	
	Gameboard gameBoard = new Gameboard();
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		
		gameObjects.add(gameBoard.init(gc, sbg));
		
		for(GameObject go : gameBoard.initPlacesToMove()) {
			gameObjects.add(go.init(gc, sbg));
		}
		
		for(int i = 0; i < 6; i++) {
			Card<EnumCircumstanceCards> card = new Card<EnumCircumstanceCards>(EnumCircumstanceCards.ACID_OCEAN);
			gameObjects.add(card.init(gc, sbg));
			card.setY(Card.Y_HAND);
			card.setCardIndex(i);
		}
		scaleInput(gc, sbg, -1);
		
	}
	
	@Override
	public void received(Connection c, Object o) {
		Logger.info("I got it! " + o.getClass().getSimpleName());
		if(o instanceof PacketAddPlayer) {
			PacketAddPlayer packet = (PacketAddPlayer)o;
			connectedClients.add(packet.id);
		}
	}
	
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		scaleRenderer(gc, sbg, g); //new render method thats abstract
		g.drawString("PLAYING!", 30, 30);
		g.drawString("Connected Ids: " + connectedClients.size(), 30, 50);
		int idCountX = 0;
		for(int id : connectedClients) {
			g.drawString(String.valueOf(id), 30 + idCountX, 60);
			idCountX += 10;
		} 
		
		gameBoard.setX((ConstantsClient.WINDOW_WIDTH / 2) - gameBoard.getImage().getWidth() + 200);
		
		g.setBackground(new Color(66, 167, 187));
		
		super.render(gc, sbg, g);
	}
	
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		super.update(gc, sbg, delta);
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
