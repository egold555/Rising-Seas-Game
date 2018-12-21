package org.golde.saas.risingseasgame.shared.cards;

public enum EnumPowerCards {
	WIND(19), COAL(22), SOLAR(17), FOREST(17), GEOTHERMAL(4);
	
	private final int maxInDeck;
	EnumPowerCards(int maxInDeck){
		this.maxInDeck = maxInDeck;
	}
	
	public int getMaxInDeck() {
		return maxInDeck;
	}
	
	public boolean doesRaiseWaterLevel() {
		return (this == COAL);
	}
}
