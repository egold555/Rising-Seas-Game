package org.golde.saas.risingseasgame.client.objects.btn;

public class Button extends ButtonAbstract {

	ButtonClickHandler handler;
	
	public Button(float x, float y, float width, float height, String text) {
		super(x, y, width, height, text);
	}

	@Override
	public void onClicked(int button, int x, int y, int clickCount) {
		if(this.handler != null) {
			handler.onClicked(button, x, y, clickCount);
		}
	}
	
	public void setHandler(ButtonClickHandler handler) {
		this.handler = handler;
	}
	
	public interface ButtonClickHandler {
		
		public void onClicked(int button, int x, int y, int clickCount);
		
	}

}
