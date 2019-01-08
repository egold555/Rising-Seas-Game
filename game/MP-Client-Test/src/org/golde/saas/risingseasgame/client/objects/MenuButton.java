package org.golde.saas.risingseasgame.client.objects;

import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.RoundedRectangle;

public class MenuButton extends RoundedRectangle implements GameObject {

	private static final long serialVersionUID = -1030879269063171472L;
	
	public static final int MENU_BUTTON_WIDTH = 300;
    public static final int  MENU_BUTTON_HEIGHT = 80;
    public static final int  MENU_BUTTON_MARGIN = 50;

    private static final float CORNER_RADIUS = 10;

    private Sprite normalBackground;
    private Sprite pressedBackground;
    private String text;
    private float textPosX;
    private float textPosY;
    
    boolean inside = false;

    public MenuButton(String text, float x, float y, float width, float height) {
        super(x, y, width, height, CORNER_RADIUS);
        this.text = text;
    }
    
    @Override
    public GameObject init(GameContainer gc) throws SlickException {
    	pressedBackground = new Sprite("button_pressed");
        normalBackground = new Sprite("button_normal");
        
        pressedBackground.init(gc);
        normalBackground.init(gc);
        return this;
    }

    private void updateFontWidth(Font font) {
        float textWidth = font.getWidth(text);
        float textHeight = font.getLineHeight();
        textPosX = (width - textWidth) / 2 + x;
        textPosY = (height - textHeight) / 2 + y;
    }
    
    @Override
    public void mouseMoved(int oldx, int oldy, int newx, int newy) {
    	inside = this.contains(newx, newy);
    }
    
    @Override
    public void mouseClicked(int button, int x, int y, int clickCount) {
    	if(inside) {
    		System.out.println("Clicked id " + button);
    	}
    }
    
    @SuppressWarnings("deprecation")
	@Override
    public void render(GameContainer gc, Graphics g) throws SlickException {
    	updateFontWidth(g.getFont());
    	if(pressedBackground.getImage() != null && normalBackground.getImage() != null) {
    		if(inside) {
        		pressedBackground.getImage().draw(x, y, width, height);
        	} else {
        		normalBackground.getImage().draw(x, y, width, height);
        	}
    	}
    	
    	
		g.getFont().drawString(textPosX, textPosY, text, Color.white);
    }
	
}
