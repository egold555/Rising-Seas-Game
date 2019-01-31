package org.golde.saas.risingseasgame.client.event.events;

import org.golde.saas.risingseasgame.client.event.Event;
import org.golde.saas.risingseasgame.shared.packets.base.Packet;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PacketRecieveEvent extends Event {

	private Packet packet;
	
}
