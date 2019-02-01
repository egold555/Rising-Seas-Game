package org.golde.saas.risingseasgame.client;

import java.io.IOException;

import org.golde.saas.risingseasgame.shared.Logger;
import org.golde.saas.risingseasgame.shared.constants.Constants;
import org.golde.saas.risingseasgame.shared.packets.base.Packet;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

public class Network extends Listener {

	private Client client;
	private PacketManagerClient packetManager;
	private boolean isConnected = false;


	public void connect() {
		try {
			client = new Client();
			packetManager = new PacketManagerClient(client);
			packetManager.registerPackets(client.getKryo());

			client.start();
			client.connect(5000, "localhost", Constants.MP_PORT, Constants.MP_PORT);
			client.addListener(this);
			Logger.info("Running networking client! ID: " + client.getID());
			isConnected = true;
		}
		catch(IOException ex) {
			Logger.error(ex, "Error running networking client");
		}
	}
	
	@Override
	public void received(Connection c, Object o) {
		if(o instanceof Packet) {
			Logger.packetRecieved("Recieved packet from server: " + o.getClass().getSimpleName() +  ". " + ((Packet)o).toString());
		}
		else {
			Logger.packetRecieved("Recieved packet from server: " + o.getClass().getSimpleName());
		}
		MainClient.getInstance().recievedPacket(c, o);
	}
	
	public boolean isConnected() {
		return isConnected;
	}
	
	@Override
	public void connected(Connection conn) {
		isConnected = true;
		Logger.info("Connected to server!");
	}

	@Override
	public void disconnected(Connection conn) {
		isConnected = false;
		Logger.info("Disconnected from server!");
	}
	
	public void sendPacketToServer(Packet packet) {
		packetManager.sendPacketToServer(packet);
	}
	
	public PacketManagerClient getPacketManager() {
		return packetManager;
	}
	
	public int getID() {
		return client.getID();
	}

	public void close() {
		client.close();
	}

}
