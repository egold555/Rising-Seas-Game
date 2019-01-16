package org.golde.saas.risingseasgame.client.objects.graphics;

import org.golde.saas.risingseasgame.client.impl.GameObject;
import org.golde.saas.risingseasgame.client.objects.graphics.sprite.Sprite;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

public class WaterLevelCircle extends Sprite {

	public WaterLevelCircle() {
		super("vignette");
		setTransparentColor(Color.black);
	}

	@Override
	public GameObject init(GameContainer gc) throws SlickException {
		
		super.init(gc);
		
		return this;
	}
	
	

}
