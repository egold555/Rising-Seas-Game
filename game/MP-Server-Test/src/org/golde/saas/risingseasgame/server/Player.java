package org.golde.saas.risingseasgame.server;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.golde.saas.risingseasgame.server.helper.EnumUtil;
import org.golde.saas.risingseasgame.shared.Logger;
import org.golde.saas.risingseasgame.shared.cards.EnumCardImpl;
import org.golde.saas.risingseasgame.shared.cards.EnumCircumstanceCards;
import org.golde.saas.risingseasgame.shared.cards.EnumDiplomaticStrategies;
import org.golde.saas.risingseasgame.shared.cards.EnumPowerCards;
import org.golde.saas.risingseasgame.shared.packets.PacketGameOver;
import org.golde.saas.risingseasgame.shared.packets.PacketInitalizeGameboard;
import org.golde.saas.risingseasgame.shared.packets.PacketPlaceGenerator;
import org.golde.saas.risingseasgame.shared.packets.PacketSetCards;
import org.golde.saas.risingseasgame.shared.packets.PacketSetPosition;
import org.golde.saas.risingseasgame.shared.packets.PacketTurn;
import org.golde.saas.risingseasgame.shared.packets.fromclient.PacketSubmitCards;

import com.esotericsoftware.kryonet.Connection;

public class Player {

	private static boolean gameOver = false;
	private static List<Player> PLAYERS = new ArrayList<Player>();

	public static Player getPlayerById(int id) {
		for(Player player : PLAYERS){
			if(player.getId() == id){
				return player;
			}
		}
		return null;
	}

	public static void onConnect(Connection con) {
		if(getPlayerById(con.getID()) == null) {
			PLAYERS.add(new Player(con));
		}

	}

	public static void onDisconnect(Connection con) {
		if(getPlayerById(con.getID()) != null) {
			PLAYERS.remove(getPlayerById(con.getID()));
		}
	}
	
	public static boolean isGameOver() {
		return gameOver;
	}

