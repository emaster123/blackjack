package blackjack;

public class Blackjack {
	
	final static int H = 700;
	final static int W = 1000;
	
	public static void main(String[] args) {
		
		Hand player = new Hand();
		Hand dealer = new Hand();
		
		Game game = new Game(player, dealer);
		
		Gui table = new Gui(game);
		Card c = new Card(Card.Suit.HEARTS, 10);
		Card d = new Card(Card.Suit.SPADES, 3);
		Card e = new Card(Card.Suit.DIAMONDS, 2);
		Card f = new Card(Card.Suit.CLUBS, 2);
		table.drawCard(c, 0);
		table.drawCard(d, 1);
		table.drawCard(e, 2);
		table.drawCard(f, 1);
		
				
	}	
}
