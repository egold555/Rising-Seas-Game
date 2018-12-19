package org.golde.saas.risingseasgame.client.objects.graphics;

import org.newdawn.slick.Color;
import org.newdawn.slick.ShapeFill;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;

public class SolidFill implements ShapeFill {

	private final Color color;
	
	public SolidFill(Color color) {
		this.color = color;
	}
	
	@Override
	public Color colorAt(Shape arg0, float arg1, float arg2) {
		return color;
	}

	@Override
	public Vector2f getOffsetAt(Shape arg0, float arg1, float arg2) {
		return new Vector2f(0,0);
	}

}
