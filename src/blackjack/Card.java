package blackjack;

public class Card {

	static enum Suit {
		HEARTS, DIAMONDS, SPADES, CLUBS
	};
	
	private Suit suit;
	private int value; // 1 is an ace, 2-10 is the number, 11 is j, 12 is q, 13 is k
	private int face; // 1 is j, 2 is q, 3 is k, 0 is nothing
	private boolean flipped; // is the card showing or just its back?
	
	public Card(Suit suit, int value) {
		this.suit = suit;
		
		// have to translate card value to face+real value
		
		if (value <= 10) {
			this.value = value;
			this.face = 0;
		} else {
			this.value = 10;
			this.face = value-10;
		}
	}
	
	@Override
	public String toString() {
		
		String result = "";
		
		switch(face){
		case 1:
			result += "jack";break;
		case 2:
			result += "queen";break;
		case 3:
			result += "king";break;
		default:
			result += value;break;
		}
		
		return result+" of "+suit.toString().toLowerCase();
		
	}
	
	public Suit getSuit() {
		return suit;
	}
	
	public void setSuit(Suit suit) {
		this.suit = suit;
	}
	
	public int getValue() {
		return value;
	}
	
	public void setValue(int value) {
		this.value = value;
	}
	
	public int getFace() {
		return face;
	}
	
	public void setFace(int face) {
		this.face = face;
	}
	
	public void showBack() {
		flipped = true;
	}
	
	public void showFront() {
		flipped = false;
	}
	
	public boolean isFlipped() {
		return flipped;
	}
	
	/*
	 * makeImage method:
	 * Generate an image through checking face, value, and suit to avoid having 52 files
	 * copy the craps dice draw method
	 */
	
	
}
