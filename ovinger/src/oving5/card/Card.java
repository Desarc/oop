package oving5.card;

public class Card {
	
	private String suit;
	private int face;
	
	public Card(String suit, int face) {
		if (suit.equals("S") || suit.equals("D") || suit.equals("H") || suit.equals("C")) {
			this.suit = suit;
		}
		if (face <= 13 && face > 0) {
			this.face = face;
		}
		else {
			this.face = -1;
		}
	}

	public String getSuit() {
		return suit;
	}

	public int getFace() {
		return face;
	}
	
	public String toString() {
		return suit+face;	
	}
}
