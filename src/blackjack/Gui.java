/**
 * @author Eli L, David S, Daniel C
 */

package blackjack;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The graphical user interface of the game
 */

public class Gui extends JFrame implements ActionListener {

	/** 
	 * Image object representing the heart suit icon used for card rendering.
	 */
	private Image heartImg;

	/** 
	 * Image object representing the spade suit icon used for card rendering.
	 */
	private Image spadeImg;

	/** 
	 * Image object representing the diamond suit icon used for card rendering.
	 */
	private Image diamondImg;

	/** 
	 * Image object representing the club suit icon used for card rendering.
	 */
	private Image clubImg;

	/** 
	 * Image object representing the back of a playing card.
	 */
	private Image backImg;

	/**
	 * Primary container panel for the game GUI.
	 */
	private JPanel p;

	/**
	 * Dedicated panel used to draw or display the dealer's hole card.
	 */
	private JPanel holeCardDrawing;

	/**
	 * Label that displays the player's current balance or available money.
	 */
	private JLabel money;

	/**
	 * Label that displays the current stake for the round.
	 */
	private JLabel stake;

	/**
	 * Label used to show how much the player won last time.
	 */
	private JLabel victory;

	/**
	 * Label that can be used to show messages (victory/loss or errors) to the player
	 */
	private JLabel result;

	/**
	 * Label showing the overall profit
	 */
	private JLabel profit;
	/**
	 * Reference to the dealer’s hole card (face-down card) to be revealed later.
	 */
	private Card holeCard;

	/**
	 * Button allowing the player to add a $1 to the stake.
	 */
	private JButton chip1;

	/**
	 * Button allowing the player to add a $5 to the stake.
	 */
	private JButton chip5;

	/**
	 * Button allowing the player to add a $10 to the stake.
	 */
	private JButton chip10;

	/**
	 * Button allowing the player to add a $25 to the stake.
	 */
	private JButton chip25;

	/**
	 * Button allowing the player to add a $50 to the stake.
	 */
	private JButton chip50;

	/**
	 * Button allowing the player to add a $100 to the stake.
	 */
	private JButton chip100;

	/**
	 * Button that resets the stake
	 */
	private JButton restartstake;

	/**
	 * Button that allows the player to stand
	 */
	private JButton stand;

	/**
	 * Button that allows the player to hit
	 */
	private JButton hit;

	/**
	 * Button that allows the player to double down 
	 */
	private JButton doubleDown;

	/**
	 * Button that restarts the game, resetting all necessary variables and UI elements.
	 */
	private JButton restart;

	/**
	 * Reference to the main Game object managing the logic and state of the blackjack game.
	 */
	private Game game;

	/**
	 * X-coordinate position for drawing dealer cards on the GUI.
	 */
	private int dealerX = 500;

	/**
	 * X-coordinate position for drawing player cards on the GUI.
	 */
	private int playerX = 500;

	/**
	 * Constant X-coordinate for the card deck's location on the GUI.
	 */
	private final int DECKX = 800;

	/**
	 * Constant Y-coordinate for the card deck's location on the GUI.
	 */
	private final int DECKY = 300;

	/**
	 * Constant Y-coordinate for the dealer's card row.
	 */
	private final int DEALERY = 100;

	/**
	 * Constant Y-coordinate for the player's card row.
	 */
	private final int PLAYERY = 550;

	/**
	 * Constant X-coordinate for positioning the dealer's hole card.
	 */
	private final int HOLEX = 580;

	/**
	 * Constructs the graphical user interface for the Blackjack game.
	 * <p>
	 * This constructor initializes and lays out all GUI components, including:
	 * <ul>
	 *   <li>Suit images used for rendering cards</li>
	 *   <li>Labels for displaying money, stake, victory, and game results</li>
	 *   <li>Chip buttons for placing bets in varying denominations ($1 to $100)</li>
	 *   <li>Action buttons for gameplay: Hit, Stand, Double Down, and Restart</li>
	 *   <li>Layout panels and configuration for the main game window</li>
	 * </ul>
	 * The GUI is styled with a green table background and fixed window size,
	 * and responds to user interaction via ActionListeners.
	 * 
	 * @param game the {@code Game} instance controlling game logic and state.
	 * 
	 * @author David S
	 */
	public Gui(Game game) {
		setTitle("Blackjack Game");
		setSize(1000, 700);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);

