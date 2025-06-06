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
	
	public ArrayList<Card> getHand() {
		return hand;
	}
	
	public int getScore() { 
		
		int score = 0;
		int numOfAces = 0;
		
		for (Card card : hand) {

			if (card.getValue() == 1) {
				numOfAces++;
			} else {
				score += card.getValue();
			}
		}
		
		
		for (int i = 0; numOfAces > i; i++) {
			if (score < 10) {
				score+=11;
			} else {
				score ++;
			}
		}	

		
		return score;
		
 	}
	
	public int size() {
		return hand.size();
	}
	
}
