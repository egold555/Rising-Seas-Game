package org.golde.saas.risingseasgame.shared.packets;

import com.esotericsoftware.kryo.Kryo;

public abstract class PacketManager {
	
	public void registerPackets(Kryo kryo) {
			kryo.register(PacketAddPlayer.class);
			kryo.register(PacketSetWater.class);
			kryo.register(PacketInitalizeGameboard.class);
	}
	
}
