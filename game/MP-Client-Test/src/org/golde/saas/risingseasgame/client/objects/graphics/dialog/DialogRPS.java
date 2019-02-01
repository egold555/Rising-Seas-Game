package org.golde.saas.risingseasgame.client.objects.graphics.dialog;

import org.golde.saas.risingseasgame.client.MainClient;
import org.golde.saas.risingseasgame.client.helper.BetterTrueTypeFont;
import org.golde.saas.risingseasgame.client.helper.FontManager;
import org.golde.saas.risingseasgame.client.objects.graphics.sprite.SpriteClickable;
import org.golde.saas.risingseasgame.client.states.GameStateAbstract;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class DialogRPS extends DialogBox {
	
	private SpriteRPC spriteRock = new SpriteRPC("rock");
	private SpriteRPC spritePaper = new SpriteRPC("paper");
	private SpriteRPC spriteScissors = new SpriteRPC("scissors");
	
	private int id = -1;
	
	@Override
	public void onInit(GameContainer gc) throws SlickException {
		spriteRock.init(gc);
		spritePaper.init(gc);
		spriteScissors.init(gc);
		
		spriteRock.setY(200);
		spritePaper.setY(200);
		spriteScissors.setY(200);
		
		spriteRock.setX(30);
		spritePaper.setX(MainClient.screenSize.width / 3 - 200 + 30);
		spriteScissors.setX(MainClient.screenSize.width / 3 + 300 + 30);
	}

	@Override
	public void onRender(GameContainer gc, Graphics g) throws SlickException {
		spriteRock.render(gc, g);
		spriteRock.drawDebugHitbox(g);
		
		spritePaper.render(gc, g);
		spritePaper.drawDebugHitbox(g);
		
		spriteScissors.render(gc, g);
		spriteScissors.drawDebugHitbox(g);

		
//		spriteRock.setY(200);
//		spritePaper.setY(200);
//		spriteScissors.setY(200);
//		
//		spriteRock.setX(30);
//		spritePaper.setX(MainClient.screenSize.width / 3 - 200 + 30);
//		spriteScissors.setX(MainClient.screenSize.width / 3 + 300 + 30);
		
		BetterTrueTypeFont font = FontManager.getOrCreateFont(40);
		font.drawString(MainClient.screenSize.width / 2 - 490, 0, "Rock Paper Axes");
		
		BetterTrueTypeFont font2 = FontManager.getOrCreateFont(20);
		font2.drawString(MainClient.screenSize.width / 2 - 600, 40, "Player " + id + " has challenged you to a game of Rock Paper Axes!");
	}
	
	@Override
	public void mouseMoved(int oldx, int oldy, int newx, int newy) {
		super.mouseMoved(oldx, oldy, newx, newy);
		spriteRock.mouseMoved(oldx, oldy, newx, newy);
		spritePaper.mouseMoved(oldx, oldy, newx, newy);
		spriteScissors.mouseMoved(oldx, oldy, newx, newy);
	}
	
	@Override
	public void mouseClicked(int button, int x, int y, int clickCount) {
		super.mouseClicked(button, x, y, clickCount);
		
	}
	
	public void open(GameStateAbstract gs, int id) {
		super.open(gs);
		this.id = id;
	}
	
	@Override
	public void close(GameStateAbstract gs) {
		this.id = -1;
		super.close(gs);
	}
	
	@SuppressWarnings("serial")
	private class SpriteRPC extends SpriteClickable {

		public SpriteRPC(String fileName) {
			super("rps/" + fileName);
		}
		
		@Override
		public boolean isOnDialog() {
			return true;
		}
		
		@Override
		public int getZIndex() {
			return 1001;
		}
		
	}

}
