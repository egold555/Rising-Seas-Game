package org.golde.saas.risingseasgame.shared.cards;

public interface EnumCardImpl {

	public String getTitle();
	public String getDesc();
	
	public String getImage();
	
	@SuppressWarnings("rawtypes")
	public Enum getEnum();
	
}
