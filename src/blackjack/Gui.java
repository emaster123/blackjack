package blackjack;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Gui extends JFrame implements ActionListener {

	// Suit images
	private Image heartImg, spadeImg, diamondImg, clubImg, backImg;

	private JPanel p;

	private JLabel money, stake, victory, result;

	// Chip Buttons
	private JButton chip1, chip5, chip10, chip25, chip50, chip100;

	// Chip pictures
	private Image whiteChip1, redChip5, blueChip10, greenChip25, orangeChip50, blackChip100;

	// Stand/hit/double buttons
	private JButton stand, hit, doubleBet, allIn;

	private Game game;

	// card coordinates
	private int dealerX = 500, playerX = 500;
	private final int DECKX = 800, DECKY = 300, DEALERY = 100, PLAYERY = 550;

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

		// Directly load chip images
		whiteChip1 = new ImageIcon("whiteChip1.png").getImage();
		redChip5 = new ImageIcon("redChip5.png").getImage();
		blueChip10 = new ImageIcon("10$_PokerChipImg.png").getImage();
		greenChip25 = new ImageIcon("greenChip25.png").getImage();
		orangeChip50 = new ImageIcon("orangeChip50.png").getImage();
		blackChip100 = new ImageIcon("blackChip100.png").getImage();

		this.game = game;

		p = new JPanel();
		p.setBackground(Color.GREEN.darker()); // Green background
		p.setLayout(null);

		// Chips
		money = new JLabel("Money: $600");
		money.setBounds(30, 10, 300, 40);
		money.setFont(new Font("SansSerif", Font.PLAIN, 24));
		p.add(money);

		// Stake info
		stake = new JLabel("Stake: $x");
		stake.setForeground(Color.YELLOW);
		stake.setFont(new Font("SansSerif", Font.BOLD, 14));
		stake.setBounds(30, 70, 200, 20);
		p.add(stake);

		victory = new JLabel("You won: $x");
		victory.setForeground(Color.YELLOW);
		victory.setFont(new Font("SansSerif", Font.BOLD, 14));
		victory.setBounds(30, 90, 200, 20);
		p.add(victory);

		// Bust message
		result = new JLabel(""); // Show who won/lost/result
		result.setForeground(Color.YELLOW);
		result.setFont(new Font("SansSerif", Font.BOLD, 20));
		result.setBounds(220, 140, 250, 30);
		p.add(result);

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

		add(chip1);
		add(chip5);
		add(chip10);
		add(chip25);
		add(chip50);
		add(chip100);

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

		doubleBet = new JButton("Double");
		doubleBet.setPreferredSize(new Dimension(100, 100));
		doubleBet.addActionListener(this);
		doubleBet.setBounds(250, 570, 75, 75);
		doubleBet.setFont(new Font("Arial", Font.PLAIN, 12));

		allIn = new JButton("All In");
		allIn.setPreferredSize(new Dimension(100, 100));
		allIn.addActionListener(this);
		allIn.setBounds(350, 570, 75, 75);
		allIn.setFont(new Font("Arial", Font.PLAIN, 12));

		add(stand);
		add(hit);
		add(doubleBet);
		add(allIn);

		add(p);
		setVisible(true);
	}

	// add a popup to ask for stake

	public void drawCard(Card card, int holder) { // 0, 1, 2, are the dealer, player, deck, respectively

		// instead of asking for value and suitImage, find out by checking a Card
		int x = 0;
		int y = 0;
		if (holder == 0) {
			x = dealerX;
			y = DEALERY;
			dealerX += 80;
		}
		if (holder == 1) {
			x = playerX;
			y = PLAYERY;
			playerX += 80;
		}
		if (holder == 2) {
			x = DECKX;
			y = DECKY;
		}
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
			cardPanel.setBounds(x, 80, 60, 80);
			cardPanel.setBackground(Color.WHITE);
			cardPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

			Image scaled = backImg.getScaledInstance(60, 80, Image.SCALE_DEFAULT);
			// scales it to normal size, part of our research
			JLabel suitLabel = new JLabel(new ImageIcon(scaled));
			suitLabel.setBounds(0, 0, 60, 80);
			cardPanel.add(suitLabel);

		} else {
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
				suitedValue = "" + card.getValue();
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

		p.add(cardPanel);
		// also neccessary for testing
		repaint();
	}

	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton) e.getSource();

		if (button.equals(stand)) {
			JOptionPane.showMessageDialog(p, "Pussy boy is standing", "No balls", JOptionPane.PLAIN_MESSAGE);
		} else if (button.equals(hit)) {
			JOptionPane.showMessageDialog(p, "Gimme another card", "Ballsy", JOptionPane.PLAIN_MESSAGE);
		} else if (button.equals(doubleBet)) {
			JOptionPane.showMessageDialog(p, "Gimme another card and take my money", "Ballsier",
					JOptionPane.PLAIN_MESSAGE);
		} else {
			int buttonInt = Integer.parseInt(button.getText().substring(1));
			int result = game.putIn(buttonInt);
			if (result == 1) {
				JOptionPane.showMessageDialog(p, "Not enough money for that bet!", "Brokey!",
						JOptionPane.PLAIN_MESSAGE);
			}
		}
		stake.setText("Stake: $" + game.getStake());
		money.setText("Money: $" + game.getBalance());
	}
}
