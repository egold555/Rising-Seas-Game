package org.golde.saas.risingseasgame.client.objects.graphics.sprite;

import org.golde.saas.risingseasgame.client.event.EventTarget;
import org.golde.saas.risingseasgame.client.event.events.EventRender;
import org.golde.saas.risingseasgame.client.impl.GameObject;
import org.golde.saas.risingseasgame.client.impl.ISprite;
import org.golde.saas.risingseasgame.client.objects.GameObjectClickable;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class SpriteClickable extends GameObjectClickable implements ISprite {

	private static final long serialVersionUID = 1650158195418705824L;
	
	private Image img;
	private String imgSrc;
	private Color transparentColor = null;
	
	public SpriteClickable(String fileName) {
		this(fileName, 0, 0);
	}
	
	public SpriteClickable(String fileName, int startX, int startY) {
		super(startX, startY, 0,0); //init to 0, then recalc
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
		
		mockConstructorForRectangle();
		
		System.out.println("INIT: " + img);
		return this;
	}
	
	@Override
	public void setXY(float inX, float inY) {
		setXY((int)inX, (int)inY);
	}
	
	private void mockConstructorForRectangle() {
		setSize(img.getWidth(), img.getHeight()); //Set the rectangle size
		checkPoints(); //Not sure if I need to call this but the constructor does so Ill do it to
	}
	
	@EventTarget
	public void render(EventRender event) throws SlickException {
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

}
