package org.golde.saas.risingseasgame.server;

import java.io.IOException;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import org.golde.saas.risingseasgame.shared.Logger;
import org.golde.saas.risingseasgame.shared.constants.Constants;
import org.golde.saas.risingseasgame.shared.packets.PacketAddPlayer;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

public class MainServer extends Listener {

	private static Server server;
	private static PacketManagerServer packetManager;
	public static final Random RANDOM = new Random();

	public static void main(String[] args) throws IOException {
		server = new Server();
		server.bind(Constants.MP_PORT, Constants.MP_PORT);
		packetManager = new PacketManagerServer(server);
		packetManager.registerPackets(server.getKryo());
		server.start();
		server.addListener(new MainServer());
		Logger.info("Server started on: " + Constants.MP_PORT);
	}

	@Override
	public void received(Connection c, Object o) {
		Logger.info("Recieved packet " + o.getClass().getSimpleName() + " from " + c.getID());
	}

	@Override
	public void connected(final Connection c) {

		Logger.info("New connection from: " + c.getID() + " " + c.getRemoteAddressTCP().getHostString());

		Player.onConnect(c);

		//TEST
		Timer timer = new Timer();

		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				for(Player player : Player.getPlayers()) {
					if(player.getId() != c.getID()) {
						PacketAddPlayer packetAddPlayer = new PacketAddPlayer();
						packetAddPlayer.id = player.getId();
						packetManager.sendToPlayer(c.getID(), packetAddPlayer);
					}
				}
			}
		}, 1000);




		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				PacketAddPlayer packetAddPlayer = new PacketAddPlayer();
				packetAddPlayer.id = c.getID();
				packetManager.sendToEveryone(packetAddPlayer);
			}
		}, 1000);
		
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				Player.getPlayerById(c.getID()).connectedToServer();
				
			}
		}, 1000);


	}

	@Override
	public void disconnected(Connection c) {
		Logger.info("Connection dropped: " + c.getID());
		Player.onDisconnect(c);
	}
	
	public static PacketManagerServer getPacketManager() {
		return packetManager;
	}


}
