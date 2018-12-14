package org.golde.saas.risingseasgame.objects;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Sprite implements GameObjectMoveable {
	
	private float posX, posY;
	private Image img;
	private String imgSrc;
	
	public Sprite(String fileName) {
		this(fileName, 0, 0);
	}
	
	public Sprite(String fileName, float startX, float startY) {
		imgSrc = "res\\" + fileName + ".png";
		this.posX = startX;
		this.posY = startY;
	}
	
	@Override
	public GameObject init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		img = new Image(imgSrc);
		System.out.println("INIT: " + img);
		return this;
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
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		img.draw(posX, posY);
	}
	
	@Deprecated
	public Image getImage() {
		return img;
	}
	
}
