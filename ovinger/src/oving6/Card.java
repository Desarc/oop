package oving6;

import java.util.ArrayList;

public class Card implements Comparable<Card> {

	ArrayList<String> suits = new ArrayList<String>();

	private String suit;
	private int face;

	public Card(String suit, int face) {
		suits.add("S");
		suits.add("H");
		suits.add("D");
		suits.add("C");
		if (suits.contains(suit)) {
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

	public int compareTo(Card card) {
		if (card.getFace() < face) {
			if (card.getFace() != 1) {
				return 1;
			}
			else {
				return -1;
			}
		}
		else if (card.getFace() > face) {
			if (face != 1) {
				return -1;
			}
			else {
				return 1;
			}
		}
		else {
			if (suits.indexOf(card.getSuit()) > suits.indexOf(suit)) {
				return 1;
			}
			else if (suits.indexOf(card.getSuit()) < suits.indexOf(suit)) {
				return -1;
			}
			else {
				return 0;
			}
		}

	}
}
