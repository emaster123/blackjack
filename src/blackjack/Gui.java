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
	private JButton chip10, chip25, chip50, chip100, chip500;

	// Chip pictures
	private Image whiteChip, blueChip, redChip, greenChip, blackChip;// 500

	private Game game;

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
		p.setBackground(Color.GREEN.darker()); // Green background
		p.setLayout(null);

		// Chips
		money = new JLabel("Money: $600");
		money.setBounds(30, 40, 300, 40);
		money.setFont(new Font("SansSerif", Font.PLAIN, 24));
		p.add(money);

		// Stake info
		stake = new JLabel("Stake: $x");
		stake.setForeground(Color.YELLOW);
		stake.setFont(new Font("SansSerif", Font.BOLD, 14));
		stake.setBounds(30, 100, 200, 20);
		p.add(stake);

		victory = new JLabel("You won: $x");
		victory.setForeground(Color.YELLOW);
		victory.setFont(new Font("SansSerif", Font.BOLD, 14));
		victory.setBounds(30, 120, 200, 20);
		p.add(victory);

		// Bust message
		result = new JLabel(""); // Show who won/lost/result
		result.setForeground(Color.YELLOW);
		result.setFont(new Font("SansSerif", Font.BOLD, 20));
		result.setBounds(220, 140, 250, 30);
		p.add(result);

		// Chip buttons

		chip10 = new JButton("$10");
		chip10.setPreferredSize(new Dimension(50, 70));
		chip10.addActionListener(this);
		chip10.setBounds(50, 200, 60, 60);
		chip10.setFont(new Font("Arial", Font.PLAIN, 12));

		chip25 = new JButton("$25");
		chip25.setPreferredSize(new Dimension(50, 120));
		chip25.addActionListener(this);
		chip25.setBounds(50, 270, 60, 60);
		chip25.setFont(new Font("Arial", Font.PLAIN, 12));

		chip50 = new JButton("$50");
		chip50.setPreferredSize(new Dimension(50, 170));
		chip50.addActionListener(this);
		chip50.setBounds(50, 340, 60, 60);
		chip50.setFont(new Font("Arial", Font.PLAIN, 12));

		chip100 = new JButton("$100");
		chip100.setPreferredSize(new Dimension(50, 220));
		chip100.addActionListener(this);
		chip100.setBounds(50, 410, 60, 60);
		chip100.setFont(new Font("Arial", Font.PLAIN, 10));

		chip500 = new JButton("$500");
		chip500.setPreferredSize(new Dimension(50, 270));
		chip500.addActionListener(this);
		chip500.setBounds(50, 480, 60, 60);
		chip500.setFont(new Font("Arial", Font.PLAIN, 10));

		add(chip10);
		add(chip25);
		add(chip50);
		add(chip100);
		add(chip500);

		add(p);
		setVisible(true);
	}

	// add a popup to ask for stake

	public void drawCard(Card card, int x, int y) {

		// instead of asking for value and suitImage, find out by checking a Card

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

		int buttonInt = Integer.parseInt(button.getText().substring(1));
		
		game.putIn(buttonInt);
		
		stake.setText("Stake: $"+game.getStake());
		money.setText("Money: $"+game.getBalance());
	}

	// for testing -->
//	public static void main(String[] args) {
//			Gui g = new Gui();
//	        g.drawCard(new Card(Card.Suit.HEARTS, 10), 300, 100);
//	        g.drawCard(new Card(Card.Suit.SPADES, 5), 370, 100);
//	        g.drawCard(new Card(Card.Suit.DIAMONDS, 2), 440, 100);
//	   
//	}
}
