package blackjack;

import java.util.ArrayList;

/**
 * Represents a player's or dealer's hand in a game of Blackjack.
 * A hand is a collection of cards with logic for calculating the score,
 * adding cards, clearing the hand, and getting its size.
 * 
 * Handles Blackjack-specific scoring rules, such as treating Aces as either
 * 1 or 11 depending on the current total.
 * 
 * @author Eli L
 */
public class Hand {

    /** The list of cards currently in the hand. */
    private ArrayList<Card> hand;

    /**
     * Constructs an empty hand.
     */
    public Hand() {
        hand = new ArrayList<Card>();
    }

    /**
     * Adds a card to the hand.
     *
     * @param card the {@code Card} to be added to the hand.
     */
    public void take(Card card) {
        hand.add(card);
    }

    /**
     * Returns the list of cards in the hand.
     *
     * @return an {@code ArrayList<Card>} containing the current hand.
     */
    public ArrayList<Card> getHand() {
        return hand;
    }

    /**
     * Calculates and returns the total Blackjack score of the hand.
     * <p>
     * Aces are treated as 11 unless doing so would cause the hand to bust,
     * in which case they are counted as 1.
     *
     * @return the Blackjack score of the hand.
     */
    public int getScore() {
        int score = 0;
        int numOfAces = 0;

        // Add values for all non-Ace cards, count Aces separately
        for (Card card : hand) {
            if (card.getValue() == 1) { // Assuming Ace is represented by 1
                numOfAces++;
            } else {
                score += card.getValue();
            }
        }

        // Add Aces optimally: count each as 11 if it doesn't cause bust, otherwise 1
        for (int i = 0; i < numOfAces; i++) {
            if (score + 11 <= 21) {
                score += 11;
            } else {
                score += 1;
            }
        }

        return score;
    }

    /**
     * Returns the number of cards currently in the hand.
     *
     * @return the number of cards in the hand.
     */
    public int size() {
        return hand.size();
    }

    /**
     * Removes all cards from the hand.
     */
    public void clear() {
        hand.clear();
    }
}
