package org.golde.saas.risingseasgame.server;

import java.util.ArrayList;
import java.util.List;

import org.golde.saas.risingseasgame.shared.packets.PacketInitalizeGameboard;
import org.golde.saas.risingseasgame.shared.packets.PacketManager;

import com.esotericsoftware.kryonet.Connection;

public class Player {

	private static List<Player> PLAYERS = new ArrayList<Player>();
	
	private boolean[] eventSpaces = new boolean[30];
	private Connection conn;
	
	//Called when they join the server
	public Player(Connection conn) {
		this.conn = conn;
		for(int i = 0; i < eventSpaces.length; i++) {
			eventSpaces[i] = MainServer.RANDOM.nextBoolean();
		}
		
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
		packetInitalizeGameboard.eventSpaces = eventSpaces;
		MainServer.getPacketManager().sendToPlayer(conn.getID(), packetInitalizeGameboard);
	}

}
