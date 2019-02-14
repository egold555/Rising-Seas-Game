package org.golde.saas.risingseasgame.client.objects.graphics.dialog;

import org.golde.saas.risingseasgame.client.MainClient;
import org.golde.saas.risingseasgame.client.impl.GameObject;
import org.golde.saas.risingseasgame.client.objects.GameObjectMoveable;
import org.golde.saas.risingseasgame.client.objects.btn.Button;
import org.golde.saas.risingseasgame.client.objects.graphics.SolidFill;
import org.golde.saas.risingseasgame.client.objects.graphics.sprite.SpriteClickable;
import org.golde.saas.risingseasgame.client.states.GameStateAbstract;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

public abstract class DialogBox extends GameObjectMoveable {
	
	public abstract void onInit(GameContainer gc) throws SlickException ;
	public abstract void onRender(GameContainer gc, Graphics g)throws SlickException ;
	
	public DialogBox() {
		setVisable(false);
	}
	
	@Override
	public int getZIndex() {
		return 1000;
	}
	
	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		
		super.render(gc, g);
		
		//BetterTrueTypeFont font = FontManager.getOrCreateFont(40);
		
		g.fill(new Rectangle(0, 0, MainClient.screenSize.width, MainClient.screenSize.height), new SolidFill(new Color(0.5f, 0.5f, 0.5f, 0.7f)));
		//font.drawString(MainClient.screenSize.width / 2, MainClient.screenSize.height / 2, "Test");
		
		onRender(gc, g);
		
	}

	@Override
	public GameObject init(GameContainer gc) throws SlickException {
		onInit(gc);
		return this;
	}
	
	public void open(GameStateAbstract gs) {
		gs.setDialogOpen();
		setVisable(true);
	}
	
	public void close(GameStateAbstract gs) {
		gs.setDialogClosed();
		setVisable(false);
	}
	
	@Override
	public boolean isOnDialog() {
		return true;
	}
	
	@SuppressWarnings("serial")
	protected class DialogButton extends Button {

		public DialogButton(float x, float y, float width, float height, String text) {
			super(x, y, width, height, text);
		}
		
		@Override
		public boolean isOnDialog() {
			return true;
		}
		
	}
	
	@SuppressWarnings("serial")
	protected class DialogSpriteClickable extends SpriteClickable {

		public DialogSpriteClickable(String fileName) {
			super(fileName);
		}
		
		public DialogSpriteClickable(String fileName, int x, int y) {
			super(fileName, x, y);
		}
		
		@Override
		public boolean isOnDialog() {
			return true;
		}
		
	}

}
