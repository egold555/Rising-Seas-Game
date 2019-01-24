package org.golde.saas.risingseasgame.server;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.golde.saas.risingseasgame.server.helper.EnumUtil;
import org.golde.saas.risingseasgame.shared.Logger;
import org.golde.saas.risingseasgame.shared.cards.EnumCardImpl;
import org.golde.saas.risingseasgame.shared.cards.EnumPowerCards;
import org.golde.saas.risingseasgame.shared.packets.PacketInitalizeGameboard;
import org.golde.saas.risingseasgame.shared.packets.PacketSetCards;
import org.golde.saas.risingseasgame.shared.packets.PacketSetPosition;
import org.golde.saas.risingseasgame.shared.packets.fromclient.PacketSubmitCards;

import com.esotericsoftware.kryonet.Connection;

public class Player {

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

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	private boolean[] eventSpaces = new boolean[30];
	private Connection conn;
	private EnumCardImpl[] cards = new EnumCardImpl[7];
	private int position = 0;

	//Called when they join the server
	public Player(Connection conn) {
		this.conn = conn;
	}

	public int getId() {
		return conn.getID();
	}

	public static List<Player> getPlayers() {
		return PLAYERS;
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
			
			newCards[packetSubmitCards.card1] = EnumUtil.randomEnum(EnumPowerCards.class);
			newCards[packetSubmitCards.card2] = EnumUtil.randomEnum(EnumPowerCards.class);
			newCards[packetSubmitCards.card3] = EnumUtil.randomEnum(EnumPowerCards.class);
			newCards[packetSubmitCards.card4] = EnumUtil.randomEnum(EnumPowerCards.class);
			
			
			setCards(newCards);
		}
		
	}
	
	public void setPosition(int pos) {
		this.position = position;
		PacketSetPosition packetSetPosition = new PacketSetPosition();
		packetSetPosition.position = this.position;
		MainServer.getPacketManager().sendToPlayer(conn.getID(), packetSetPosition);
	}

}
