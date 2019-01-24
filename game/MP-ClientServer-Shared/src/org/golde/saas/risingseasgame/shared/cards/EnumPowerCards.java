package org.golde.saas.risingseasgame.shared.cards;

public enum EnumPowerCards implements EnumCardImpl{
	WIND("Wind Generator", "", 19), COAL("Coal Generator", "", 22), SOLAR("Solar Generator", "", 17), FOREST("Forest", "", 17), GEOTHERMAL("Geothermal Generator", "", 4);
	
	
	private final int maxInDeck;
	private final String name, desc;
	EnumPowerCards(String name, String desc, int maxInDeck){
		this.name = name;
		this.desc = desc;
		this.maxInDeck = maxInDeck;
	}
	
	public int getMaxInDeck() {
		return maxInDeck;
	}
	
	public boolean doesRaiseWaterLevel() {
		return (this == COAL);
	}

	@Override
	public String getTitle() {
		return name;
	}

	@Override
	public String getDesc() {
		return desc;
	}

	@Override
	public String getImage() {
		return name();
	}

	@Override
	public Enum getEnum() {
		return this;
	}
	
	
}
