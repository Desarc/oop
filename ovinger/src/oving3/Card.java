package oving3;

public class Card {
	String suit;
	int face;

	public Card() {
	}
	
	public Card(String suit, int face) {
		this.suit = suit;
		this.face = face;
	}
	
	public String toString(){
		String s = "";
		s += suit;
		s += face;
		return s;
	}
}
