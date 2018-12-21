package org.golde.saas.risingseasgame.shared.cards;

public enum EnumCircumstanceCards implements EnumCardImpl {
	
	CARBON_FARM("Your carbon farming initiative pays off!", "Move ahead one\nspace"),
	HOME_BATTERY_SYSTEMS("Defeat one opponent to win free home battery systems.", "Choose an opponent\nand play one round of\nrock, paper, scissors.\nWinner moves ahead\none space, loser back"),
	WILD_FIRE("Wild Fire!", "Move one space back\nfor each forest!"),
	SOLAR_ROADS("Install solar roads", "Move ahead one\nspace"),
	CARBON_SCRUBBER("Smart planning!", "Your island invests\nin carbon scrubber\ntechnology.\nMove ahead one space"),
	GEOTHERMAL_VENTS("Geothermal vents found!", "Use the energy to\nheat your homes.\nMove ahead two\nspaces."),
	TORNADO("Tornado!", "Move back one space\nfor each coal plant"),
	SCARCE_RESOURCES("War Over Scarce Resources", "Choose an opponent\nand play one round of\nrock, paper, scissors.\nWinner moves ahead\none space, loser back"),
	SAILBOAT_FORCE("Convert your Navy to an all sailboat force!", "Move ahead one\nspace."),
	VOLCANO_ERUPTS("Huge volcano erupts!", "Particles in the air\nreflect sunlight and\ncool the Earth. Move\nahead two spaces"),
	FUSION_REACTOR("ITER, World’s largest fusion reactor is successful", "All players move\nforward two spaces."),
	SNOW_PACK("Low snow-pack this year", "Move back two\nspaces"),
	SOLAR_ROOF("Install solar panels on every roof! ", "Move ahead two \nspaces"),
	WINDFARM("Doldrums!", "Move back one space\nfor each wind farm"),
	WINTER_WARMTH("Unreasonable Winter Warmth!", "Relocate displaced\nindigenous peoples.\nMove back two spaces"),
	VEGETARIAN("All your citizens went vegetarian!", "Move ahead two\nspaces"),
	OVERCAST("Overcast!", "Move back one space\nfor eachsolar farm"),
	//TEST("Install personal windmills for everyone on your island", ""),
	ACID_OCEAN("Oceans acidify!", "Aqua tourism and\nfishing suffer.\nMove back two\nspaces."),
	
	;

	private final String title;
	private final String desc;
	private EnumCircumstanceCards(String title, String desc) {
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

	
}
