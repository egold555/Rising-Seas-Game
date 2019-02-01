package org.golde.saas.risingseasgame.client.objects.graphics.dialog;

import org.golde.saas.risingseasgame.client.helper.BetterTrueTypeFont;
import org.golde.saas.risingseasgame.client.helper.FontManager;
import org.golde.saas.risingseasgame.client.objects.btn.Button.ButtonClickHandler;
import org.golde.saas.risingseasgame.client.states.GameStateAbstract;
import org.golde.saas.risingseasgame.shared.packets.PacketGameOver;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class DialogGameOver extends DialogBox {
	
	private int whoWon = -1;
	private DialogButton btnQuit = new DialogButton(90, 90, 60, 40, "Quit");
	
	@Override
	public void onInit(GameContainer gc) throws SlickException {
		btnQuit.init(gc);
		btnQuit.setVisable(false);
		btnQuit.setHandler(new ButtonClickHandler() {
			
			@Override
			public void onClicked(int button, int x, int y, int clickCount) {
				
			}
			
		});
	}
	
	@Override
	public void mouseClicked(int button, int x, int y, int clickCount) {
		super.mouseClicked(button, x, y, clickCount);
		btnQuit.mouseClicked(button, x, y, clickCount);
	}
	
	@Override
	public void mouseMoved(int oldx, int oldy, int newx, int newy) {
		super.mouseMoved(oldx, oldy, newx, newy);
		btnQuit.mouseMoved(oldx, oldy, newx, newy);
	}

	@Override
	public void onRender(GameContainer gc, Graphics g) throws SlickException {
		btnQuit.render(gc, g);
		BetterTrueTypeFont font = FontManager.getOrCreateFont(40);
		font.drawString(50, 50, "Game Over! Player " + whoWon + " won!");
	}
	
	public void open(GameStateAbstract gs, PacketGameOver packetGameOver) {
		this.open(gs);
		this.whoWon = packetGameOver.personWhoWon;
	}

}
