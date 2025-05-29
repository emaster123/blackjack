package blackjack;

public class Game {
	
	private int stake, winnings, balance;
	private Deck deck;
	private Hand player, dealer;
	private String result; // corresponds to win/bj/bust/push
	
	public Game(Hand player, Hand dealer) {
		stake = 0;
		winnings = 0;
		balance = 600;
		this.player = player;
		this.dealer = dealer;
		this.deck = new Deck();
	}
	
	public int putIn(int stake) { // 0 is success, 1 is fail
		if (stake > balance) {
			return 0;
		} else {
			this.stake += stake;
			this.balance -= stake;
			System.out.println("Put in $" + stake);
			return 1;
		}
	}
	
	public void win() {
		winnings = stake*2;
		stake = 0;
		balance += winnings;
	}
	

	public int compare() { // 0 is player's win, 1 is dealer's win, 2 is no one's win
		
		int playerScore = player.getScore(0);
		int dealerScore = dealer.getScore(0);
		
		if (playerScore == dealerScore) {
			// push
			return 2;
		} else if (playerScore == 21) {
			// bj
			return 0;
		} else if (dealerScore == 21) {
			// bj
			return 1;
		} else if (playerScore > 21) {
			// busts
			return 1;
		} else if (dealerScore > 21) {
			// busts
			return 1;
		} else if (playerScore > dealerScore) {
			// player has better cards
			return 0;
		} else {
			// dealer has better cards
			return 1;
		}
		
	}
	
	public int getStake() {
		return stake;
	}
	
	public int getWinnings() {
		return winnings;
	}
	
	public int getBalance() {
		return balance;
	}
	
}
