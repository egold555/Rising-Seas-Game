package org.golde.saas.risingseasgame.shared.cards;

public enum EnumGenericCards implements EnumCardImpl{
	BLANK("", "");

	private final String title, desc;
	private EnumGenericCards(String title, String desc) {
		this.title = title;
		this.desc = desc;
	}
	
	@Override
	public String getTitle() {
		return title;
	}

	@Override
	public String getDesc() {
		return desc;
	}

	@Override
	public String getImage() {
		return name();
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Enum getEnum() {
		return this;
	}

}
