package blackjack;

import javax.swing.*;
import java.awt.*;

public class Gui extends JFrame {

	// Suit images
	private Image heartImg;
	private Image spadeImg;
	private Image diamondImg;
	private Image clubImg;
	private Image backImg;
	
	private JPanel p;

	private JLabel chips;
	private JLabel putIn;
	private JLabel victory;
	private JLabel result;
	
	public Gui() {
		setTitle("Blackjack Game");
		setSize(600, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		// Directly load suit images
		heartImg = new ImageIcon("heart.png").getImage();
		spadeImg = new ImageIcon("spade.png").getImage();
		diamondImg = new ImageIcon("diamond.png").getImage();
		clubImg = new ImageIcon("club.png").getImage();
		backImg = new ImageIcon("Back.png").getImage();

		p = new JPanel();
		p.setBackground(Color.GREEN.darker()); // Green background
		p.setLayout(null);

		// Chips
		chips = new JLabel("Money");
		chips.setBounds(30, 40, 100, 40);
		chips.setFont(new Font("SansSerif", Font.PLAIN, 24));
		p.add(chips);

		// Stake info
		putIn = new JLabel("Stake: $x");
		putIn.setForeground(Color.YELLOW);
		putIn.setFont(new Font("SansSerif", Font.BOLD, 14));
		putIn.setBounds(30, 100, 200, 20);
		p.add(putIn);

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

		add(p);
		setVisible(true);
	}

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
			valueLabel.setHorizontalAlignment(JLabel.CENTER); // simpler constant
			valueLabel.setBounds(5, 5, 50, 30);
			cardPanel.add(valueLabel);

			Image scaled = suit.getScaledInstance(20, 20, Image.SCALE_DEFAULT);
			// scales it to normal size, part of our research
			JLabel suitLabel = new JLabel(new ImageIcon(scaled));
			suitLabel.setBounds(20, 40, 20, 20);
			cardPanel.add(suitLabel);

		}

		p.add(cardPanel);
	}

}
