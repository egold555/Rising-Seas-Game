package org.golde.saas.risingseasgame.server;

import java.io.IOException;
import java.net.Inet4Address;
import java.util.Arrays;
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
import org.golde.saas.risingseasgame.shared.packets.PacketTurn;
import org.golde.saas.risingseasgame.shared.packets.base.Packet;
import org.golde.saas.risingseasgame.shared.packets.fromclient.PacketStartGame;
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
	
	private static int currentTurn = 0;
	
	private static boolean shutdown = false;

	public static void main(String[] args) throws IOException {
		server = new Server();
		server.bind(Constants.MP_PORT, Constants.MP_PORT);
		packetManager = new PacketManagerServer(server);
		packetManager.registerPackets(server.getKryo());
		server.start();
		server.addListener(new MainServer());
		Logger.info("Server started on: " + Constants.MP_PORT);
		Logger.info("IP: " + Inet4Address.getLocalHost().getHostAddress());

		startGameLoop();

	}

	private static void startGameLoop() {
		Logger.info("Game loop started.");
		new Thread() {
			public void run() {
				while(!shutdown) {
					scheduler.update();
					if(Player.isGameOver()) {
						shutdown("Game over");
					}
				}
			}
		}.start();

		while(!shutdown) {
			Scanner scanner = new Scanner(System.in);
			//if(scanner.hasNextLine()) {
				String in = scanner.nextLine();
				String[] args = new String[0];
				if(in.length() > 1) {
					args = in.split(" ");
				}
				 

				if(args[0].equalsIgnoreCase("exit")) {
					shutdown("User Exit Command");
				}
				else if(args[0].equalsIgnoreCase("rps")) {
//					int id = Player.getPlayers().get(0).getId();
//
//					PacketRPSChallenge challenge = new PacketRPSChallenge();
//
//					challenge.playerId = -1;
//
//					packetManager.sendToPlayer(id, challenge);
					
					int id = Player.getPlayers().get(0).getId();
					PacketRPSChallenge packet = new PacketRPSChallenge();
					
					packetManager.sendToPlayer(id, packet);
					
				}
				else if(args[0].equalsIgnoreCase("turn")) {
					PacketTurn packet = new PacketTurn();
					packet.id = Integer.parseInt(args[1]);
					packetManager.sendToEveryone(packet);
				}
				else
				{
					System.out.println("Unknown cmd: " + Arrays.asList(args).toString());
				}
//			}
//			else {
//				Logger.warning("Scanner has no new next line?");
//				try {
//					Thread.sleep(1000);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
			//scanner.close();
		}

	}

	@Override
	public void received(Connection c, Object o) {
		if(o instanceof Packet) {
			Logger.packetRecieved( "Recieved packet : " + o.getClass().getSimpleName() +  " from " + c.getID() + ". " + ((Packet)o).toString());
		} else {
			Logger.packetRecieved("Recieved packet " + o.getClass().getSimpleName() + " from " + c.getID());
		}
		
		if(o instanceof PacketStartGame) {
			nextTurn();
		}

		Player.getPlayerById(c.getID()).recievePacket(o);
	}
	
	public static  void nextTurn() {
		PacketTurn packetTurn = new PacketTurn();
		packetTurn.id = Player.getPlayers().get(currentTurn).getId();
		packetManager.sendToEveryone(packetTurn);
		Logger.info("Turn: " + packetTurn.id);
		
		currentTurn++;
		if(currentTurn > Player.getPlayers().size() - 1) {
			currentTurn = 0;
		}
		
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
				PacketHelloWorld helloWorld = new PacketHelloWorld();
				helloWorld.firstPlayer = (Player.getPlayers().size() == 1);
				packetManager.sendToPlayer(c.getID(), helloWorld);

			}
		}, 500);

		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				for(Player player : Player.getPlayers()) {
					if(player.getId() != c.getID()) {
						PacketAddPlayer packetAddPlayer = new PacketAddPlayer();
						packetAddPlayer.id = player.getId();
						packetAddPlayer.name = player.getName();
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
	
	public static void shutdown(String reason) {
		shutdown = true;
		server.stop();
		Logger.info("----- SERVER CLOSED -----");
		Logger.info("Reason: " + reason);
		System.exit(0);
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
