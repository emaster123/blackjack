package blackjack;

import java.util.ArrayList;

public class Hand {
	
	private ArrayList<Card> hand;
	
	public Hand() {
		hand = new ArrayList<Card>();
	}
	
	public void take(Card card) {
		hand.add(card);
	}
	
	public void take(Deck deck) {
		hand.add(deck.deal());
	}
	
	public ArrayList<Card> getHand() {
		return hand;
	}
	
	public int getScore(int move) { // 0 is stand, 1 is hit 
		
		int score = 0;
		
		int valueOfAce = 11 - move*10; // if standing, then go for 11 (11 - 0*10). if hitting, go for 1 (11 - 1*10)
		
		for (Card card : hand) {
			if (card.getValue() == 1) {
				score += valueOfAce;
			} else {
				score += card.getValue();
			}
		}
		
		return score;
		
 	}
	
	public int size() {
		return hand.size();
	}
	
}
