package org.golde.saas.risingseasgame.shared.cards;

public enum EnumDiplomaticStrategies implements EnumCardImpl {

	//•	Diplomatic Pouch,
	
	Immunity("Immunity", "This card allows you to build one coal plant with no repercussion, no matter what Diplomacy card is used against you"),
	Sanctions("Sanctions", "Use this card to threaten a player who wants to build a coal plant. If they do so, every move they make for the rest of the game is reduced by one"),
	Economic_Diplomacy("Economic Diplomacy", "This card allows you to give one or more of your energy cards to any player to persuade them not to build a coal plant"),
	Gun_Boat_Diplomacy("Gun Boat Diplomacy", "This card allows you to challenge a player to rock, paper, scissors, in an attempt, to prevent them from building a coal plant. If you win. You move ahead one space"),
	Emissary("Emissary", "Use this card to persuade a player not to build a coal plant by allowing them to move ahead to your location five spaces"),
	Espionage("Espionage", "This allows you to see the Diplomacy cards of one player"),
	Covert_Operation("Covert Operation", "Pay non-state actors to shuttle one coal plant. Handing this card to any player will prevent them from building one coal plant."),

	;
	
	private final String title;
	private final String desc;
	private EnumDiplomaticStrategies(String title, String desc) {
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

	@Override
	public Enum getEnum() {
		return this;
	}
	
}
