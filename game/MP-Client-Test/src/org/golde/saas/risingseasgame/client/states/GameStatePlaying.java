package org.golde.saas.risingseasgame.client.states;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.golde.saas.risingseasgame.client.ConstantsClient;
import org.golde.saas.risingseasgame.client.impl.GameObject;
import org.golde.saas.risingseasgame.client.objects.Card;
import org.golde.saas.risingseasgame.client.objects.board.Gameboard;
import org.golde.saas.risingseasgame.client.objects.board.PlaceToMove;
import org.golde.saas.risingseasgame.client.objects.btn.Button;
import org.golde.saas.risingseasgame.client.objects.btn.Button.ButtonClickHandler;
import org.golde.saas.risingseasgame.client.objects.graphics.dialog.DialogChoosePersonRPS;
import org.golde.saas.risingseasgame.client.objects.graphics.dialog.DialogGameOver;
import org.golde.saas.risingseasgame.client.objects.graphics.dialog.DialogRPS;
import org.golde.saas.risingseasgame.client.objects.graphics.dialog.DialogTurn;
import org.golde.saas.risingseasgame.shared.Logger;
import org.golde.saas.risingseasgame.shared.cards.EnumCircumstanceCards;
import org.golde.saas.risingseasgame.shared.cards.EnumDiplomaticStrategies;
import org.golde.saas.risingseasgame.shared.cards.EnumGenericCards;
import org.golde.saas.risingseasgame.shared.cards.EnumPowerCards;
import org.golde.saas.risingseasgame.shared.packets.PacketAddPlayer;
import org.golde.saas.risingseasgame.shared.packets.PacketGameOver;
import org.golde.saas.risingseasgame.shared.packets.PacketRPSChallenge;
import org.golde.saas.risingseasgame.shared.packets.PacketRPSPicker;
import org.golde.saas.risingseasgame.shared.packets.PacketSetCards;
import org.golde.saas.risingseasgame.shared.packets.PacketTurn;
import org.golde.saas.risingseasgame.shared.packets.fromclient.PacketSubmitCards;
import org.lwjgl.input.Keyboard;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;

import com.esotericsoftware.kryonet.Connection;

public class GameStatePlaying extends GameStateAbstract {

	private Gameboard gameBoard = new Gameboard();

	public static GameStatePlaying INSTANCE;

	public List<GameObject> tempGameObject = new ArrayList<GameObject>(); //so we can push more game objects to the screen without a concurrent modification exception

	public TrueTypeFont ttf;

	@SuppressWarnings("rawtypes")
	List<Card> cards = new ArrayList<Card>();

	//WaterLevelCircle WaterLevelCircle = new WaterLevelCircle();
	
	Button sendCards = new Button(0, 0, 90, 30, "Send Cards");
	
//	DialogBox dBox = new DialogBox();
	
	private boolean canSelectCard = true;
	private static final int MAX_CARDS_SELECTABLE = 4;
	
	private DialogRPS dialogRPS = new DialogRPS();
	//private DialogChoosePersonRPS dialogChoosePersonRPS = new DialogChoosePersonRPS();
	private DialogTurn dialogTurn = new DialogTurn();
	private DialogGameOver dialogGameOver = new DialogGameOver();
	
	private int[] selectedCards = new int[MAX_CARDS_SELECTABLE];
	
	public boolean isFirstPlayer;
	
	private HashMap<Integer, String> playerNames = new HashMap<Integer, String>();

	@Override
	public void init(GameContainer gc) throws SlickException {
		INSTANCE = this;
		gameObjects.add(gameBoard.init(gc));
		gameObjects.add(sendCards.init(gc));
		
		

		for(GameObject go : gameBoard.getObjectsToInit()) {
			gameObjects.add(go.init(gc));
		}

		for(int i = 0; i < 7; i++) {
			Card<EnumGenericCards> card = new Card<EnumGenericCards>(EnumGenericCards.BLANK);
			gameObjects.add(card.init(gc));
			card.setY(Card.Y_HAND);
			card.setCardIndex(i);
			cards.add(card);
		}
		scaleInput(gc, -1);

		//Font font = new Font("Helvetica", Font.PLAIN, 14);
		//ttf = new TrueTypeFont(font, true);

		//dBox.init(gc);
		
		sendCards.setXY(ConstantsClient.WINDOW_WIDTH - 700, ConstantsClient.WINDOW_HEIGHT - 670);
		sendCards.setVisable(false);
		
		
		sendCards.setHandler(new ButtonClickHandler() {
			
			@SuppressWarnings("rawtypes")
			@Override
			public void onClicked(int button, int x, int y, int clickCount) {
				PacketSubmitCards packetSubmitCards = new PacketSubmitCards();
				
				packetSubmitCards.card1 = selectedCards[0];
				packetSubmitCards.card2 = selectedCards[1];
				packetSubmitCards.card3 = selectedCards[2];
				packetSubmitCards.card4 = selectedCards[3];
				packetSubmitCards.place = gameBoard.getSelectedPTM();
				
				getNetwork().sendPacketToServer(packetSubmitCards);
				
				for(Card card : cards) {
					card.setSelected(false);
				}
				
				PlaceToMove.resetStaticSelectedCards(gameBoard);
				
				
			}
		});
		
		gameObjects.add(dialogRPS.init(gc));
		//gameObjects.add(dialogChoosePersonRPS.init(gc));
		gameObjects.add(dialogTurn.init(gc));
		dialogTurn.open(this);
		
		gameObjects.add(dialogGameOver.init(gc));
		dialogGameOver.close(this);
		
	}

