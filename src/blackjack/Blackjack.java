package blackjack;

public class Blackjack {
	
	final static int H = 700;
	final static int W = 1000;
	
	public static void main(String[] args) {
		
		Hand player = new Hand();
		Hand dealer = new Hand();
		Deck deck;
		Card currCard;
		Card deckCard;
		Card holeCard;
		
		deck = new Deck();
		
		Game game = new Game(player, dealer, deck);
		
		Gui table = new Gui(game);
		
		// game sequence
		
		deckCard = new Card(Card.Suit.CLUBS, 1);
		deckCard.showBack();
		table.drawCard(deckCard, 2);
		
		currCard = deck.deal();
		player.take(currCard);
		table.drawCard(currCard, 1);
	
		currCard = deck.deal();
		dealer.take(currCard);
		table.drawCard(currCard, 0);
		
		currCard = deck.deal();
		player.take(currCard);
		table.drawCard(currCard, 1);
		
		holeCard = deck.deal();
		holeCard.showBack();
		dealer.take(holeCard);
		table.drawCard(holeCard, 3);
					
	}	
}
