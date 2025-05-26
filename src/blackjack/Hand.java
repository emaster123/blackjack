package blackjack;

import java.util.ArrayList;

public class Hand {
	
	private ArrayList<Card> hand;
	private int aces;
	
	public Hand() {
		hand = new ArrayList<Card>();
		aces = 0;
	}
	
	public void take(Card card) {
		if (card.getValue() == 1) { // ace
			aces++;
		}
		hand.add(card);
	}
	
	public ArrayList<Card> getHand() {
		return hand;
	}
	
	public int[] getScores() {
		
		int[] scores = new int [aces+1];
		int acesCounted = aces;
		
		
		
 	}
	
}
