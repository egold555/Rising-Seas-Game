package org.golde.saas.risingseasgame.client.objects.board;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.golde.saas.risingseasgame.client.MainClient;
import org.golde.saas.risingseasgame.client.impl.GameObject;
import org.golde.saas.risingseasgame.client.objects.graphics.sprite.Sprite;
import org.golde.saas.risingseasgame.client.states.EnumGameState;
import org.golde.saas.risingseasgame.client.states.GameStatePlaying;
import org.golde.saas.risingseasgame.shared.Logger;
import org.golde.saas.risingseasgame.shared.cards.EnumPowerCards;
import org.golde.saas.risingseasgame.shared.packets.PacketAddPlayer;
import org.golde.saas.risingseasgame.shared.packets.PacketInitalizeGameboard;
import org.golde.saas.risingseasgame.shared.packets.PacketPlaceGenerator;
import org.golde.saas.risingseasgame.shared.packets.PacketSetPosition;
import org.golde.saas.risingseasgame.shared.packets.PacketSetWater;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import com.esotericsoftware.kryonet.Connection;

public class Gameboard extends Sprite {
	
	//private int playerPosition = 0;
	
	private final List<PlaceToMove> placesToMove = new ArrayList<PlaceToMove>();
	
	//private PlayerPositionGraphic playerPositionGraphic;
	
	private WaterLevelGraphic waterLevelGraphic = new WaterLevelGraphic();
	
	private List<PlayerPositionGraphic> playerGraphics = new ArrayList<PlayerPositionGraphic>();
	
	public Gameboard() {
		super("Gameboard3");
		placesToMove.addAll(PlaceToMove.getEveryPlaceToMove());
		//playerPositionGraphic  = new PlayerPositionGraphic(placesToMove);
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
		//toReturn.add(playerPositionGraphic);
		toReturn.add(waterLevelGraphic);
		
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
		
		waterLevelGraphic.setX(MainClient.screenSize.width - 900);
		
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
			waterLevelGraphic.setWaterLevel( ((PacketSetWater)o).waterLevel);
		}
		else if(o instanceof PacketSetPosition) {
			//this.playerPosition = ((PacketSetPosition)o).position;
			//playerPositionGraphic.setPosition(playerPosition);
			PacketSetPosition packetSetPosition = (PacketSetPosition)o;
			//Logger.error("I have recieved the packet PacketSetPosition. PlayerGraphics has a size of: " + playerGraphics.size());
			for(PlayerPositionGraphic ppg : playerGraphics) {
				//Logger.info("Does id " + ppg.getId() + " from ppg match the packet id of " + packetSetPosition.id + "?");
				if(ppg.getId() == packetSetPosition.id) {
					ppg.setPosition(packetSetPosition.position);
					//Logger.warning("Set position of " + packetSetPosition.id + " to position " + packetSetPosition.position);
				}
			}
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
		else if(o instanceof PacketAddPlayer) {
			PacketAddPlayer packetAddPlayer = (PacketAddPlayer)o;
			PlayerPositionGraphic ppg = new PlayerPositionGraphic(getPlacesToMove(), packetAddPlayer.id);
			((GameStatePlaying)MainClient.getInstance().getGameState(EnumGameState.PLAYING)).tempGameObject.add(ppg); //Concurrent mod fix
			playerGraphics.add(ppg);
			//Logger.warning("Added player to the game with a ID of: " + packetAddPlayer.id);
		}
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		super.render(gc, g); //draw bg
		//g.drawString("Water Level: " + waterLevel, 10, 50);
		//draw debug mouse
		
	}

}
