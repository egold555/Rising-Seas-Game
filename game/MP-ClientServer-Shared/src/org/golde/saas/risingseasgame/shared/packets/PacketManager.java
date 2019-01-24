package org.golde.saas.risingseasgame.shared.packets;

import org.golde.saas.risingseasgame.shared.packets.fromclient.PacketSubmitCards;

import com.esotericsoftware.kryo.Kryo;

public abstract class PacketManager {

	public void registerPackets(Kryo kryo) {
		kryo.register(PacketAddPlayer.class);
		kryo.register(PacketSetWater.class);
		kryo.register(PacketInitalizeGameboard.class);
		kryo.register(PacketHelloWorld.class);
		kryo.register(PacketSetCards.class);
		kryo.register(PacketSetPosition.class);
		kryo.register(PacketPlaceGenerator.class);
		
		
		kryo.register(PacketSubmitCards.class);
	}

}
