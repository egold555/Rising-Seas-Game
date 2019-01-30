package org.golde.saas.risingseasgame.client.objects.board;

import org.golde.saas.risingseasgame.client.objects.graphics.SolidFill;
import org.golde.saas.risingseasgame.client.objects.graphics.sprite.Sprite;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

public class WaterLevelGraphic extends Sprite {

	private int waterLevel;
	
	public WaterLevelGraphic() {
		super("cyl");
	}
	
	@Override
	public float getScaleOfImage() {
		return 0.4f;
	}
	
	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		super.render(gc, g);
		g.fill(new Rectangle(getX() + 162, getY() + 355, 41, -(waterLevel * 10)), new SolidFill(Color.blue));
	}
	
	public void setWaterLevel(int waterLevel) {
		this.waterLevel = waterLevel;
	}

}
