package org.golde.saas.risingseasgame.client.objects.btn;

import org.golde.saas.risingseasgame.client.helper.BetterTrueTypeFont;
import org.golde.saas.risingseasgame.client.helper.FontManager;
import org.golde.saas.risingseasgame.client.impl.GameObject;
import org.golde.saas.risingseasgame.client.objects.GameObjectClickable;
import org.golde.saas.risingseasgame.client.objects.graphics.SolidFill;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

@SuppressWarnings("serial")
public abstract class ButtonAbstract extends GameObjectClickable {

	private final String text;
	
	private Color drawColor = Color.white;
	private Color hoverColor = Color.gray;

	private boolean visable = true;
	
	public ButtonAbstract(float x, float y, float width, float height, String text) {
		super(x, y, width, height);
		this.text = text;
	}

	@Override
	public GameObject init(GameContainer gc) throws SlickException {
		return this;
	}
	
	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		super.render(gc, g);
		
		Color theColor = isMouseInside() ? hoverColor : drawColor;
		
		g.draw(this, new SolidFill(theColor));
		BetterTrueTypeFont font = FontManager.getOrCreateFont(10);
		font.drawString(x, y, text, theColor); //Align center should work but doesnt because of the position of the text

	}
	
	@Override
	public void mouseClicked(int button, int x, int y, int clickCount) {
		if(isMouseInside()) {
			onClicked(button, x, y, clickCount);
		}
		super.mouseClicked(button, x, y, clickCount);
	}
	
	public abstract void onClicked(int button, int x, int y, int clickCount);
	
	public final void setDrawColor(Color color) {
		this.drawColor = color;
	}
	
	public final void setHoverColor(Color color) {
		this.hoverColor = color;
	}
	
	@Override
	public boolean isVisable() {
		return visable;
	}
	
	@Override
	public void setVisable(boolean visable) {
		this.visable = visable;
	}

}
