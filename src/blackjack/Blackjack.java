package blackjack;

public class Blackjack {
	
	final static int H = 700;
	final static int W = 1000;

	private static Hand player;
	private static Hand dealer;
	private static Deck deck;
	private static Card deckCard;
	private static Card holeCard;
	private static Game game;
	private static Gui table;

	public static void main(String[] args) {
		
		Gui.intro();
		
		player = new Hand();
		dealer = new Hand();
		deck = new Deck();

		game = new Game(player, dealer, deck);
		table = new Gui(game);

		initGame(); // Start the first game
	}

	public static void initGame() {
		// Reset the deck and hands
		deck.shuffle();
		player.clear(); 
		dealer.clear();
		game.setStake(0);

		// Draw deck cover card
		deckCard = new Card(Card.Suit.CLUBS, 1);
		deckCard.showBack();
		table.drawCard(deckCard, 2);

		// Deal cards to player and dealer
		Card currCard;

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
