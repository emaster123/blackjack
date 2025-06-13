package blackjack;

/**
 * @author David S
 */

/**
 * The class where everything runs. 
 */
public class Blackjack {
	
	/** 
	 * Constant height of the game window in pixels.
	 */
	final static int H = 700;

	/** 
	 * Constant width of the game window in pixels.
	 */
	final static int W = 1000;

	/**
	 * The player's hand of cards.
	 * Used to store and evaluate the player's current cards in play.
	 */
	private static Hand player;

	/**
	 * The dealer's hand of cards.
	 * Stores the cards held by the dealer during the current round.
	 */
	private static Hand dealer;

	/**
	 * The deck of cards used in the game.
	 * Provides shuffled cards for dealing to player and dealer.
	 */
	private static Deck deck;

	/**
	 * The top card of the deck shown face-down (simulating the deck visually).
	 */
	private static Card deckCard;

	/**
	 * The dealer's hole card (face-down card revealed at the end of the round).
	 */
	private static Card holeCard;

	/**
	 * The game logic controller.
	 * Handles the rules, balance, betting, and win/loss logic.
	 */
	private static Game game;

	/**
	 * The GUI for displaying the table and interacting with the user.
	 */
	private static Gui table;

	
	
	/**
	 * Main method. First gives an intro, then creates everything necessary
	 * @param args
	 */
	public static void main(String[] args) {
		
		Gui.intro();
		
		player = new Hand();
		dealer = new Hand();
		deck = new Deck();

		game = new Game(player, dealer, deck);
		table = new Gui(game);

		initGame(); // Start the first game
	}

	/**
	 * Method that initializes the game. Clears the hands, resets the stake and bets the cards.
	 */
	public static void initGame() {
		// Reset the deck and hands
		deck = new Deck();
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
