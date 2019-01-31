package org.golde.saas.risingseasgame.client.states;

import org.golde.saas.risingseasgame.client.event.EventTarget;
import org.golde.saas.risingseasgame.client.event.events.EventPacketRecieved;
import org.golde.saas.risingseasgame.client.event.events.EventRender;
import org.golde.saas.risingseasgame.client.event.events.EventUpdate;
import org.golde.saas.risingseasgame.shared.packets.PacketHelloWorld;
import org.newdawn.slick.SlickException;

public class GameStateConnecting extends GameStateAbstract {
	
	@EventTarget
	public void render(EventRender event) throws SlickException {
		
		event.getGraphics().drawString("Connecting.......", 30, 30);
		
		//super.render(gc, g);
	}
	
	@EventTarget
	public void update(EventUpdate event) throws SlickException {
		if(getNetwork().isConnected()) {
			
		}
	}
	
	@EventTarget
	public void recievedPacket(EventPacketRecieved event) {
		if(event.getPacket() instanceof PacketHelloWorld) {
			getMainClient().changeState(EnumGameState.PLAYING);
		}
	}

	@Override
	public EnumGameState getEnumGameState() {
		return EnumGameState.TITLE_SCREEN;
	}

}
