package org.golde.saas.risingseasgame;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.InputListener;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.opengl.SlickCallable;
import org.newdawn.slick.opengl.renderer.Renderer;
import org.newdawn.slick.opengl.renderer.SGL;
import org.newdawn.slick.state.StateBasedGame;

public abstract class ScalableStateBasedGame extends StateBasedGame {
	
	/** The renderer to use for all GL operations */
	private static SGL GL = Renderer.get();

	/** The normal or native width of the game */
	private float normalWidth;
	/** The normal or native height of the game */
	private float normalHeight;

	/** True if we should maintain the aspect ratio */
	private boolean maintainAspect;
	/** The target width */
	private int targetWidth;
	/** The target height */
	private int targetHeight;
	/** The game container wrapped */
	private GameContainer container;

	public ScalableStateBasedGame(String name, int normalWidth, int normalHeight) {
		this(name, normalWidth, normalHeight, false);
	}
	
	public ScalableStateBasedGame(String name, int normalWidth, int normalHeight, boolean maintainAspect) {
		super(name);
		this.normalWidth = normalWidth;
		this.normalHeight = normalHeight;
		this.maintainAspect = maintainAspect;
	}
	
	
	
	@Override
	public void initStatesList(GameContainer arg0) throws SlickException {
		this.container = arg0;
		
		recalculateScale();
	}
	
	public void recalculateScale() throws SlickException {
		targetWidth = container.getWidth();
		targetHeight = container.getHeight();
		
		if (maintainAspect) {
			boolean normalIsWide = (normalWidth / normalHeight > 1.6 ? true : false);
			boolean containerIsWide = ((float) targetWidth / (float) targetHeight > 1.6 ? true : false);
			float wScale = targetWidth / normalWidth;
			float hScale = targetHeight / normalHeight;

			if (normalIsWide & containerIsWide) {
				float scale = (wScale < hScale ? wScale : hScale);
				targetWidth = (int) (normalWidth * scale);
				targetHeight = (int) (normalHeight * scale);
			} else if (normalIsWide & !containerIsWide) {
				targetWidth = (int) (normalWidth * wScale);
				targetHeight = (int) (normalHeight * wScale);
			} else if (!normalIsWide & containerIsWide) {
				targetWidth = (int) (normalWidth * hScale);
				targetHeight = (int) (normalHeight * hScale);
			} else {
				float scale = (wScale < hScale ? wScale : hScale);
				targetWidth = (int) (normalWidth * scale);
				targetHeight = (int) (normalHeight * scale);
			}

		} 
		
		container.getInput().setScale(normalWidth / targetWidth,
									  normalHeight / targetHeight);
		

		int yoffset = 0;
		int xoffset = 0;
		
		if (targetHeight < container.getHeight()) {
			yoffset = (container.getHeight() - targetHeight) / 2;
		}
		if (targetWidth < container.getWidth()) {
			xoffset = (container.getWidth() - targetWidth) / 2;
		}
		container.getInput().setOffset(-xoffset / (targetWidth / normalWidth), 
									   -yoffset / (targetHeight / normalHeight));
		
	}

	/**
	 * @see org.newdawn.slick.BasicGame#update(org.newdawn.slick.GameContainer, int)
	 */
	@Override
	public void preUpdateState(GameContainer container, int delta) throws SlickException {
		if ((targetHeight != container.getHeight()) ||
		    (targetWidth != container.getWidth())) {
			recalculateScale();
		}
	}

	/**
	 * @see org.newdawn.slick.Game#render(org.newdawn.slick.GameContainer, org.newdawn.slick.Graphics)
	 */
	@Override
	public final void preRenderState(GameContainer container, Graphics g) throws SlickException {
		int yoffset = 0;
		int xoffset = 0;
		
		if (targetHeight < container.getHeight()) {
			yoffset = (container.getHeight() - targetHeight) / 2;
		}
		if (targetWidth < container.getWidth()) {
			xoffset = (container.getWidth() - targetWidth) / 2;
		}
		
		SlickCallable.enterSafeBlock();
		g.setClip(xoffset, yoffset, targetWidth, targetHeight);
		GL.glTranslatef(xoffset, yoffset, 0);
		g.scale(targetWidth / normalWidth, targetHeight / normalHeight);
		GL.glPushMatrix();
		//held.render(container, g);
		GL.glPopMatrix();
		g.clearClip();
		SlickCallable.leaveSafeBlock();
		
		renderOverlay(container, g);
	}
	
	/**
	 * Render the overlay that will sit over the scaled screen
	 * 
	 * @param container The container holding the game being render
	 * @param g Graphics context on which to render
	 */
	protected void renderOverlay(GameContainer container, Graphics g) {
	}

}
