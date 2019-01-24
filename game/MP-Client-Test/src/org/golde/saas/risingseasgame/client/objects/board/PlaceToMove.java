package org.golde.saas.risingseasgame.client.objects.board;

import java.util.ArrayList;
import java.util.List;

import org.golde.saas.risingseasgame.client.impl.GameObject;
import org.golde.saas.risingseasgame.client.objects.GameObjectClickable;
import org.golde.saas.risingseasgame.client.objects.GameObjectMoveable;
import org.golde.saas.risingseasgame.client.objects.graphics.SolidFill;
import org.golde.saas.risingseasgame.shared.cards.EnumPowerCards;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;

public class PlaceToMove extends GameObjectClickable {

	public static final List<PlaceToMove> getEveryPlaceToMove(){
		List<PlaceToMove> toReturn = new ArrayList<PlaceToMove>();

		//30
		toReturn.add(new PlaceToMove(728, 406));
		toReturn.add(new PlaceToMove(651, 379));
		toReturn.add(new PlaceToMove(597, 321));
		toReturn.add(new PlaceToMove(580, 235));
		toReturn.add(new PlaceToMove(600, 172));
		toReturn.add(new PlaceToMove(650, 118));

		toReturn.add(new PlaceToMove(726, 92));

		toReturn.add(new PlaceToMove(821, 119));
		toReturn.add(new PlaceToMove(872, 166));
		toReturn.add(new PlaceToMove(887, 235));
		toReturn.add(new PlaceToMove(865, 301));
		toReturn.add(new PlaceToMove(803, 350));

		toReturn.add(new PlaceToMove(733, 365));

		toReturn.add(new PlaceToMove(669, 342));
		toReturn.add(new PlaceToMove(630, 297));
		toReturn.add(new PlaceToMove(619, 236));
		toReturn.add(new PlaceToMove(653, 163));
		toReturn.add(new PlaceToMove(727, 129));
		toReturn.add(new PlaceToMove(814, 158));
		toReturn.add(new PlaceToMove(847, 234));
		toReturn.add(new PlaceToMove(814, 296));

		toReturn.add(new PlaceToMove(733, 322));

		toReturn.add(new PlaceToMove(673, 293));
		toReturn.add(new PlaceToMove(656, 236));
		toReturn.add(new PlaceToMove(675, 188));
		toReturn.add(new PlaceToMove(730, 163));
		toReturn.add(new PlaceToMove(796, 186));
		toReturn.add(new PlaceToMove(808, 235));
		toReturn.add(new PlaceToMove(780, 267));
		toReturn.add(new PlaceToMove(730, 281));

		return toReturn;	
	}
	
	private boolean isAction = false;
	private EnumPowerCards placedGenerator;
	
	public static final int OFFSET = 7;
	
	public PlaceToMove(float x, float y) {
		super(x - OFFSET, y - OFFSET, 15, 15);
	}


	public boolean isAction() {
		return isAction;
	}
	
	public void setIsAction(boolean isAction) {
		this.isAction = isAction;
	}
	
	public void setPlacedGenerator(EnumPowerCards placedGenerator) {
		this.placedGenerator = placedGenerator;
	}

	@Override
	public GameObject init(GameContainer gc) throws SlickException {
		return this;
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		//g.fill(new Circle(getX(), getY(), 4), new SolidFill(Color.red));
		if(isAction) {
			g.setLineWidth(3);
			g.draw(new Circle(getX() + OFFSET, getY() + OFFSET, 8), new SolidFill(Color.black));
			g.resetLineWidth();
		}
		
		drawDebugHitbox(g);
		
		if(placedGenerator != null) {
			
			Color genColor = null;
			
			switch(placedGenerator) {
			case COAL:
				genColor = Color.black;
				break;
			case FOREST:
				genColor = Color.green;
				break;
			case GEOTHERMAL:
				genColor = Color.yellow;
				break;
			case SOLAR:
				genColor = Color.magenta;
				break;
			case WIND:
				genColor = Color.magenta;
				break;
			default:
				break;
			}
			
			g.fill(new Circle(getX() - 15 + OFFSET, getY() + OFFSET, 4), new SolidFill(genColor));
			
		}
		
	}

}
