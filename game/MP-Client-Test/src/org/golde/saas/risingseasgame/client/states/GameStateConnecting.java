package org.golde.saas.risingseasgame.client.states;

import java.util.Scanner;

import org.golde.saas.risingseasgame.client.MainClient;
import org.golde.saas.risingseasgame.client.objects.graphics.dialog.DialogTurn;
import org.golde.saas.risingseasgame.shared.Logger;
import org.golde.saas.risingseasgame.shared.packets.PacketHelloWorld;
import org.golde.saas.risingseasgame.shared.packets.fromclient.PacketSetName;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import com.esotericsoftware.kryonet.Connection;

public class GameStateConnecting extends GameStateAbstract {
	
	private String name;
	
	@Override
	public void init(GameContainer gc) throws SlickException {
		Logger.info("Please enter your name:");
		this.name = MainClient.getInstance().scanner.nextLine();
	}
	
	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		
		g.drawString("Connecting.......", 30, 30);
		
		super.render(gc, g);
	}
	
	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		
	}
	
	@Override
	public void recievedPacket(Connection c, Object o) {
		if(o instanceof PacketHelloWorld) {
			
			PacketSetName packetSetName = new PacketSetName();
			packetSetName.name = this.name;
			getNetwork().getPacketManager().sendPacketToServer(packetSetName);
			
			//Display.setTitle("RisingSeas (ID: " + getNetwork().getID() + ")");
			PacketHelloWorld packet = (PacketHelloWorld)o;
			DialogTurn.firstPlayer = packet.firstPlayer;
			getMainClient().changeState(EnumGameState.PLAYING);
		}
	}

	@Override
	public EnumGameState getEnumGameState() {
		return EnumGameState.TITLE_SCREEN;
	}

}
