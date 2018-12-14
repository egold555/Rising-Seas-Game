package org.golde.saas.risingseasgame.client;

import org.golde.saas.risingseasgame.shared.Logger;
import org.golde.saas.risingseasgame.shared.packets.PacketManager;
import org.golde.saas.risingseasgame.shared.packets.base.Packet;
import org.golde.saas.risingseasgame.shared.packets.base.PacketTCP;
import org.golde.saas.risingseasgame.shared.packets.base.PacketUDP;

import com.esotericsoftware.kryonet.Client;

public class PacketManagerClient extends PacketManager {

	private final Client client;
	
	public PacketManagerClient(Client client) {
		this.client = client;
	}
	
	public void sendToEveryone(Packet packet) {
		//Used for connection
		String packetName = packet.getClass().getSimpleName();
		if(packet instanceof PacketTCP) {
			client.sendTCP(packet);
			Logger.info("Sending TCP Packet " + packetName + " to everyone");
		}
		//Every type of game packet
		else if(packet instanceof PacketUDP) {
			client.sendUDP(packet);
			Logger.info("Sending UDP Packet " + packetName + " to everyone");
		}
		else {
			Logger.warning("Unknown type of packet being sent: " + packetName);
		}
	}
	
}
