package org.golde.saas.risingseasgame.objects;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Sprite extends Image implements GameObjectMoveable {
	
	private float posX, posY;
	
	public Sprite(String fileName) throws SlickException {
		this(fileName, 0, 0);
	}
	
	public Sprite(String fileName, float startX, float startY) throws SlickException {
		super("res\\" + fileName + ".png");
		this.posX = startX;
		this.posY = startY;
	}

	@Override
	public float getX() {
		return posX;
	}
	
	@Override
	public float getY() {
		return posY;
	}
	
	@Override
	public void setX(float x) {
		this.posX = x;
	}
	
	@Override
	public void setY(float y) {
		this.posY = y;
	}
	
	@Override
	public void setXY(float x, float y) {
		setX(x);
		setY(y);
	}
	
	@Override
	public void draw(Graphics g) {
		super.draw(posX, posY);
	}
	
	@Override
	@Deprecated
	//Make it so we use the correct draw method
	public void draw() {
		super.draw();
	}
	
}
