package org.golde.saas.risingseasgame.objects;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Sprite extends Image implements GameObject{
	
	private float posX, posY;
	
	public Sprite(String fileName) throws SlickException {
		this(fileName, 0, 0);
	}
	
	public Sprite(String fileName, float startX, float startY) throws SlickException {
		super("res\\" + fileName + ".png");
		this.posX = startX;
		this.posY = startY;
	}

	public float getX() {
		return posX;
	}
	
	public float getY() {
		return posY;
	}
	
	public void setX(float x) {
		this.posX = x;
	}
	
	public void setY(float y) {
		this.posY = y;
	}
	
	public void setXY(float x, float y) {
		setX(x);
		setY(y);
	}
	
	@Override
	public void draw() {
		super.draw(posX, posY);
	}
	
}
