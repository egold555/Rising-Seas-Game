package org.golde.saas.risingseasgame.client.objects;

import org.golde.saas.risingseasgame.client.MainClient;
import org.golde.saas.risingseasgame.client.helper.BetterTrueTypeFont;
import org.golde.saas.risingseasgame.client.helper.FontManager;
import org.golde.saas.risingseasgame.client.helper.TextHelper;
import org.golde.saas.risingseasgame.client.impl.GameObject;
import org.golde.saas.risingseasgame.client.objects.graphics.sprite.Sprite;
import org.golde.saas.risingseasgame.client.objects.graphics.sprite.SpriteClickable;
import org.golde.saas.risingseasgame.client.states.EnumGameState;
import org.golde.saas.risingseasgame.client.states.GameStatePlaying;
import org.golde.saas.risingseasgame.shared.cards.EnumCardImpl;
import org.golde.saas.risingseasgame.shared.cards.EnumPowerCards;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

//Assign "EnumCard" to be a generic type of enum that extends EnumCard that also extends Enum. This could be any name but EnumCard is specific enough.
@SuppressWarnings("serial")
public class Card<EnumCard extends Enum<EnumCard> & EnumCardImpl> extends SpriteClickable {

	private EnumCard theEnum;

	public static final int CARD_WIDTH = 190;
	public static final int Y_HAND = 450;

	boolean selected;

	private CheckmarkSprite checkmark = new CheckmarkSprite();
	
	private Sprite cardInsideImage = null;

	//Takes any enum that implements EnumCardImpl
	public Card(EnumCard theEnum) {
		super("cardicons/card");
		this.theEnum = theEnum;
	}
	
	private void setInsideSprite() {
		if(theEnum instanceof EnumPowerCards) {
			cardInsideImage = new InsideSprite(theEnum);
			cardInsideImage.setXY(getX(), getY());
		}
		else {
			cardInsideImage = null;
		}
	}

	@Override
	public GameObject init(GameContainer gc) throws SlickException {
		GameObject toReturn = super.init(gc);
		checkmark.init(gc);
		setInsideSprite();
		return toReturn;
	}

	public void setTheEnum(EnumCard theEnum) {
		this.theEnum = theEnum;
		setInsideSprite();
	}
	public EnumCard getTheEnum() {
		return theEnum;
	}

	@SuppressWarnings("deprecation")
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

		if(cardInsideImage != null) {
			if(cardInsideImage.getImage() == null) {
				cardInsideImage.init(gc);
			}
			cardInsideImage.render(gc, g);
		}
		
		if(selected) {
			checkmark.setXY(getX() + 10, getY() + 2);
			checkmark.render(gc, g);
		}

		//drawDebugHitbox(g);
		
	}

	@Override
	public float getScaleOfImage() {
		return 0.27f;
	}

	public void setCardIndex(int index) {
		setX(Card.CARD_WIDTH * index);
	}

	@Override
	public void mouseClicked(int button, int x, int y, int clickCount) {
		GameStatePlaying gsp = (GameStatePlaying)MainClient.getInstance().getGameState(EnumGameState.PLAYING);
		if(isMouseInside()) {
			if(gsp.canSelectCard()) {
				selected = !selected; //Toggle if the card is selected
			}
			else {
				if(selected) {
					selected = false;
				}
			}
			
		}
		
		
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	private class CheckmarkSprite extends Sprite {

		public CheckmarkSprite() {
			super("check");
		}

		@Override
		public int getZIndex() {
			return 3;
		}

		@Override
		public float getScaleOfImage() {
			return 0.05f;
		}

	}
	
	private class InsideSprite extends Sprite {

		public InsideSprite(EnumCard theEnum) {
			super("cardicons/" + theEnum.getImage());
		}
		
		@Override
		public float getScaleOfImage() {
			return 0.3f;
		}
		
		@Override
		public void setXY(float inX, float inY) {
			// TODO Auto-generated method stub
			super.setXY(inX + 50, inY + 50);
		}
		
	}

}
