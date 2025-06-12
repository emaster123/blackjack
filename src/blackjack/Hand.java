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

	    // Add values for all non-Ace cards, count Aces separately
	    for (Card card : hand) {
	        if (card.getValue() == 1) { // Assuming Ace is represented by 1
	            numOfAces++;
	        } else {
	            score += card.getValue();
	        }
	    }

	    // Add Aces optimally: count each as 11 if it doesn't cause bust, otherwise 1
	    for (int i = 0; i < numOfAces; i++) {
	        if (score + 11 <= 21) {
	            score += 11;
	        } else {
	            score += 1;
	        }
	    }

	    return score;
	}

	
	public int size() {
		return hand.size();
	}
	
	public void clear() {
		hand.clear();
	}
	
	
}
