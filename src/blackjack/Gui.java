package blackjack;

import javax.swing.*;
import java.awt.*;

public class Gui extends JFrame {

    // Suit images
    private Image heartImg;
    private Image spadeImg;
    private Image diamondImg;
    private Image clubImg;

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

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(0, 153, 0)); // Green background
        mainPanel.setLayout(null);

        // Chips
        JLabel chips = new JLabel("Money");
        chips.setBounds(30, 40, 100, 40);
        chips.setFont(new Font("SansSerif", Font.PLAIN, 24));
        mainPanel.add(chips);

        // Stake info
        JLabel putIn = new JLabel("You put in: $300");
        putIn.setForeground(Color.YELLOW);
        putIn.setFont(new Font("SansSerif", Font.BOLD, 14));
        putIn.setBounds(30, 100, 200, 20);
        mainPanel.add(putIn);

        JLabel won = new JLabel("You won: $0");
        won.setForeground(Color.YELLOW);
        won.setFont(new Font("SansSerif", Font.BOLD, 14));
        won.setBounds(30, 120, 200, 20);
        mainPanel.add(won);

        // Bust message
        JLabel bust = new JLabel("BUST (you lose)");
        bust.setForeground(Color.YELLOW);
        bust.setFont(new Font("SansSerif", Font.BOLD, 20));
        bust.setBounds(220, 140, 250, 30);
        mainPanel.add(bust);

        // Dealer cards
        drawCard(mainPanel, "K", spadeImg, 180, 40);
        drawCard(mainPanel, "A", heartImg, 270, 40);

        // Player cards
        drawCard(mainPanel, "10", diamondImg, 180, 250);
        drawCard(mainPanel, "9", clubImg, 270, 250);
        drawCard(mainPanel, "3", heartImg, 360, 250);

        add(mainPanel);
        setVisible(true);
    }

    private void drawCard(JPanel panel, String value, Image suitImage, int x, int y) {
        JPanel cardPanel = new JPanel();
        cardPanel.setLayout(null);
        cardPanel.setBounds(x, y, 60, 80);
        cardPanel.setBackground(Color.WHITE);
        cardPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        JLabel valueLabel = new JLabel(value, SwingConstants.CENTER);
        valueLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        valueLabel.setForeground(Color.RED);
        valueLabel.setBounds(5, 5, 50, 30);
        cardPanel.add(valueLabel);

        Image scaled = suitImage.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        JLabel suitLabel = new JLabel(new ImageIcon(scaled));
        suitLabel.setBounds(20, 40, 20, 20);
        cardPanel.add(suitLabel);

        panel.add(cardPanel);
    }

    public static void main(String[] args) {
        new Gui();
    }
}