	@Override
	public void recievedPacket(Connection c, Object o) {
		
		gameBoard.recievedPacket(c, o);
		
		if(o instanceof PacketAddPlayer) {
			PacketAddPlayer packet = (PacketAddPlayer)o;
			playerNames.put(packet.id, packet.name);
		}

		else if(o instanceof PacketSetCards) {
			setCards((PacketSetCards)o);
		}
		
		else if(o instanceof PacketRPSChallenge) {
			dialogRPS.open(this);
		}
		
//		else if(o instanceof PacketRPSPicker) {
//			dialogChoosePersonRPS.open(this);
//		}
		
		else if(o instanceof PacketTurn) {
			PacketTurn turn = (PacketTurn)o;
			boolean isMyTurn = turn.id == getNetwork().getID();
			dialogTurn.setTurn(turn.id);
			if(isMyTurn) {
				dialogTurn.close(this);
			}
			else {
				if(turn.id != -2) {
					dialogTurn.open(this);
				}
			}
		}
		
		else if(o instanceof PacketGameOver) {
			PacketGameOver packetGameOver = (PacketGameOver)o;
			dialogGameOver.open(this, packetGameOver);
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
		for(int id : playerNames.keySet()) {
			g.drawString(String.valueOf(id), 30 + idCountX, 60);
			idCountX += 10;
		} 

		gameBoard.setX((ConstantsClient.WINDOW_WIDTH / 2) - gameBoard.getImage().getWidth());
		//WaterLevelCircle.setXY(0, 0);

		g.setBackground(ConstantsClient.WATER_COLOR);
		
		super.render(gc, g);
		
		//dBox.render(gc, g);

		
		
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		super.update(gc, delta);
		int selected = 0;
		canSelectCard = true;
		int cardIndex = 0;
		
		selectedCards = new int[MAX_CARDS_SELECTABLE];
		
		for(GameObject go : gameObjects) { //could use array shifting. Might be fore the better and that could also potentally fix cards being doublely clicked
			if(go instanceof Card) {
				Card card = (Card)go;
				if(card.isSelected()) {
					selected++;
					
					if(selected > MAX_CARDS_SELECTABLE) {
						selected--;
					}
					
					if(selected > MAX_CARDS_SELECTABLE - 1) { //1 - the amount of cards you want to select. THIS IS A WORKAROUND IDK IT JUST WORKS DONT TOUCH
						canSelectCard = false;
						
					}
					
					selectedCards[selected-1] = cardIndex;
					
				}
				cardIndex++;
			}
		}
		
		sendCards.setVisable(canSubmitCards(selectedCards, selected));
	}

	private boolean canSubmitCards(int[] selectedCards, int selectedCount) {

		//System.out.println("SelectedCOunt " + selectedCount);
		
		//No generators selected
		if(gameBoard.getSelectedPTM() == -1) {
			return false;
		}
		
		if(selectedCount == 2) {
			
			return (cards.get(selectedCards[0]).getTheEnum() == cards.get(selectedCards[1]).getTheEnum() /*&& 
					cards.get(selectedCards[0]).getTheEnum() == EnumPowerCards.COAL*/);
			
		}
		else if(selectedCount == 4) {
			
			//System.out.println("1) " + cards.get(selectedCards[0]).getTheEnum().name());
			//System.out.println("2) " + cards.get(selectedCards[1]).getTheEnum().name());
			//System.out.println("3) " + cards.get(selectedCards[2]).getTheEnum().name());
			//System.out.println("4) " + cards.get(selectedCards[3]).getTheEnum().name());
			
			return (cards.get(selectedCards[0]).getTheEnum() == cards.get(selectedCards[1]).getTheEnum() && 
					cards.get(selectedCards[2]).getTheEnum() == cards.get(selectedCards[3]).getTheEnum() && 
					cards.get(selectedCards[1]).getTheEnum() == cards.get(selectedCards[2]).getTheEnum() /*&&
					cards.get(selectedCards[1]).getTheEnum() != EnumPowerCards.COAL*/);
			
		}
		return false;
		
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

	@SuppressWarnings("unchecked")
	private void setCards(PacketSetCards p) {
		int cardNum = -1;
		for(Field f : p.getClass().getDeclaredFields()) {
			if(f.getName().startsWith("card") && f.getType() == String.class) {

				String val;
				try {
					val = (String)f.get(p);

					@SuppressWarnings("rawtypes")
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
