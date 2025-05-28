package blackjack;

public class Blackjack {

	public static void main(String[] args) {
		
		Gui table = new Gui();
		
		Deck deck = new Deck();
		Card deckCard = new Card(Card.Suit.CLUBS, 1);
		deckCard.showBack(); 
		table.drawCard(deckCard, 500,200);
		
		Hand player = new Hand();
		Hand dealer = new Hand();
				
	}	
}
