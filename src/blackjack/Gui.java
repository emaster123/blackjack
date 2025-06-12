package blackjack;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Gui extends JFrame implements ActionListener {

    // Suit images
    private Image heartImg, spadeImg, diamondImg, clubImg, backImg;

    private JPanel p, holeCardDrawing;

    private JLabel money, stake, victory, result;

    private Card holeCard; // save it to flip it

    // Chip Buttons
    private JButton chip1, chip5, chip10, chip25, chip50, chip100, restartstake;

    // Stand/hit/double buttons
    private JButton stand, hit, doubleDown, restart;

    private Game game;

    // card coordinates
    private int dealerX = 500, playerX = 500;
    private final int DECKX = 800, DECKY = 300, DEALERY = 100, PLAYERY = 550, HOLEX = 580;

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
        
        restartstake = new JButton("Reset stake");
        restartstake.setPreferredSize(new Dimension(50,120));
        restartstake.addActionListener(this);
        restartstake.setBounds(50,180,60,60);
        restartstake.setFont(new Font("Arial", Font.PLAIN, 10));
        
        
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

    // add a popup to ask for stake

    public void drawCard(Card card, int holder) { // 0, 1, 2, 3 are the dealer, player, deck, hole respectively

        // instead of asking for value and suitImage, find out by checking a Card
        int x = 0;
        int y = 0;

        switch (holder) {
        case 0: // Dealer's card is drawn
            x = dealerX;
            y = DEALERY;
            dealerX += 80;
            break;
        case 1: // Player's card is drawn
            x = playerX;
            y = PLAYERY;
            playerX += 80;
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

    public void message(String s) {
        result.setText(s);
        repaint();
    }

    public void flipHoleCard() {
        p.remove(holeCardDrawing);
        holeCard.showFront();
        System.out.println("The hole card is " + holeCard);
        drawCard(holeCard, 3);
    }

    public void actionPerformed(ActionEvent e) {
        int result = -1;

        JButton button = (JButton) e.getSource();

        if (!(button.equals(stand) || button.equals(hit) || button.equals(doubleDown) || button.equals(restart)) && game.isBetting()) { 
        	// putting a bet, do not read text
            int moneyIn = Integer.parseInt(button.getText().substring(1));
            if (!game.putIn(moneyIn)) {
                message("Not enough money");
            }
        } else if (button.equals(restart) && restart.isVisible()) { // restart

            clearTable();
            Blackjack.initGame();

        } else if (game.getStake() == 0) { // can't do anything unless you have a stake

        	game.setBetting(false);
            message("First put in a bet");

        } else { // here you can play

            if (button.equals(stand)) { // stand

                // reveal dealers hole card -> if dealer has less than 17, he draws until past
                // 17 -> compare

                doubleDown.setVisible(false);
                result = stand();

            } else if (button.equals(hit)) { // hit

                // if over 21 -> lose, else keep going. if 21 then reveal then auto win

                doubleDown.setVisible(false);
                result = hit();

            } else if (button.equals(doubleDown) && doubleDown.isVisible()) { // double bet

                if (!game.putIn(game.getStake())) {
                    message("Not enough money");
                } else {

                    game.putIn(game.getStake());

                    result = hit();

                    if (result != 22) { // if the player didn't bust from hitting
                        result = stand(); // then stand
                    }

                }
            }

            restart.setVisible(true);
            
            switch (result) {
            case 11: // dealer bj
                message("Dealer has a Blackjack!");
                game.lose();
                break;
            case 12: // dealer busts
                message("Dealer busts! Player wins!");
                game.win(2);
                break;
            case 13: // dealer wins
                message("Dealer wins!");
                game.lose();
                break;
            case 21: // player bj
                message("Player has a Blackjack!");
                game.win(2.5);
                break;
            case 22: // player busts
                message("Player busts! Dealer wins!");
                game.lose();
                break;
            case 23: // player wins
                message("Player wins!");
                game.win(2);
                break;
            case 30: // push
                message("Push!");
                game.win(1);
                break;
            default: restart.setVisible(false);break;
            }
        }

        stake.setText("Stake: $" + game.getStake());
        money.setText("Money: $" + game.getBalance());
        victory.setText("You win $ " + game.getWinnings());

    }

    public void clearTable() {

        // Remove all card components from the panel
        Component[] components = p.getComponents();
        for (int i = components.length - 1; i >= 0; i--) {
            Component c = components[i];
            // Skip JLabel components like money, stake, victory, result
            if (!(c instanceof JLabel)) {
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

    public void finish() {
        restart.setVisible(true);
    }

    public int stand() {
        flipHoleCard();

        for (Card card: game.stand()) {

            drawCard(card, 0);

            // how to slow down the drawing process?

        }
        finish();
        return game.compare();

    }

    public int hit() {
        drawCard(game.hit(), 1);

        if (game.compare() == 22) { // Player busts
            finish();
            message("Lose!");
            return 22;
        } else if (game.compare() == 21) { // Player BJ
            return 21;
        } else {
            return -1; // nothing
        }
    }
    
    public static void intro() {
    	
    	int opening = JOptionPane.showConfirmDialog(null, "Blackjack Game\nBy Eli L, Daniel C, David S\nDo you know the rules?", "Blackjack", JOptionPane.YES_NO_OPTION);
    	
    	if (opening == JOptionPane.NO_OPTION) {
    		JOptionPane.showMessageDialog(null, 
    					"Blackjack is a game of chance. You have to get as close as you can to a score of 21, but not over."+
    					"\nNumber cards are worth as much as their number says, face cards are worth 10 and an ace can either be 1 or 11."+
    					"\nPlace a bet with the buttons on the side"+
    					"\nYou are able to hit, stand or double."+
    					"\nHit: Take a card"+
    					"\nStand: Finish taking cards and let the dealer take cards. He must pick up cards until he has gotten to 17"+
    					"\nDouble: Double your bet, hit once, then stand."+
    					"\nGood luck!"
    				);
    	}
    }

}