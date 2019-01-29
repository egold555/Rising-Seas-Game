package org.golde.saas.risingseasgame.client.objects.graphics.sprite;

import org.golde.saas.risingseasgame.client.MainClient;
import org.golde.saas.risingseasgame.client.impl.GameObject;
import org.golde.saas.risingseasgame.client.impl.ISprite;
import org.golde.saas.risingseasgame.client.objects.GameObjectMoveable;
import org.golde.saas.risingseasgame.shared.Logger;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Sprite extends GameObjectMoveable implements ISprite {

	private Image img;
	private String imgSrc;
	private Color transparentColor = null;
	
	
	public Sprite(String fileName) {
		this(fileName, 0, 0);
	}
	
	public Sprite(String fileName, int startX, int startY) {
		imgSrc = "res\\" + fileName + ".png";
		setXY(startX, startY);
	}
	
	@Override
	public void setTransparentColor(Color transparentColor) {
		this.transparentColor = transparentColor;
	}
	
	@Override
	public GameObject init(GameContainer gc) throws SlickException {
		if(transparentColor == null) {
			img = new Image(imgSrc).getScaledCopy(getScaleOfImage());
		}
		else {
			img = new Image(imgSrc, transparentColor).getScaledCopy(getScaleOfImage());
		}
		
		//System.out.println("INIT: " + img);
		return this;
	}
	
	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		if(img == null) {
			Logger.warning("Sprite tried to render with a null image!");
			return;
		}
		img.draw(getX(), getY());
	}
	
	@Deprecated
	@Override
	public Image getImage() {
		return img;
	}
	
	@Override
	public float getScaleOfImage() {
		return 1;
	}
	
	@Override
	public void setXY(float inX, float inY) {
		setXY((int)inX, (int)inY);
	}

	
	
}
