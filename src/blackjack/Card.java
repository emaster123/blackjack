package blackjack;

/**
 * Represents a single playing card in a standard 52-card deck.
 * Each card has a {@code Suit}, a numerical value (used for scoring)
 * or a face, and a state indicating whether the card is flipped.
 *
 * Aces are represented by value 1, face cards (Jack, Queen, King) are worth 10 points
 * but have a distinct face value for display purposes.
 * @author Eli L
 */
public class Card {

    /**
     * Enumeration representing the four suits of a standard deck.
     */
    public static enum Suit {
        HEARTS, DIAMONDS, SPADES, CLUBS
    };

    /** The suit of this card (Hearts, Diamonds, Spades, Clubs). */
    private Suit suit;

    /**
     * The point value of this card for scoring.
     * Aces are 1, cards 2–10 are their face value,
     * and face cards (Jack, Queen, King) are 10.
     */
    private int value;

    /**
     * The face type: 1 = Jack, 2 = Queen, 3 = King, 0 = non-face card.
     * This is used for display/rendering purposes.
     */
    private int face;

    /** Indicates whether the card is face-down (true) or face-up (false). */
    private boolean flipped;

    /**
     * Constructs a new {@code Card} with the given suit and value.
     * If the value is greater than 10, it is treated as a face card and set to value 10,
     * with the appropriate face identifier.
     *
     * @param suit  the {@code Suit} of the card.
     * @param value the numeric or face value of the card (1 = Ace, 11 = Jack, 12 = Queen, 13 = King).
     */
    public Card(Suit suit, int value) {
        this.suit = suit;

        if (value <= 10) {
            this.value = value;
            this.face = 0;
        } else {
            this.value = 10;
            this.face = value - 10;
        }
    }

    /**
     * Returns a human-readable string representing the card,
     * including its face or numeric value and its suit.
     *
     * @return a {@code String} representation such as "king of hearts".
     */
    @Override
    public String toString() {
        String result = "";

        switch (face) {
            case 1:
                result += "jack";
                break;
            case 2:
                result += "queen";
                break;
            case 3:
                result += "king";
                break;
            default:
                result += value;
                break;
        }

        return result + " of " + suit.toString().toLowerCase();
    }

    /**
     * Returns the suit of this card.
     *
     * @return the {@code Suit} of the card.
     */
    public Suit getSuit() {
        return suit;
    }

    /**
     * Sets the suit of this card.
     *
     * @param suit the {@code Suit} to set.
     */
    public void setSuit(Suit suit) {
        this.suit = suit;
    }

    /**
     * Returns the point value of the card (1–10).
     *
     * @return the value of the card.
     */
    public int getValue() {
        return value;
    }

    /**
     * Sets the value of the card. This should normally be 1–10.
     *
     * @param value the value to set.
     */
    public void setValue(int value) {
        this.value = value;
    }

    /**
     * Returns the face type of the card.
     * 0 = not a face card, 1 = Jack, 2 = Queen, 3 = King.
     *
     * @return the face identifier.
     */
    public int getFace() {
        return face;
    }

    /**
     * Sets the face identifier of the card.
     *
     * @param face the face value to set (0–3).
     */
    public void setFace(int face) {
        this.face = face;
    }

    /**
     * Flips the card to show its back.
     */
    public void showBack() {
        flipped = true;
    }

    /**
     * Flips the card to show its front.
     */
    public void showFront() {
        flipped = false;
    }

    /**
     * Returns whether the card is currently face-down (true) or face-up (false).
     *
     * @return {@code true} if flipped, otherwise {@code false}.
     */
    public boolean isFlipped() {
        return flipped;
    }

    /*
     * Future extension:
     * The makeImage method could generate a graphical representation of the card
     * dynamically using suit, value, and face—similar to how dice images are rendered in craps.
     */
}
