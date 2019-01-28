package org.golde.saas.risingseasgame.client.objects.board;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.golde.saas.risingseasgame.client.impl.GameObject;
import org.golde.saas.risingseasgame.client.objects.GameObjectMoveable;
import org.golde.saas.risingseasgame.client.objects.graphics.SolidFill;
import org.golde.saas.risingseasgame.client.objects.graphics.sprite.Sprite;
import org.golde.saas.risingseasgame.client.states.GameStatePlaying;
import org.golde.saas.risingseasgame.shared.Logger;
import org.golde.saas.risingseasgame.shared.cards.EnumPowerCards;
import org.golde.saas.risingseasgame.shared.packets.PacketInitalizeGameboard;
import org.golde.saas.risingseasgame.shared.packets.PacketPlaceGenerator;
import org.golde.saas.risingseasgame.shared.packets.PacketSetPosition;
import org.golde.saas.risingseasgame.shared.packets.PacketSetWater;
import org.golde.saas.risingseasgame.shared.packets.base.Packet;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;

import com.esotericsoftware.kryonet.Connection;

public class Gameboard extends Sprite {

	private int waterLevel = 0;
	
	private int playerPosition = 0;
	
	private final List<PlaceToMove> placesToMove = new ArrayList<PlaceToMove>();
	
	private PlayerPositionGraphic playerPositionGraphic;
	
	public Gameboard() {
		super("Gameboard3");
		placesToMove.addAll(PlaceToMove.getEveryPlaceToMove());
		playerPositionGraphic  = new PlayerPositionGraphic(placesToMove);
	}

	
	public List<PlaceToMove> getPlacesToMove() {
		return placesToMove;
	}
	
	
	public int getSelectedPTM() {
		for(int i = 0; i < placesToMove.size(); i++) {
			if(placesToMove.get(i).isSelected()) {
				return i;
			}
		}
		return -1;
	}
	
	public List<GameObject> getObjectsToInit(){
		List<GameObject> toReturn = new ArrayList<GameObject>();
		
		toReturn.addAll(placesToMove);
		toReturn.add(playerPositionGraphic);
		
		return toReturn;
	}

	private void initalizeGameboard(PacketInitalizeGameboard p) {
		int count = 0;
		
		int eventSpacesCount = 0;
		
		boolean[] eventSpaces = new boolean[30];
		
		for(Field f : PacketInitalizeGameboard.class.getDeclaredFields()) {
			
			if(f.getName().startsWith("eventSpace") && f.getType() == boolean.class) {
				//found boolean field
				try {
					eventSpaces[eventSpacesCount] = f.getBoolean(p);
					
					Logger.info("Set " + eventSpacesCount + " to " + eventSpaces[eventSpacesCount]);
					eventSpacesCount++;
				}
				catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		
		for(GameObject go : GameStatePlaying.INSTANCE.gameObjects) {
			if(go instanceof PlaceToMove) {
				((PlaceToMove)go).setIsAction(eventSpaces[count]);
				count++;
			}
		}
	}
	
	@Override
	public void mouseClicked(int button, int x, int y, int clickCount) {
		for(PlaceToMove ptm : placesToMove) {
			if(ptm.isMouseInside()) {
				ptm.setIsSelected(!ptm.isSelected());
			}
		}
		
	}

	@Override
	public float getScaleOfImage() {
		return 0.2f;
	}
	
	public final void recievedPacket(Connection c, Object o) {
		if(o instanceof PacketSetWater) {
			this.waterLevel = ((PacketSetWater)o).waterLevel;
		}
		else if(o instanceof PacketSetPosition) {
			this.playerPosition = ((PacketSetPosition)o).position;
			playerPositionGraphic.setPosition(playerPosition);
		}
		else if(o instanceof PacketInitalizeGameboard) {
			this.initalizeGameboard((PacketInitalizeGameboard)o);
		}
		else if(o instanceof PacketPlaceGenerator) {
			PacketPlaceGenerator packetPlaceGenerator = (PacketPlaceGenerator)o;
			if(packetPlaceGenerator.position == -1) {
				System.err.println("Failed to place " + packetPlaceGenerator.generator + " @ " + "-1");
				return;
			}
			getPlacesToMove().get(packetPlaceGenerator.position).setPlacedGenerator(EnumPowerCards.valueOf(packetPlaceGenerator.generator));
		}
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		super.render(gc, g); //draw bg
		g.drawString("Water Level: " + waterLevel, 10, 50);
		//draw debug mouse

	}

}
