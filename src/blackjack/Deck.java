package blackjack;

import java.util.ArrayList;
import java.util.Collections;
import blackjack.Card.Suit;

public class Deck {

	private ArrayList<Card> deck;
	
	public Deck() {
		
		deck = fillDeck();
		shuffle();
		
	}
	
	// fills the deck anew
	private ArrayList<Card> fillDeck() {
		
		ArrayList<Card> result = new ArrayList<Card>();
		
		for (Suit suit : Suit.values()) {
			
			for (int i = 1; i < 14; i++) {
				result.add(new Card(suit, i));
			}
			
		}
		
		return result;	
		
	}
	
	
	/* shuffles the deck
	* pre: the deck is untouched
	*/
	public void shuffle() {
		
		Collections.shuffle(deck);
	}
	
	public Card deal() {
		
		Card result = deck.get(0);
		deck.remove(0);
		System.out.println("A " + result + " is drawn");
		return result;
		
	}
	
	
	public ArrayList<Card> getDeck() {
		return deck;
	}
		
}
