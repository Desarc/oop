package oving5.card;

import java.util.ArrayList;

public class CardDeck {

	private ArrayList<Card> cards;
	
	public CardDeck() {
		cards = new ArrayList<Card>();
		String[] suits = {"S", "H", "D", "C" };
		for (String suit : suits) {
			for (int i = 1; i <= 13; i++) {
				cards.add(new Card(suit, i));
			}
		}
	}
	
	public int getCardCount() {
		return cards.size();
	}
	
	public Card getCard(int i) {
		if (i >= 0 && i < getCardCount()) {
			return cards.get(i);
		}
		return null;
	}
	
	public ArrayList<Card> deal(int n) {
		if (n > getCardCount()) {
			return null;
		}
		ArrayList<Card> dealtCards = new ArrayList<Card>();
		for (int i = 0; i < n; i++) {
			dealtCards.add(getCard(getCardCount()-1));
			cards.remove(getCardCount()-1);
		}
		return dealtCards;
	}
	
}
