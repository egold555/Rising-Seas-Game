package org.golde.saas.risingseasgame.client.impl;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;

public interface ISprite {

	public void setTransparentColor(Color transparentColor);
	
	@Deprecated
	public Image getImage();
	
	public float getScaleOfImage();
	public void setXY(float inX, float inY);
	
}
