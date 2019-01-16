package org.golde.saas.risingseasgame.client.objects;

import java.awt.MouseInfo;

import org.golde.saas.risingseasgame.client.helper.BetterTrueTypeFont;
import org.golde.saas.risingseasgame.client.helper.FontManager;
import org.golde.saas.risingseasgame.client.helper.TextHelper;
import org.golde.saas.risingseasgame.client.objects.graphics.SolidFill;
import org.golde.saas.risingseasgame.client.objects.graphics.sprite.Sprite;
import org.golde.saas.risingseasgame.client.objects.graphics.sprite.SpriteClickable;
import org.golde.saas.risingseasgame.shared.cards.EnumCardImpl;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

//Assign "EnumCard" to be a generic type of enum that extends EnumCard that also extends Enum. This could be any name but EnumCard is specific enough.
public class Card<EnumCard extends Enum<EnumCard> & EnumCardImpl> extends SpriteClickable {

	private EnumCard theEnum;

	public static final int CARD_WIDTH = 190;
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

		//g.setColor(Color.black);
		BetterTrueTypeFont fontName = FontManager.getOrCreateFont(10);
		BetterTrueTypeFont fontDesc = FontManager.getOrCreateFont(12);
		//g.drawString(theEnum.getTitle(), getX() + 18, getY() + 14);
		//g.drawString(TextHelper.stringArrayToNewLineChar(TextHelper.wordWrap(theEnum.getDesc(), 18)), getX() + 18, getY() + 172);
		fontName.drawString(getX() + 18, getY() + 14, theEnum.getTitle(), Color.black);
		fontDesc.drawString(getX() + 18, getY() + 172, TextHelper.stringArrayToNewLineChar(TextHelper.wordWrap(theEnum.getDesc(), 20)), Color.black);
		//g.setColor(Color.white);

		
		g.draw(this, new SolidFill(isMouseInside() ? Color.green : Color.blue));

	}

	@Override
	public float getScaleOfImage() {
		return 0.27f;
	}

	public void setCardIndex(int index) {
		setX(Card.CARD_WIDTH * index);
	}




}