	public static List<Player> getPlayers() {
		return PLAYERS;
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	private boolean[] eventSpaces = new boolean[30];
	private Connection conn;
	private EnumCardImpl[] cards = new EnumCardImpl[7];
	private int position = 0;
	private HashMap<EnumPowerCards, Integer> generatorsPlaced = new HashMap<EnumPowerCards, Integer>();

	//Called when they join the server
	public Player(Connection conn) {
		this.conn = conn;
		
		for(EnumPowerCards ep : EnumPowerCards.values()) {
			generatorsPlaced.put(ep, 0);
		}
		
	}

	public int getId() {
		return conn.getID();
	}

	public void connectedToServer() {
		PacketInitalizeGameboard packetInitalizeGameboard = new PacketInitalizeGameboard();
		int eventSpacesCount = 0;

		for(Field f : PacketInitalizeGameboard.class.getDeclaredFields()) {
			if(f.getName().startsWith("eventSpace") && f.getType() == boolean.class) {
				//found boolean field
				try {
					boolean state = MainServer.RANDOM.nextBoolean();
					f.setBoolean(packetInitalizeGameboard, state);
					eventSpaces[eventSpacesCount] = state;
					eventSpacesCount++;

				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}

		System.out.println("Set array: " + Arrays.toString(eventSpaces));
		MainServer.getPacketManager().sendToPlayer(conn.getID(), packetInitalizeGameboard);

		//Set initial cards
		
		EnumCardImpl[] initialCards = new EnumCardImpl[7];
		for(int i = 0; i < initialCards.length; i++) {
			initialCards[i] = EnumUtil.randomEnum(EnumPowerCards.class);
		}
		
		setCards(initialCards);

	}
	
	private void setCards(EnumCardImpl[] theCards) {
		cards = theCards;
		
		PacketSetCards packetSetCards = new PacketSetCards();
		
		for(int i = 0; i < packetSetCards.getClass().getDeclaredFields().length; i++) {
			Field f = packetSetCards.getClass().getDeclaredFields()[i];
			if(f.getName().startsWith("card") && f.getType() == String.class) {
				try {
					f.set(packetSetCards, cards[i].getEnum().name());
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		
		MainServer.getPacketManager().sendToPlayer(conn.getID(), packetSetCards);
		
	}
	
	public void recievePacket(Object o) {
		
		if(o instanceof PacketSubmitCards) {
			PacketSubmitCards packetSubmitCards = (PacketSubmitCards)o;
			Logger.info("Got submitted cards");
			EnumCardImpl[] newCards = cards.clone();
			EnumCardImpl origCard = cards.clone()[packetSubmitCards.card1];
			
			newCards[packetSubmitCards.card1] = EnumUtil.randomEnum(EnumPowerCards.class);
			newCards[packetSubmitCards.card2] = EnumUtil.randomEnum(EnumPowerCards.class);
			if(newCards.length == 4) {
				newCards[packetSubmitCards.card3] = EnumUtil.randomEnum(EnumPowerCards.class);
				newCards[packetSubmitCards.card4] = EnumUtil.randomEnum(EnumPowerCards.class);
			}
			
			
			setCards(newCards);
			handelCardFunctions(origCard, packetSubmitCards.place);
			MainServer.nextTurn();
		}
		
	}
	
	private void handelCardFunctions(EnumCardImpl card, int place) {
		if(card instanceof EnumCircumstanceCards) {
			doCircumstanceAction((EnumCircumstanceCards)card);
		}
		else if(card instanceof EnumDiplomaticStrategies) {
			doDiplomaticAction((EnumDiplomaticStrategies)card);
		}
		else if(card instanceof EnumPowerCards) {
			doPowerCards((EnumPowerCards)card, place);
		}
	}
	
	private void doPowerCards(EnumPowerCards card, int place) {
//		switch(card) {
//		case COAL:
//			break;
//		case FOREST:
//			break;
//		case GEOTHERMAL:
//			break;
//		case SOLAR:
//			break;
//		case WIND:
//			break;
//		default:
//			break;
//		}
		
		if(card == EnumPowerCards.COAL) {
			MainServer.addWater(3);
		}
		
		move(howManySpacesToMove(place));
		PacketPlaceGenerator packetPlaceGenerator = new PacketPlaceGenerator();
		packetPlaceGenerator.generator = card.name();
		packetPlaceGenerator.position = place;
		MainServer.getPacketManager().sendToPlayer(getId(), packetPlaceGenerator);
		
		incrementGeneratorHashmap(card);
		
	}
	
	private void incrementGeneratorHashmap(EnumPowerCards ep) {
		generatorsPlaced.put(ep, generatorsPlaced.get(ep) + 1);
	}

	private void doCircumstanceAction(EnumCircumstanceCards card) {
		switch(card) {
		case ACID_OCEAN:
			move(-2);
			break;
		case CARBON_FARM:
			move(1);
			break;
		case CARBON_SCRUBBER:
			move(1);
			break;
		case FUSION_REACTOR:
			for(Player p : PLAYERS) {
				p.move(2);
			}
			break;
		case GEOTHERMAL_VENTS:
			move(2);
			break;
		case HOME_BATTERY_SYSTEMS: //Choose an opponent and play one round of rock, paper, scissors. Winner moves ahead one space, loser back
			break; 
		case OVERCAST:
			move(-generatorsPlaced.get(EnumPowerCards.SOLAR)); //Move back one space for each solar farm
			break;
		case SAILBOAT_FORCE:
			move(1);
			break;
		case SCARCE_RESOURCES: //Choose an opponent and play one round of rock, paper, scissors. Winner moves ahead one space, loser back
			break;
		case SNOW_PACK:
			move(-2);
			break;
		case SOLAR_ROADS:
			move(1);
			break;
		case SOLAR_ROOF:
			move(2);
			break;
		case TORNADO:
			move(-generatorsPlaced.get(EnumPowerCards.COAL)); //Move back one space for each coal plant
			break;
		case VEGETARIAN:
			move(2);
			break;
		case VOLCANO_ERUPTS:
			move(2);
			break;
		case WILD_FIRE:
			move(-generatorsPlaced.get(EnumPowerCards.FOREST)); //Move back one space for each forest
			break;
		case WINDFARM:
			move(-generatorsPlaced.get(EnumPowerCards.WIND)); //Move back one space for each wind farm
			break;
		case WINTER_WARMTH:
			move(-2);
			break;
		default:
			break;
		
		}
	}
	
	private void doDiplomaticAction(EnumDiplomaticStrategies card) {
		switch(card) {
		case Covert_Operation:
			break;
		case Economic_Diplomacy:
			break;
		case Emissary:
			break;
		case Espionage:
			break;
		case Gun_Boat_Diplomacy:
			break;
		case Immunity:
			break;
		case Sanctions:
			break;
		default:
			break;
		
		}
	}

	public void move(int pos) {
		this.setPosition(this.position + pos);
	}
	
	public void setPosition(int pos) {
		
		this.position = pos;
		
		if(pos > 29) {
			//Player won the game
			this.position = 29;
			pos = 29;
			won();
		}
		
		
		PacketSetPosition packetSetPosition = new PacketSetPosition();
		packetSetPosition.position = this.position;
		packetSetPosition.id = getId();
		//MainServer.getPacketManager().sendToPlayer(conn.getID(), packetSetPosition);
		MainServer.getPacketManager().sendToEveryone(packetSetPosition);
		if(eventSpaces[pos]) {
			System.out.println("Player " + getId() + " landed on a event space");
		}
		
		
		
	}
	
	private void won() {
		gameOver = true;
		System.out.println("Player " + getId() + " won the game!");
		PacketTurn packetTurn = new PacketTurn();
		packetTurn.id = -2;
		MainServer.getPacketManager().sendToEveryone(packetTurn);
		
		PacketGameOver packetGameOver = new PacketGameOver();
		packetGameOver.personWhoWon = getId();
		MainServer.getPacketManager().sendToEveryone(packetGameOver);
	}
	
	private static int howManySpacesToMove(int generatorPlacedIn) {
		if(generatorPlacedIn > 21) {
			return 1;
		}
		else if(generatorPlacedIn > 12) {
			return 2;
		}
		else if(generatorPlacedIn > 6) {
			return 3;
		}
		else {
			return 4;
		}
	}

}
