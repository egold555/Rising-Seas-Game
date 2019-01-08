package org.golde.saas.risingseasgame.client.objects;

import org.golde.saas.risingseasgame.shared.cards.EnumCardImpl;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

//Assign "EnumCard" to be a generic type of enum that extends EnumCard that also extends Enum. This could be any name but EnumCard is specific enough.
public class Card<EnumCard extends Enum<EnumCard> & EnumCardImpl> extends Sprite {

	private EnumCard theEnum;
	
	public static final int CARD_WIDTH = 221;
	public static final int Y_HAND = 450;
	
	//Takes any enum that implements EnumCardImpl
	public Card(EnumCard theEnum) {
		super("card");
		this.theEnum = theEnum;
	}
	
	public void setTheEnum(EnumCard theEnum) {
		this.theEnum = theEnum;
	}
	
	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		super.render(gc, g);
//		System.out.println("Card width: " + getImage().getWidth());
		g.setColor(Color.black);
		g.drawString(theEnum.getTitle(), getX() + 20, getY() + 15);
		g.drawString(theEnum.getDesc(), getX() + 20, getY() + 190);
		g.setColor(Color.white);
	}
	
	@Override
	public float getScaleOfImage() {
		return 0.3f;
	}
	
	public void setCardIndex(int index) {
		setX(Card.CARD_WIDTH * index);
	}
	
}
