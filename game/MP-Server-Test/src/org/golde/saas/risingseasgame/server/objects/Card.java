package org.golde.saas.risingseasgame.server.objects;

import org.golde.saas.risingseasgame.server.Player;
import org.golde.saas.risingseasgame.shared.cards.EnumCardImpl;
import org.golde.saas.risingseasgame.shared.cards.EnumCircumstanceCards;
import org.golde.saas.risingseasgame.shared.cards.EnumDiplomaticStrategies;

public class Card<EnumCard extends Enum<EnumCard> & EnumCardImpl> {

	private final EnumCard theEnum;
	
	public Card(EnumCard theEnum) {
		this.theEnum = theEnum;
	}
	
	public void doAction(Player player) {
		if(theEnum instanceof EnumCircumstanceCards) {
			doCircumstanceAction((EnumCircumstanceCards)theEnum);
		}
		else if(theEnum instanceof EnumDiplomaticStrategies) {
			doDiplomaticAction((EnumDiplomaticStrategies)theEnum);
		}
			
	}
	
	private void doCircumstanceAction(EnumCircumstanceCards card) {
		switch(card) {
		case ACID_OCEAN:
			break;
		case CARBON_FARM:
			break;
		case CARBON_SCRUBBER:
			break;
		case FUSION_REACTOR:
			break;
		case GEOTHERMAL_VENTS:
			break;
		case HOME_BATTERY_SYSTEMS:
			break;
		case OVERCAST:
			break;
		case SAILBOAT_FORCE:
			break;
		case SCARCE_RESOURCES:
			break;
		case SNOW_PACK:
			break;
		case SOLAR_ROADS:
			break;
		case SOLAR_ROOF:
			break;
		case TORNADO:
			break;
		case VEGETARIAN:
			break;
		case VOLCANO_ERUPTS:
			break;
		case WILD_FIRE:
			break;
		case WINDFARM:
			break;
		case WINTER_WARMTH:
			break;
		default:
			break;
		
		}
	}
	
	private void doDiplomaticAction(EnumDiplomaticStrategies card) {
		switch(card) {
		case Covert_Operation:
			break;
		case Economic_Diplomacy:
			break;
		case Emissary:
			break;
		case Espionage:
			break;
		case Gun_Boat_Diplomacy:
			break;
		case Immunity:
			break;
		case Sanctions:
			break;
		default:
			break;
		
		}
	}
	
}
