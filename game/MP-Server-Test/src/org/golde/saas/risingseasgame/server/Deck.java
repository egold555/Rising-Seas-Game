package org.golde.saas.risingseasgame.server;

import java.util.ArrayList;
import java.util.List;

import org.golde.saas.risingseasgame.shared.cards.EnumCardImpl;
import org.golde.saas.risingseasgame.shared.cards.EnumCircumstanceCards;
import org.golde.saas.risingseasgame.shared.cards.EnumDiplomaticStrategies;
import org.golde.saas.risingseasgame.shared.cards.EnumPowerCards;

public class Deck<EnumCard extends Enum<EnumCard> & EnumCardImpl> {

	public enum DeckType {
		CIRCUMSTANCE(5, DECK_CIRCUMSTANCE), POWER(10, DECK_POWER), DIPLOMATIC(20, DECK_DIPLOMATIC);
		
		private final int maxCardsInDeck;
		DeckType(int maxCardsInDeck, List<? extends EnumCardImpl> listOfCards){
			this.maxCardsInDeck = maxCardsInDeck;
		}
		
	}

	private static List<EnumCircumstanceCards> DECK_CIRCUMSTANCE = new ArrayList<EnumCircumstanceCards>();
	private static List<EnumPowerCards> DECK_POWER = new ArrayList<EnumPowerCards>();
	private static List<EnumDiplomaticStrategies> DECK_DIPLOMATIC = new ArrayList<EnumDiplomaticStrategies>();
	
	
	private void shufleDeck() {
		
	}
	
	private void dddd() {
		getNextCardInDeck(DECK_POWER);
	}
	
	public CardInDeck getNextCardInDeck(List<? extends EnumCardImpl> listOfCards) {
		return null;
	}
	
	class CardInDeck {
		
	}
	
}
