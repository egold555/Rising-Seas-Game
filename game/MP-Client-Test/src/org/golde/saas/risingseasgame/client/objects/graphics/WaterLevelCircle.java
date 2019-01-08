package org.golde.saas.risingseasgame.client.objects.graphics;

import org.golde.saas.risingseasgame.client.objects.GameObject;
import org.golde.saas.risingseasgame.client.objects.Sprite;
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