		// Directly load suit images
		heartImg = new ImageIcon("heart.png").getImage();
		spadeImg = new ImageIcon("spade.png").getImage();
		diamondImg = new ImageIcon("diamond.png").getImage();
		clubImg = new ImageIcon("club.png").getImage();
		backImg = new ImageIcon("Back.png").getImage();

		this.game = game;

		p = new JPanel();
		p.setBackground(Color.GREEN.darker().darker()); // Green background
		p.setLayout(null);

		// Chips
		money = new JLabel("Money: $600");
		money.setBounds(30, 10, 300, 40);
		money.setForeground(Color.YELLOW);
		money.setFont(new Font("SansSerif", Font.BOLD, 24));
		p.add(money);

		// Stake info
		stake = new JLabel("Stake: $0");
		stake.setForeground(Color.YELLOW);
		stake.setFont(new Font("SansSerif", Font.BOLD, 14));
		stake.setBounds(30, 70, 200, 20);
		p.add(stake);

		victory = new JLabel("You won: $0");
		victory.setForeground(Color.YELLOW);
		victory.setFont(new Font("SansSerif", Font.BOLD, 14));
		victory.setBounds(30, 90, 200, 20);
		p.add(victory);

		// Bust message
		result = new JLabel(""); // Show who won/lost/result
		result.setForeground(Color.YELLOW);
		result.setFont(new Font("SansSerif", Font.BOLD, 20));
		result.setBounds(240, 140, 250, 30);
		p.add(result);
		
		profit = new JLabel("Highest Profit: $" + game.readHighestProfit());
		profit.setForeground(Color.YELLOW);
		profit.setFont(new Font("SansSerif", Font.BOLD, 20));
		profit.setBounds(750, 10, 300, 40);
		p.add(profit);

		// Chip buttons

		chip1 = new JButton("$1");
		chip1.setPreferredSize(new Dimension(50, 70));
		chip1.addActionListener(this);
		chip1.setBounds(50, 130, 60, 60);
		chip1.setFont(new Font("Arial", Font.PLAIN, 12));

		chip5 = new JButton("$5");
		chip5.setPreferredSize(new Dimension(50, 70));
		chip5.addActionListener(this);
		chip5.setBounds(50, 210, 60, 60);
		chip5.setFont(new Font("Arial", Font.PLAIN, 12));

		chip10 = new JButton("$10");
		chip10.setPreferredSize(new Dimension(50, 70));
		chip10.addActionListener(this);
		chip10.setBounds(50, 280, 60, 60);
		chip10.setFont(new Font("Arial", Font.PLAIN, 12));

		chip25 = new JButton("$25");
		chip25.setPreferredSize(new Dimension(50, 120));
		chip25.addActionListener(this);
		chip25.setBounds(50, 350, 60, 60);
		chip25.setFont(new Font("Arial", Font.PLAIN, 12));

		chip50 = new JButton("$50");
		chip50.setPreferredSize(new Dimension(50, 170));
		chip50.addActionListener(this);
		chip50.setBounds(50, 420, 60, 60);
		chip50.setFont(new Font("Arial", Font.PLAIN, 12));

		chip100 = new JButton("$100");
		chip100.setPreferredSize(new Dimension(50, 220));
		chip100.addActionListener(this);
		chip100.setBounds(50, 490, 60, 60);
		chip100.setFont(new Font("Arial", Font.PLAIN, 10));

		restartstake = new JButton("Reset stake");
		restartstake.setPreferredSize(new Dimension(120, 70));
		restartstake.addActionListener(this);
		restartstake.setBounds(120, 130, 100, 60);
		restartstake.setFont(new Font("Arial", Font.PLAIN, 10));

		add(chip1);
		add(chip5);
		add(chip10);
		add(chip25);
		add(chip50);
		add(chip100);
		add(restartstake);

		// Stand, hit, and double buttons

		stand = new JButton("Stand");
		stand.setPreferredSize(new Dimension(100, 100));
		stand.addActionListener(this);
		stand.setBounds(50, 570, 75, 75);
		stand.setFont(new Font("Arial", Font.PLAIN, 14));

		hit = new JButton("Hit");
		hit.setPreferredSize(new Dimension(100, 100));
		hit.addActionListener(this);
		hit.setBounds(150, 570, 75, 75);
		hit.setFont(new Font("Arial", Font.PLAIN, 14));

