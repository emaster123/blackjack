package blackjack;

import java.util.ArrayList;
import java.util.Random;
import blackjack.Card.Suit;

/**
 * Represents a standard deck of playing cards used in a Blackjack game.
 * 
 * The deck consists of 52 cards, and includes methods
 * for shuffling, dealing, and retrieving the current state of the deck.
 * 
 * @author Eli L
 */
public class Deck {

    /** The internal list representing the current state of the deck. */
    private ArrayList<Card> deck;

    /**
     * Constructs a new shuffled deck of 52 cards and removes the top card (burn card).
     */
    public Deck() {
        deck = fillDeck();
        shuffle();
    }

    /**
     * Generates a new list of 52 cards (13 cards per suit).
     *
     * @return a new {@code ArrayList<Card>} containing a full standard deck.
     */
    private ArrayList<Card> fillDeck() {
        ArrayList<Card> result = new ArrayList<Card>();

        for (Suit suit : Suit.values()) {
            for (int i = 1; i < 13; i++) {
                result.add(new Card(suit, i));
            }
        }

        return result;
    }

    /**
     * Shuffles the deck using the Fisher-Yates algorithm.
     * Also removes the top card from the deck (burn card) after shuffling.
     * <p>
     * This simulates a standard casino-style card burn after a shuffle.
     */
    public void shuffle() {
        Random r = new Random();
        for (int i = deck.size() - 1; i > 0; i--) {
            int index = r.nextInt(i + 1);
            Card n = deck.get(index);
            deck.set(index, deck.get(i));
            deck.set(i, n);
        }

        deck.remove(0); // burn the first card
    }

    /**
     * Deals (removes and returns) the top card from the deck.
     *
     * @return the top {@code Card} from the deck.
     */
    public Card deal() {
        Card result = deck.get(0);
        deck.remove(0);
        return result;
    }

    /**
     * Returns the current state of the deck (mostly for debugging or inspection).
     *
     * @return the {@code ArrayList<Card>} representing the remaining cards in the deck.
     */
    public ArrayList<Card> getDeck() {
        return deck;
    }
}
