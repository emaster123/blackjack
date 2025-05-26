package blackjack;

import java.util.ArrayList;

public class Hand {
	
	private ArrayList<Card> hand;
	private int aces;

	public void take(Card card) {
		hand.add(card);
	}
	
	public ArrayList<Card> getHand() {
		return hand;
	}
	
	public int[] getScores() {
		
		int[] scores = new int [aces+1];
		int acesCounted = aces;
		
		for (int score : scores) {
			
			
			
		}
		
 	}
	
}
