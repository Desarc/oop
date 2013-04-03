package oving3;

import java.util.ArrayList;

import acm.graphics.GImage;
import acm.program.GraphicsProgram;

public class CardDeck extends GraphicsProgram {
	
	ArrayList<Card> cards;
	
	public void init() {
		cards = new ArrayList<Card>();
		String[] suits = {"S", "H", "D", "C" };
		for (int i=0;i<suits.length;i++) {
			for (int j=1;j<=13;j++) {
				cards.add(new Card(suits[i], j));
			}
		}
		
	}
	
	public void run() {
		setSize(800, 600);
		drawDeck();
	}
	
	public Card getCard(int pos) {
		return cards.get(pos);
	}
	
	public void drawDeck() {
		int x = 0, y = 0;
		for (int i=1; i<cards.size()+1; i++) {
			add(createGImage(cards.get(i-1).suit, cards.get(i-1).face),x,y);
			x+=40;
			if (i%13==0 && i!=0) {
				x=0;
				y+=80;
			}
			
		}
		
	}
	
	
	GImage createGImage(String suit, int value) {
		String name="";
		switch(suit.charAt(0)){
		case 'H':
			name+="hearts";
			break;
		case 'D':
			name+="diamonds";
			break;
		case 'C':
			name+="clubs";
			break;
		case 'S':
			name+="spades";
			break;
		}
		name+="-";
		switch(value) {
		case 1:
			name+="a";
			break;
		case 11:
			name+="j";
			break;
		case 12:
			name+="q";
			break;
		case 13:
			name+="k";
			break;
		default:
			name+="" + value;
		}

		name+="-150.png";
		return new GImage("oving3/img/" + name);
	}
}
