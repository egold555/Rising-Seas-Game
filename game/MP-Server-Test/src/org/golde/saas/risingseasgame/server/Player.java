package org.golde.saas.risingseasgame.server;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.golde.saas.risingseasgame.server.helper.EnumUtil;
import org.golde.saas.risingseasgame.shared.cards.EnumCardImpl;
import org.golde.saas.risingseasgame.shared.cards.EnumPowerCards;
import org.golde.saas.risingseasgame.shared.packets.PacketInitalizeGameboard;
import org.golde.saas.risingseasgame.shared.packets.PacketSetCards;
import org.golde.saas.risingseasgame.shared.packets.PacketSetPosition;

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
		PacketSetCards packetSetCards = new PacketSetCards();

		for(int i = 0; i < packetSetCards.getClass().getDeclaredFields().length; i++) {
			Field f = packetSetCards.getClass().getDeclaredFields()[i];
			if(f.getName().startsWith("card") && f.getType() == String.class) {
				EnumPowerCards cardPicked = EnumUtil.randomEnum(EnumPowerCards.class);
				try {
					f.set(packetSetCards, cardPicked.name());
					cards[i] = cardPicked;
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}

		MainServer.getPacketManager().sendToPlayer(conn.getID(), packetSetCards);

	}
	
	public void setPosition(int pos) {
		this.position = position;
		PacketSetPosition packetSetPosition = new PacketSetPosition();
		packetSetPosition.position = this.position;
		MainServer.getPacketManager().sendToPlayer(conn.getID(), packetSetPosition);
	}

}
