package org.golde.saas.risingseasgame.client.objects;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Sprite extends GameObjectMoveable {

	private Image img;
	private String imgSrc;
	
	public Sprite(String fileName) {
		this(fileName, 0, 0);
	}
	
	public Sprite(String fileName, float startX, float startY) {
		imgSrc = "res\\" + fileName + ".png";
		setXY(startX, startY);
	}
	
	@Override
	public GameObject init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		img = new Image(imgSrc).getScaledCopy(getScaleOfImage());
		System.out.println("INIT: " + img);
		return this;
	}
	
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		img.draw(getX(), getY());
	}
	
	@Deprecated
	public Image getImage() {
		return img;
	}
	
	public float getScaleOfImage() {
		return 1;
	}
	
}
