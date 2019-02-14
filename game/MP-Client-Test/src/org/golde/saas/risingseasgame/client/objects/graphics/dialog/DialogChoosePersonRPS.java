package org.golde.saas.risingseasgame.client.objects.graphics.dialog;

import org.golde.saas.risingseasgame.client.MainClient;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class DialogChoosePersonRPS extends DialogBox  {

	SpritePerson[] people = new SpritePerson[3];

	@Override
	public void onInit(GameContainer gc) throws SlickException {
		
		initSpritesId();
		
		for(int i = 0; i < people.length; i++) {

			people[i].init(gc);
			people[i].setY(200);

			switch(i) {
			case 1: people[i].setX(30);
			case 2: people[i].setX(MainClient.screenSize.width / 3 - 200 + 30);
			case 3: people[i].setX(MainClient.screenSize.width / 3 + 300 + 30);
			}

		}
	}
	
	private void initSpritesId() {
		
		int MY_ID = MainClient.getInstance().getNetwork().getID();
		
		int spriteCount = 0;
		for(int i = 1; i < 5; i++) {
			if(i == MY_ID) {
				continue;
			}
			people[spriteCount] = new SpritePerson(i);
			spriteCount++;
		}
		
	}

	@Override
	public void onRender(GameContainer gc, Graphics g) throws SlickException {
		for(SpritePerson p : people) {
			p.render(gc, g);
		}
	}

	@Override
	public void mouseClicked(int button, int x, int y, int clickCount) {
		for(SpritePerson p : people) {
			p.mouseClicked(button, x, y, clickCount);
		}
	}

	@Override
	public void mouseMoved(int oldx, int oldy, int newx, int newy) {
		for(SpritePerson p : people) {
			p.mouseDragged(oldx, oldy, newx, newy);
		}
	}

	private void rpsWith(int id) {

	}

	@SuppressWarnings("serial")
	private class SpritePerson extends DialogSpriteClickable {

		private final int idToBattle;
		public SpritePerson(int id) {
			super("rps/person/" + id);
			this.idToBattle = id;
		}

		@Override
		public void mouseClicked(int button, int x, int y, int clickCount) {
			if(isMouseInside()) {
				rpsWith(idToBattle);
			}
		}

		@Override
		public int getZIndex() {
			return 1001;
		}

	}

}
