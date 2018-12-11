package org.golde.saas.risingseasgame.objects;

import org.newdawn.slick.SlickException;

public class ResImageMoveable extends ResImage {

	private float posX, posY;
	
	public ResImageMoveable(String fileName) throws SlickException {
		this(fileName, 0, 0);
	}
	
	public ResImageMoveable(String fileName, float startX, float startY) throws SlickException {
		super(fileName);
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
