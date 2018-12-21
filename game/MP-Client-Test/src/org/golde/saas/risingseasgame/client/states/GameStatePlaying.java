package org.golde.saas.risingseasgame.client.states;

import java.awt.Font;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.golde.saas.risingseasgame.client.ConstantsClient;
import org.golde.saas.risingseasgame.client.objects.Card;
import org.golde.saas.risingseasgame.client.objects.GameObject;
import org.golde.saas.risingseasgame.client.objects.Gameboard;
import org.golde.saas.risingseasgame.client.objects.graphics.WaterLevelCircle;
import org.golde.saas.risingseasgame.shared.Logger;
import org.golde.saas.risingseasgame.shared.cards.EnumCircumstanceCards;
import org.golde.saas.risingseasgame.shared.packets.PacketAddPlayer;
import org.golde.saas.risingseasgame.shared.packets.PacketInitalizeGameboard;
import org.golde.saas.risingseasgame.shared.packets.PacketSetWater;
import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.StateBasedGame;

import com.esotericsoftware.kryonet.Connection;

public class GameStatePlaying extends GameStateAbstract {
	
	List<Integer> connectedClients = new ArrayList<Integer>();
	
	List<Card<EnumCircumstanceCards>> listOfTestCards = new ArrayList<Card<EnumCircumstanceCards>>();
	
	private Gameboard gameBoard = new Gameboard();
	
	public static GameStatePlaying INSTANCE;
	
	public List<GameObject> tempGameObject = new ArrayList<GameObject>(); //so we can push more game objects to the screen without a concurrent modification exception
	
	public TrueTypeFont ttf;
	
	//WaterLevelCircle WaterLevelCircle = new WaterLevelCircle();
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		INSTANCE = this;
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
		
		Font font = new Font("Helvetica", Font.PLAIN, 14);
		ttf = new TrueTypeFont(font, true);
		//gameObjects.add(WaterLevelCircle.init(gc, sbg));
		//Collections.sort(gameObjects);
		
	}
	
	@Override
	public void received(Connection c, Object o) {
		Logger.info("Recieved: " + o.getClass().getSimpleName());
		if(o instanceof PacketAddPlayer) {
			PacketAddPlayer packet = (PacketAddPlayer)o;
			connectedClients.add(packet.id);
		}
		else if(o instanceof PacketSetWater) {
			PacketSetWater packet = (PacketSetWater)o;
			gameBoard.waterLevel = packet.waterLevel;
		}
		
		else if(o instanceof PacketInitalizeGameboard) {
			gameBoard.initalizeGameboard((PacketInitalizeGameboard)o);
		}
	}
	
//	public void drawText(Graphics g, String text, int x, int y, Color color) {
//		g.pushTransform();
//		g.resetTransform();
//		g.setColor(color);
//		g.drawString(text, x, y);
//		g.popTransform();
//	}
	
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		
		//g.setFont(ttf);
		scaleRenderer(gc, sbg, g);
		
		for(GameObject temp : tempGameObject) {
			gameObjects.add(temp);
		}
		
		tempGameObject.clear();
		
		
		  //new render method thats abstract
		int idCountX = 0;
		for(int id : connectedClients) {
			g.drawString(String.valueOf(id), 30 + idCountX, 60);
			idCountX += 10;
		} 
		
		gameBoard.setX((ConstantsClient.WINDOW_WIDTH / 2) - gameBoard.getImage().getWidth());
		//WaterLevelCircle.setXY(0, 0);
		
		g.setBackground(ConstantsClient.WATER_COLOR);
		
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
