package org.golde.saas.risingseasgame.client.event.events;

import org.golde.saas.risingseasgame.client.event.Event;
import org.golde.saas.risingseasgame.shared.packets.base.Packet;

import com.esotericsoftware.kryonet.Connection;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class EventPacketRecieved extends Event {

	private Connection connection;
	private Packet packet;
	
}
