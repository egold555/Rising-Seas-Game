package org.golde.saas.risingseasgame.server;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import org.golde.saas.risingseasgame.shared.Logger;
import org.golde.saas.risingseasgame.shared.constants.Constants;
import org.golde.saas.risingseasgame.shared.packets.PacketAddPlayer;
import org.golde.saas.risingseasgame.shared.packets.PacketHelloWorld;
import org.golde.saas.risingseasgame.shared.packets.PacketRPSChallenge;
import org.golde.saas.risingseasgame.shared.packets.PacketSetWater;
import org.golde.saas.risingseasgame.shared.packets.base.Packet;
import org.golde.saas.risingseasgame.shared.scheduler.Scheduler;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

public class MainServer extends Listener {

	private static Server server;
	private static PacketManagerServer packetManager;
	public static final Random RANDOM = new Random();

	private static Scheduler scheduler = new Scheduler();
	
	private static int waterLevel = 0;

	public static void main(String[] args) throws IOException {
		server = new Server();
		server.bind(Constants.MP_PORT, Constants.MP_PORT);
		packetManager = new PacketManagerServer(server);
		packetManager.registerPackets(server.getKryo());
		server.start();
		server.addListener(new MainServer());
		Logger.info("Server started on: " + Constants.MP_PORT);

		startGameLoop();

	}

	private static void startGameLoop() {
		Logger.info("Game loop started.");
		new Thread() {
			public void run() {
				while(true) {
					scheduler.update();
				}
			}
		}.start();
		
		while(true) {
			Scanner scanner = new Scanner(System.in);
			String in = scanner.nextLine();
			
			if(in.equalsIgnoreCase("exit")) {
				System.exit(0);
			}
			else if(in.equalsIgnoreCase("rpc")) {
				int id = Player.getPlayers().get(0).getId();
				
				PacketRPSChallenge challenge = new PacketRPSChallenge();
				
				challenge.playerId = -1;
				
				packetManager.sendToPlayer(id, challenge);
			}
			else
			{
				System.out.println("Unknown cmd: " + in);
			}
			scanner.close();
		}
		
	}

	@Override
	public void received(Connection c, Object o) {
		if(o instanceof Packet) {
			Logger.packetRecieved( "Recieved packet : " + o.getClass().getSimpleName() +  " from " + c.getID() + ". " + ((Packet)o).toString());
		} else {
			Logger.packetRecieved("Recieved packet " + o.getClass().getSimpleName() + " from " + c.getID());
		}
		
		Player.getPlayerById(c.getID()).recievePacket(o);
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
				packetManager.sendToPlayer(c.getID(), new PacketHelloWorld());

			}
		}, 500);

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

		//This seems to not like 'activate' until I run in debug code and change a number and save again? Very confusing
//		scheduler.runRepeatingTask(1, 500, new Runnable() {
//
//			@Override
//			public void run() {
//
//				Logger.info("Set cards");
//
//				PacketSetCards packetSetCards = new PacketSetCards();
//
//				for(Field f : packetSetCards.getClass().getDeclaredFields()) {
//					if(f.getName().startsWith("card") && f.getType() == String.class) {
//						String what = getRandomCard().name();
//						try {
//							f.set(packetSetCards, what);
//						} catch (IllegalArgumentException | IllegalAccessException e) {
//							e.printStackTrace();
//						}
//					}
//				}
//
//				packetManager.sendToPlayer(c.getID(), packetSetCards);
//			}
//		});

	}

	@Override
	public void disconnected(Connection c) {
		Logger.info("Connection dropped: " + c.getID());
		Player.onDisconnect(c);
	}

	public static void addWater(int waterAmount) {
		waterLevel = waterLevel + waterAmount;
		PacketSetWater packetSetWater = new PacketSetWater();
		packetSetWater.waterLevel = waterLevel;
		packetManager.sendToEveryone(packetSetWater);
	}

	public static PacketManagerServer getPacketManager() {
		return packetManager;
	}

//	private Enum<?> getRandomCard() {
//		switch(RANDOM.nextInt(3)) {
//		case 0: return randomEnum(EnumCircumstanceCards.class); 
//		case 1: return randomEnum(EnumDiplomaticStrategies.class); 
//		case 2: return randomEnum(EnumPowerCards.class); 
//		default: return null; // :(
//		}
//	}
//
//	private <T extends Enum<? extends EnumCardImpl>> T randomEnum(Class<T> clazz){
//		int x = RANDOM.nextInt(clazz.getEnumConstants().length);
//		return clazz.getEnumConstants()[x];
//	}

}
