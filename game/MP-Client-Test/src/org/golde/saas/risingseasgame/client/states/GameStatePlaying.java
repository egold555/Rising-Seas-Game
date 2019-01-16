package org.golde.saas.risingseasgame.client.states;

import java.awt.Font;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.golde.saas.risingseasgame.client.ConstantsClient;
import org.golde.saas.risingseasgame.client.impl.GameObject;
import org.golde.saas.risingseasgame.client.objects.Card;
import org.golde.saas.risingseasgame.client.objects.Gameboard;
import org.golde.saas.risingseasgame.client.objects.graphics.DialogBox;
import org.golde.saas.risingseasgame.shared.Logger;
import org.golde.saas.risingseasgame.shared.cards.EnumCircumstanceCards;
import org.golde.saas.risingseasgame.shared.cards.EnumDiplomaticStrategies;
import org.golde.saas.risingseasgame.shared.cards.EnumPowerCards;
import org.golde.saas.risingseasgame.shared.packets.PacketAddPlayer;
import org.golde.saas.risingseasgame.shared.packets.PacketInitalizeGameboard;
import org.golde.saas.risingseasgame.shared.packets.PacketSetCards;
import org.golde.saas.risingseasgame.shared.packets.PacketSetWater;
import org.lwjgl.input.Keyboard;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;

import com.esotericsoftware.kryonet.Connection;

public class GameStatePlaying extends GameStateAbstract {

	List<Integer> connectedClients = new ArrayList<Integer>();

	private Gameboard gameBoard = new Gameboard();

	public static GameStatePlaying INSTANCE;

	public List<GameObject> tempGameObject = new ArrayList<GameObject>(); //so we can push more game objects to the screen without a concurrent modification exception

	public TrueTypeFont ttf;

	List<Card> cards = new ArrayList<Card>();

	//WaterLevelCircle WaterLevelCircle = new WaterLevelCircle();
	
	DialogBox dBox = new DialogBox();
	
	private boolean canSelectCard = true;
	private static final int MAX_CARDS_SELECTABLE = 4;

	@Override
	public void init(GameContainer gc) throws SlickException {
		INSTANCE = this;
		gameObjects.add(gameBoard.init(gc));
		

		for(GameObject go : gameBoard.initPlacesToMove()) {
			gameObjects.add(go.init(gc));
		}

		for(int i = 0; i < 7; i++) {
			Card<EnumCircumstanceCards> card = new Card<EnumCircumstanceCards>(EnumCircumstanceCards.values()[i]);
			gameObjects.add(card.init(gc));
			card.setY(Card.Y_HAND);
			card.setCardIndex(i);
			cards.add(card);
		}
		scaleInput(gc, -1);

		Font font = new Font("Helvetica", Font.PLAIN, 14);
		ttf = new TrueTypeFont(font, true);

		dBox.init(gc);
		
	}

	@Override
	public void recievedPacket(Connection c, Object o) {

		if(o instanceof PacketAddPlayer) {
			PacketAddPlayer packet = (PacketAddPlayer)o;
			connectedClients.add(packet.id);
		}
		else if(o instanceof PacketSetWater) {
			PacketSetWater packet = (PacketSetWater)o;
			gameBoard.waterLevel = packet.waterLevel;
		}

		else if(o instanceof PacketInitalizeGameboard) {
			System.out.println("I am calling the method");
			gameBoard.initalizeGameboard((PacketInitalizeGameboard)o);
		}

		else if(o instanceof PacketSetCards) {
			setCards((PacketSetCards)o);
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {

		//g.setFont(ttf);
		scaleRenderer(gc, g);

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
		
		super.render(gc, g);
		
		//dBox.render(gc, g);

		
		
		
	}
	
	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		int selected = 0;
		canSelectCard = true;
		for(GameObject go : gameObjects) {
			if(go instanceof Card) {
				Card card = (Card)go;
				if(card.isSelected()) {
					selected++;
					if(selected > MAX_CARDS_SELECTABLE - 1) { //1 - the amount of cards you want to select. THIS IS A WORKAROUND IDK IT JUST WORKS DONT TOUCH
						canSelectCard = false;
					}
				}
			}
		}
	}

	@Override
	public void keyPressed(int key, char c) {
		super.keyPressed(key, c);
		if(key == Keyboard.KEY_ESCAPE) {
			System.exit(0);
		}
	}

	@Override
	public EnumGameState getEnumGameState() {
		return EnumGameState.PLAYING;
	}

	private void setCards(PacketSetCards p) {
		int cardNum = -1;
		for(Field f : p.getClass().getDeclaredFields()) {
			if(f.getName().startsWith("card") && f.getType() == String.class) {

				String val;
				try {
					val = (String)f.get(p);

					Enum impl = null;

					try {
						impl = EnumPowerCards.valueOf(val);
						cardNum++;
						Logger.info("Set type to EnumPowerCards");
					}
					catch(Exception ignored1) {

						try {
							impl = EnumCircumstanceCards.valueOf(val);
							cardNum++;
							Logger.info("Set type to EnumCircumstanceCards");
						}
						catch(Exception ignored2) {

							try {
								impl = EnumDiplomaticStrategies.valueOf(val);
								cardNum++;
								Logger.info("Set type to EnumDiplomaticStratigies");
							}
							catch(Exception ignored3) {

							}

						}

					}

					if(impl == null) {
						Logger.error("Something bad happened. is val null: " + (val == null));
					}
					else 
					{
						Logger.info("num: " + cardNum + " size: " + cards.size()) ;
							cards.get(cardNum).setTheEnum(impl);
					}


				} 
				catch (IllegalArgumentException | IllegalAccessException e) {
					Logger.error(e, "Failed to get f.get(p) of a string");
				}





			}
		}

	}
	
	public boolean canSelectCard() {
		return canSelectCard;
	}

}
