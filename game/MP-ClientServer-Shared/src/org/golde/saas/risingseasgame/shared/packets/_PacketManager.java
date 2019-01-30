package org.golde.saas.risingseasgame.shared.packets;

import org.golde.saas.risingseasgame.shared.packets.fromclient.PacketRPSSubmit;
import org.golde.saas.risingseasgame.shared.packets.fromclient.PacketSubmitCards;

import com.esotericsoftware.kryo.Kryo;

public abstract class _PacketManager {

	public void registerPackets(Kryo kryo) {
		kryo.register(PacketAddPlayer.class);
		kryo.register(PacketSetWater.class);
		kryo.register(PacketInitalizeGameboard.class);
		kryo.register(PacketHelloWorld.class);
		kryo.register(PacketSetCards.class);
		kryo.register(PacketSetPosition.class);
		kryo.register(PacketPlaceGenerator.class);
		kryo.register(PacketMessage.class);
		kryo.register(PacketRPSChallenge.class);
		
		//Client 
		kryo.register(PacketSubmitCards.class);
		kryo.register(PacketRPSSubmit.class);
	}

}
