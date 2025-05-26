package blackjack;

import java.util.ArrayList;
import java.util.Random;
import blackjack.Card.Suit;

public class Deck {

	private ArrayList<Card> deck;
	
	public Deck() {
		
		deck = fillDeck();
		
	}
	
	// fills the deck anew
	private ArrayList<Card> fillDeck() {
		
		ArrayList<Card> result = new ArrayList<Card>();
		
		for (Suit suit : Suit.values()) {
			
			for (int i = 0; i < 13; i++) {
				result.add(new Card(suit, i));
			}
			
		}
		
		return result;	
		
	}
	
	
	/* shuffles the deck
	* pre: the deck is untouched
	*/
	public void shuffle() {
		
		// Fisher-Yates shuffle
		Random r = new Random();
		for (int i = deck.size()-1; i > 0; i--) {
			int index = r.nextInt(i+1);
			Card n = deck.get(index);
			deck.set(index, deck.get(i));
			deck.set(i, n);
		}
		
		deck.remove(0); // burn the first card
		
	}
	
	public Card deal() {
		
		Card result = deck.get(0);
		deck.remove(0);
		return result;
		
	}
	
	public ArrayList<Card> getDeck() {
		return deck;
	}
		
}
