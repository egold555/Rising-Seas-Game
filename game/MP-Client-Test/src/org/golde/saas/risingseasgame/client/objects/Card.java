package org.golde.saas.risingseasgame.client.objects;

import org.golde.saas.risingseasgame.shared.cards.EnumCardImpl;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Card<EnumCard extends Enum<EnumCard> & EnumCardImpl> extends Sprite {

	private final EnumCard theEnum;
	
	//Takes any enum that implements EnumCardImpl
	public Card(EnumCard theEnum) {
		super("card");
		this.theEnum = theEnum;
	}
	
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		super.render(gc, sbg, g);
		
		g.drawString(theEnum.getTitle(), getX(), getY());
		
	}
	
	@Override
	public float getScaleOfImage() {
		return 0.4f;
	}
	
}
