package org.golde.saas.risingseasgame.shared.cards;

public enum EnumCircumstanceCards implements EnumCardImpl {
	
	CARBON_FARM("Your carbon farming initiative pays off!", "Move ahead one space"),
	HOME_BATTERY_SYSTEMS("Defeat one opponent to win free home battery systems.", "Choose an opponent and play one round of rock, paper, scissors. Winner moves ahead one space, loser back"),
	WILD_FIRE("Wild Fire!", "Move one space back for each forest!"),
	SOLAR_ROADS("Install solar roads", "Move ahead one space"),
	CARBON_SCRUBBER("Smart planning!", "Your island invests in carbon scrubber technology. Move ahead one space"),
	GEOTHERMAL_VENTS("Geothermal vents found!", "Use the energy to heat your homes. Move ahead two spaces."),
	TORNADO("Tornado!", "Move back one space for each coal plant"),
	SCARCE_RESOURCES("War Over Scarce Resources", "Choose an opponent and play one round of rock, paper, scissors. Winner moves ahead one space, loser back"),
	SAILBOAT_FORCE("Convert your Navy to an all sailboat force!", "Move ahead one space."),
	VOLCANO_ERUPTS("Huge volcano erupts!", "Particles in the air reflect sunlight and cool the Earth. Move ahead two spaces"),
	FUSION_REACTOR("ITER, World’s largest fusion reactor is successful", "All players move forward two spaces."),
	SNOW_PACK("Low snow-pack this year", "Move back two spaces"),
	SOLAR_ROOF("Install solar panels on every roof! ", "Move ahead two spaces"),
	WINDFARM("Doldrums!", "Move back one space for each wind farm"),
	WINTER_WARMTH("Unreasonable Winter Warmth!", "Relocate displaced indigenous peoples. Move back two spaces"),
	VEGETARIAN("All your citizens went vegetarian!", "Move ahead two spaces"),
	OVERCAST("Overcast!", "Move back one space for each solar farm"),
	//TEST("Install personal windmills for everyone on your island", ""),
	ACID_OCEAN("Oceans acidify!", "Aqua tourism and fishing suffer. Move back two spaces."),
	
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

	@Override
	public String getImage() {
		return name();
	}

	@Override
	public Enum getEnum() {
		return this;
	}

	
}