		doubleDown = new JButton("Double");
		doubleDown.setPreferredSize(new Dimension(100, 100));
		doubleDown.addActionListener(this);
		doubleDown.setBounds(250, 570, 75, 75);
		doubleDown.setFont(new Font("Arial", Font.PLAIN, 12));

		restart = new JButton("New game");
		restart.setPreferredSize(new Dimension(100, 100));
		restart.addActionListener(this);
		restart.setBounds(350, 570, 100, 75);
		restart.setFont(new Font("Arial", Font.PLAIN, 12));
		restart.setVisible(false);

		add(stand);
		add(hit);
		add(doubleDown);
		add(restart);

		add(p);
		setVisible(true);
	}

	
	/**
	 * Draws a card on the GUI at the appropriate position based on the card's owner.
	 * <p>
	 * This method determines the (x, y) drawing coordinates based on the holder type:
	 * <ul>
	 *   <li><b>0</b> - Dealer</li>
	 *   <li><b>1</b> - Player</li>
	 *   <li><b>2</b> - Deck</li>
	 *   <li><b>3</b> - Dealer's hole card (face-down)</li>
	 * </ul>
	 * Depending on whether the card is flipped, either the back of the card is shown or
	 * the suit and value are rendered. Suit icons and text colors are applied accordingly.
	 * The card panel is added to the main display panel, and the UI is updated via repaint.
	 * 
	 * @param card   the {@code Card} object to be drawn, either face-up or face-down.
	 * @param holder an integer code representing the card's owner or location:
	 *               <ul>
	 *                 <li>0 = Dealer</li>
	 *                 <li>1 = Player</li>
	 *                 <li>2 = Deck (used when dealing from deck)</li>
	 *                 <li>3 = Dealer’s hole card (face-down, second card)</li>
	 *               </ul>
	 * @author Daniel C
	 */
	public void drawCard(Card card, int holder) { // 0, 1, 2, 3 are the dealer, player, deck, hole respectively

		// instead of asking for value and suitImage, find out by checking a Card
		int x = 0;
		int y = 0;

		switch (holder) {
		case 0: // Dealer's card is drawn
			x = dealerX;
			y = DEALERY;
			dealerX += 80; // shift for next card
			break;
		case 1: // Player's card is drawn
			x = playerX;
			y = PLAYERY;
			playerX += 80; // shift
			break;
		case 2: // Deck card is drawn
			x = DECKX;
			y = DECKY;
			break;
		case 3: // Hole card is drawn (only different to dealer is that it is preset to the
			// second dealer card
			x = HOLEX;
			y = DEALERY;
			dealerX = 660; // get ready to draw 3rd card
			holeCard = card;
			break;
		}

		System.out.println("Drawing a " + card + " at x = " + x + " and y = " + y);

		JPanel cardPanel;
		Color textColour;

		switch (card.getSuit()) {
		case DIAMONDS:
			textColour = Color.RED;
			break;
		case HEARTS:
			textColour = Color.RED;
			break;
		case SPADES:
			textColour = Color.BLACK;
			break;
		case CLUBS:
			textColour = Color.BLACK;
			break;
		default:
			textColour = Color.GREEN;
			break;
		}

		if (card.isFlipped()) { 

			cardPanel = new JPanel();
			cardPanel.setLayout(null);
			cardPanel.setBounds(x, y, 60, 80);
			cardPanel.setBackground(Color.WHITE);
			cardPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

			Image scaled = backImg.getScaledInstance(60, 80, Image.SCALE_DEFAULT);
			// scales it to normal size, part of our research
			JLabel suitLabel = new JLabel(new ImageIcon(scaled));
			suitLabel.setBounds(0, 0, 60, 80);
			cardPanel.add(suitLabel);

		} else {

			// alternatively make this into char and then do some num bs to return the
			// suited value as a char

			String suitedValue = "";
			switch (card.getFace()) {
			case 1:
				suitedValue = "J";
				break;
			case 2:
				suitedValue = "Q";
				break;
			case 3:
				suitedValue = "K";
				break;
			case 0:

				if (card.getValue() == 1) {
					suitedValue = "A";
				} else {
					suitedValue = "" + card.getValue();
				}
				break;
			}

			Image suit;
			switch (card.getSuit()) {
			case HEARTS:
				suit = heartImg;
				break;
			case SPADES:
				suit = spadeImg;
				break;
			case CLUBS:
				suit = clubImg;
				break;
			case DIAMONDS:
				suit = diamondImg;
				break;
			default:
				suit = heartImg;
				System.out.println("Had to default");
				break;

			}

			cardPanel = new JPanel();
			cardPanel.setLayout(null);
			cardPanel.setBounds(x, y, 60, 80);
			cardPanel.setBackground(Color.WHITE);
			cardPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

			JLabel valueLabel = new JLabel(suitedValue);
			valueLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
			valueLabel.setForeground(textColour);
			valueLabel.setHorizontalAlignment(JLabel.CENTER);
			valueLabel.setBounds(5, 5, 50, 30);
			cardPanel.add(valueLabel);

			Image scaled = suit.getScaledInstance(20, 20, Image.SCALE_DEFAULT);
			// scales it to normal size, part of our research
			JLabel suitLabel = new JLabel(new ImageIcon(scaled));
			suitLabel.setBounds(20, 40, 20, 20);
			cardPanel.add(suitLabel);

		}

		if (holder == 3) {
			holeCardDrawing = cardPanel;
			p.add(holeCardDrawing);
		} else {
			p.add(cardPanel);
		}

		// also neccessary for testing
		repaint();
	}
	
	/**
	 * Updates the {@code result} to show a message 
	 * @param s the message
	 * @author Eli L
	 */

	public void message(String s) {
		result.setText(s);
		repaint();
	}

	/**
	 * Flips the hole card
	 * @author Eli L
	 */
	
	public void flipHoleCard() {
		p.remove(holeCardDrawing);
		holeCard.showFront();
		System.out.println("The hole card is " + holeCard);
		drawCard(holeCard, 3);
	}

	/**
	 * Handles user interactions from button clicks during the Blackjack game.
	 * <p>
	 * This method distinguishes between betting and playing phases:
	 * <ul>
	 *   <li>If in the betting phase, it processes chip inputs.</li>
	 *   <li>If playing, it processes actions like Stand, Hit, and Double Down.</li>
	 *   <li>Handles game result logic after each move and displays appropriate messages.</li>
	 *   <li>Updates stake, money, and winnings labels based on game state.</li>
	 * </ul>
	 *
	 * @param e the {@code ActionEvent} triggered by user interaction with a JButton.
	 * @author Eli L and David S
	 */
	public void actionPerformed(ActionEvent e) {
	    int result = -1;

	    // Identify which button was clicked
	    JButton button = (JButton) e.getSource();

	    // Handle chip betting phase
	    if (!(button.equals(stand) || button.equals(hit) || button.equals(doubleDown) || button.equals(restart))
	            && game.isBetting()) {

	        // Convert button label (e.g., "$25") to integer value
	        int moneyIn = Integer.parseInt(button.getText().substring(1));

	        // Try placing the bet; show error if insufficient funds
	        if (!game.putIn(moneyIn)) {
	            message("Not enough money");
	        }

	    // Handle restart logic (only if restart button is visible)
	    } else if (button.equals(restart) && restart.isVisible()) {

	        clearTable();              // Clear all cards and reset visuals
	        game.setBetting(true);     // Enable betting for the next round
	        Blackjack.initGame();      // Start a new game

	    // Enforce that a stake must be placed before playing
	    } else if (game.getStake() == 0) {

	        message("First put in a bet");

	    } else {
	        // Player is now taking game actions (hit, stand, double)
	        game.setBetting(false);

	        if (button.equals(stand)) {

	            // Player chooses to stand, dealer plays
	            doubleDown.setVisible(false);
	            result = stand();

	        } else if (button.equals(hit)) {

	            // Player hits (draws a card)
	            doubleDown.setVisible(false);
	            result = hit();

	        } else if (button.equals(doubleDown) && doubleDown.isVisible()) {

	            // Player doubles down (doubles bet and hits once)
	            if (!game.putIn(game.getStake())) {
	                message("Not enough money");
	            } else {
	                game.putIn(game.getStake());   // Double the stake
	                result = hit();                // Take one card

	                if (result != 22) {            // If not busted, auto-stand
	                    result = stand();
	                }
	            }
	        }

	        // Make restart button visible after round
	        restart.setVisible(true);

	        // Evaluate game result and apply consequences
	        switch (result) {
	            case 11: // Dealer has Blackjack
	                message("Dealer has a Blackjack!");
	                game.lose();
	                break;
	            case 12: // Dealer busts
	                message("Dealer busts! Player wins!");
	                game.win(2);
	                break;
	            case 13: // Dealer wins
	                message("Dealer wins!");
	                game.lose();
	                break;
	            case 21: // Player Blackjack
	                message("Player has a Blackjack!");
	                game.win(2.5);
	                break;
	            case 22: // Player busts
	                message("Player busts! Dealer wins!");
	                game.lose();
	                break;
	            case 23: // Player wins
	                message("Player wins!");
	                game.win(2);
	                break;
	            case 30: // Push (tie)
	                message("Push!");
	                game.win(1);
	                break;
	            default: // Game still ongoing or no result
	                restart.setVisible(false);
	                break;
	        }
	    }

	    // Update UI labels with latest game values
	    stake.setText("Stake: $" + game.getStake());
	    money.setText("Money: $" + game.getBalance());
	    victory.setText("You win $" + game.getWinnings());
	    profit.setText("Highest profit: $" + game.readHighestProfit());
	}

	
	/**
	 * Removes all card components by going through each component, seeing if it is not a JLabel or JButton. If it isn't,
	 * it deletes the component since it must be a JFrame (a card). Then it resets everything else.
	 * 
	 * @author Eli L
	 */
	
	public void clearTable() {

		// Remove all card components from the panel
		Component[] components = p.getComponents();
		for (int i = components.length - 1; i >= 0; i--) {
			Component c = components[i];
			// Skip JLabel components like money, stake, victory, result
			if (!(c instanceof JLabel || c instanceof JButton)) {
				p.remove(c);
			}
		}

		// Reset card positions
		dealerX = 500;
		playerX = 500;

		// Clear hole card reference and visual
		holeCard = null;
		holeCardDrawing = null;

		// Clear result message
		result.setText("");

		restart.setVisible(false);
		doubleDown.setVisible(true);

		// Refresh panel
		repaint();
	}

	/**
	 * When the round is over, the restart button appears to start a new game
	 * @author Eli L
	 */
	
	public void finish() {
		restart.setVisible(true);
	}

	/**
	 * Stand by flipping the hole card, running {@code game.stand()} and then drawing the cards from that method
	 * Then finish the round and run {@code game.compare()} to see the result
	 * 
	 * @author Eli L
	 */
	
	public int stand() {
		flipHoleCard();

		for (Card card : game.stand()) {

			drawCard(card, 0);

			// how to slow down the drawing process?

		}
		finish();
		return game.compare();

	}

	/**
	 * 
	 * Run the {@code game.run()} and then draw the card returned from it. 
	 * Run {@code game.compare()} to see if the round can be ended immediately
	 * 
	 * @return The result:
	 * <ul>
	 * 		<li> 22 - the player busts </li>
	 * 		<li> 21 - the player gets a blackjack </li>
	 * 		<li> -1 - the game hasn't ended </li>
	 * </ul>
	 * 
	 * @author Eli L 
	 */
	
	public int hit() {
		drawCard(game.hit(), 1);

		if (game.compare() > 21) { // Player busts
			finish();
			message("Lose!");
			return 22;
		} else if (game.compare() == 21) { // Player BJ
			return 21;
		} else {
			return -1; // nothing
		}
	}

	
	/**
	 * Gives an introductory pop-up about the authors and Blackjack
	 * @author Eli L
	 */
	public static void intro() {

		int opening = JOptionPane.showConfirmDialog(null,
				"Blackjack Game\nBy Eli L, Daniel C, David S\nDo you know the rules?", "Blackjack",
				JOptionPane.YES_NO_OPTION);

		if (opening == JOptionPane.NO_OPTION) {
			JOptionPane.showMessageDialog(null,
					"Blackjack is a game of chance. You have to get as close as you can to a score of 21, but not over."
							+ "\nNumber cards are worth as much as their number says, face cards are worth 10 and an ace can either be 1 or 11."
							+ "\nPlace a bet with the buttons on the side" + "\nYou are able to hit, stand or double."
							+ "\nHit: Take a card"
							+ "\nStand: Finish taking cards and let the dealer take cards. He must pick up cards until he has gotten to 17"
							+ "\nDouble: Double your bet, hit once, then stand." + "\nGood luck!");
		}
	}
}