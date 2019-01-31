package org.golde.saas.risingseasgame.shared.cards;

public enum EnumRPS implements EnumCardImpl {

	ROCK, PAPER, SCISSORS;
	
	@Override
	public String getTitle() {
		String str = name();
		return str.substring(0, 1).toUpperCase() + str.substring(1);
	}

	@Override
	public String getDesc() {
		return "";
	}

	@Override
	public String getImage() {
		return "rps/" + name().toLowerCase();
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Enum getEnum() {
		return this;
	}

}
