package org.golde.saas.risingseasgame.shared.packets;

import org.golde.saas.risingseasgame.shared.packets.fromclient.PacketRPSSubmit;
import org.golde.saas.risingseasgame.shared.packets.fromclient.PacketSetName;
import org.golde.saas.risingseasgame.shared.packets.fromclient.PacketStartGame;
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
		kryo.register(PacketRPSChallenge.class);
		kryo.register(PacketTurn.class);
		kryo.register(PacketGameOver.class);
		kryo.register(PacketRPSPicker.class);
		
		//Client 
		kryo.register(PacketSubmitCards.class);
		kryo.register(PacketRPSSubmit.class);
		kryo.register(PacketStartGame.class);
		kryo.register(PacketSetName.class);
	}

}
