package org.golde.saas.risingseasgame.client.objects.graphics;

import org.golde.saas.risingseasgame.client.MainClient;
import org.golde.saas.risingseasgame.client.helper.BetterTrueTypeFont;
import org.golde.saas.risingseasgame.client.helper.FontManager;
import org.golde.saas.risingseasgame.client.impl.GameObject;
import org.golde.saas.risingseasgame.client.objects.GameObjectMoveable;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

public class DialogBox extends GameObjectMoveable {
	
	@Override
	public int getZIndex() {
		return 1000;
	}
	
	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		
		super.render(gc, g);
		
		BetterTrueTypeFont font = FontManager.getOrCreateFont(40);
		
		g.fill(new Rectangle(0, 0, MainClient.screenSize.width, MainClient.screenSize.height), new SolidFill(new Color(0.5f, 0.5f, 0.5f, 0.7f)));
		font.drawString(MainClient.screenSize.width / 2, MainClient.screenSize.height / 2, "Test");
		
		
	}

	@Override
	public GameObject init(GameContainer gc) throws SlickException {
		return this;
	}

}
