package org.golde.saas.risingseasgame.server;

import org.golde.saas.risingseasgame.shared.Logger;
import org.golde.saas.risingseasgame.shared.packets.PacketManager;
import org.golde.saas.risingseasgame.shared.packets.base.Packet;
import org.golde.saas.risingseasgame.shared.packets.base.PacketTCP;
import org.golde.saas.risingseasgame.shared.packets.base.PacketUDP;

import com.esotericsoftware.kryonet.Server;

public class PacketManagerServer extends PacketManager {

	private final Server server;

	public PacketManagerServer(Server server) {
		this.server = server;
	}

	public void sendToPlayer(int id, Packet packet) {
		//Used for connection
		String packetName = packet.getClass().getSimpleName();
		if(packet instanceof PacketTCP) {
			server.sendToTCP(id, packet);
			Logger.info("Sending TCP Packet " + packetName + " to " + id);
		}
		//Every type of game packet
		else if(packet instanceof PacketUDP) {
			server.sendToUDP(id, packet);
			Logger.info("Sending UDP Packet " + packetName + " to " + id);
		}
		else {
			Logger.warning("Unknown type of packet being sent: " + packetName);
		}
	}

	public void sendToEveryone(Packet packet) {
		//Used for connection
		String packetName = packet.getClass().getSimpleName();
		if(packet instanceof PacketTCP) {
			server.sendToAllTCP(packet);
			Logger.info("Sending TCP Packet " + packetName + " to everyone");
		}
		//Every type of game packet
		else if(packet instanceof PacketUDP) {
			server.sendToAllUDP(packet);
			Logger.info("Sending UDP Packet " + packetName + " to everyone");
		}
		else {
			Logger.warning("Unknown type of packet being sent: " + packetName);
		}
	}
	
	public void sendToEveryoneExcept(Packet packet, int except) {
		//Used for connection
		String packetName = packet.getClass().getSimpleName();
		if(packet instanceof PacketTCP) {
			server.sendToAllExceptTCP(except, packet);
			Logger.info("Sending TCP Packet " + packetName + " to everyone except " + except);
		}
		//Every type of game packet
		else if(packet instanceof PacketUDP) {
			server.sendToAllExceptUDP(except, packet);
			Logger.info("Sending UDP Packet " + packetName + " to everyone except " + except);
		}
		else {
			Logger.warning("Unknown type of packet being sent: " + packetName);
		}
	}

}
