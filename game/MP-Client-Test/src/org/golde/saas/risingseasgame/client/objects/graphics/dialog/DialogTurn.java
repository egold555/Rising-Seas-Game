package org.golde.saas.risingseasgame.client.objects.graphics.dialog;

import org.golde.saas.risingseasgame.client.MainClient;
import org.golde.saas.risingseasgame.client.helper.BetterTrueTypeFont;
import org.golde.saas.risingseasgame.client.helper.FontManager;
import org.golde.saas.risingseasgame.client.objects.btn.Button;
import org.golde.saas.risingseasgame.client.objects.btn.Button.ButtonClickHandler;
import org.golde.saas.risingseasgame.shared.packets.fromclient.PacketStartGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class DialogTurn extends DialogBox {

	private int turn = -1;
	public static boolean firstPlayer = false;
	DialogButton btnStart = new DialogButton(90, 90, 60, 40, "Start Game!");
	
	@Override
	public void onInit(GameContainer gc) throws SlickException {
		btnStart.init(gc);
		btnStart.setVisable(false);
		btnStart.setHandler(new ButtonClickHandler() {
			
			@Override
			public void onClicked(int button, int x, int y, int clickCount) {
				PacketStartGame packetStartGame = new PacketStartGame();
				MainClient.getInstance().getNetwork().sendPacketToServer(packetStartGame);
			}
			
		});
	}
	
	@Override
	public void mouseClicked(int button, int x, int y, int clickCount) {
		super.mouseClicked(button, x, y, clickCount);
		btnStart.mouseClicked(button, x, y, clickCount);
	}
	
	@Override
	public void mouseMoved(int oldx, int oldy, int newx, int newy) {
		super.mouseMoved(oldx, oldy, newx, newy);
		btnStart.mouseMoved(oldx, oldy, newx, newy);
	}

	@Override
	public void onRender(GameContainer gc, Graphics g) throws SlickException {
		BetterTrueTypeFont font = FontManager.getOrCreateFont(40);
		if(turn == -1) {
			font.drawString(50, 50, "Game not started.");
			
			if(firstPlayer) {
				btnStart.setVisable(true);
				btnStart.render(gc, g);
			}
		}
		else {
			font.drawString(50, 50, "Player " + turn + "'s turn");
		}
	}
	
	public void setTurn(int turn) {
		this.turn = turn;
	}
	
	public void setFirstPlayer(boolean firstPlayer) {
		this.firstPlayer = firstPlayer;
	}

}
