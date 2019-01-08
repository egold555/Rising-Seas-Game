package org.golde.saas.risingseasgame.server;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.golde.saas.risingseasgame.shared.packets.PacketInitalizeGameboard;

import com.esotericsoftware.kryonet.Connection;

public class Player {

	private static List<Player> PLAYERS = new ArrayList<Player>();
	
	private boolean[] eventSpaces = new boolean[30];
	private Connection conn;
	
	//Called when they join the server
	public Player(Connection conn) {
		this.conn = conn;
	}

	public int getId() {
		return conn.getID();
	}

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
//		PacketSetWater packetSetWater = new PacketSetWater();
//		packetSetWater.waterLevel = 6;
//		MainServer.getPacketManager().sendToPlayer(conn.getID(), packetSetWater);
	}

}
