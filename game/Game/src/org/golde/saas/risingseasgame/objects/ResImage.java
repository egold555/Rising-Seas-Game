package org.golde.saas.risingseasgame.objects;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class ResImage extends Image {

	public ResImage(String fileName) throws SlickException {
		super("res\\" + fileName + ".png");
	}
	
}
