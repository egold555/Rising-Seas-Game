package org.golde.saas.risingseasgame.server;

import java.util.ArrayList;
import java.util.List;

import com.esotericsoftware.kryonet.Connection;

public class Player {

	private static List<Player> PLAYERS = new ArrayList<Player>();

	private Connection conn;
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

}
