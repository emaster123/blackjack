		package blackjack;

import java.util.ArrayList;

public class Game {
	
	private int stake, winnings, balance;
	private Hand player, dealer;
	private Deck deck;
	private boolean betting = true;
	
	private FileManager fileManager = new FileManager();
	
	public Game(Hand player, Hand dealer, Deck deck) {
		stake = 0;
		winnings = 0;
		balance = 600;
		this.player = player;
		this.dealer = dealer;
		this.deck = deck;
		
	}
	
	public boolean putIn(int stake) { // true is success, false is fail
		if (stake > balance) {
			return false;
		} else {
			this.stake += stake;
			this.balance -= stake;
			System.out.println("Put in $" + stake);
			return true;
		}
	}
	
	public void win(double multiplier) { // bj is 2.5, normal is 2, push is 1
		winnings = (int) (stake*multiplier);
		stake = 0;
		balance += winnings;
		fileManager.checkAndUpdateProfit(getWinnings());
		winnings = 0;
	}
	
	public void lose() {
		stake = 0;
		fileManager.checkAndUpdateProfit(getWinnings());
		winnings = 0;
		
		if (balance == 0) {
			reset();
		}
		
	}
	

	/*
	 * First: 1 is dealer, 2 is player, 3 is neither
	 * Second: 1 is Blackjack!, 2 is bust (and other hand's win), 3 is win by higher numer
	 */
	
	public int compare() { 
		// 0 is player's win, 1 is dealer's win, 2 is no one's win, 3 is player busts
		
		int playerScore = player.getScore();
		int dealerScore = dealer.getScore();
		 
		System.out.println("Player has a " + playerScore + "\nDealer has a " + dealerScore);
		
		if (playerScore == dealerScore) {
			// push
			return 30;
		} else if (playerScore == 21) {
			// bj
			return 21;
		} else if (dealerScore == 21) {
			// bj
			return 11;
		} else if (playerScore > 21) {
			// busts
			return 22;
		} else if (dealerScore > 21) {
			// busts
			return 12;
		} else if (playerScore > dealerScore) {
			// player has better cards
			return 23;
		} else {
			// dealer has better cards
			return 13;
		}
		
	}
	
	public Card hit() {
		System.out.println("Hitting (game.java)");
		Card c = deck.deal();
		player.take(c);
		return c;
	};
	
	public ArrayList<Card> stand() {
	
		Card c;
		ArrayList<Card> cardsToDraw = new ArrayList<>();
		
		while (dealer.getScore() < 17) {
			
			c = deck.deal();
			System.out.println("The dealer has a score less than 17, he draws a " + c);
			dealer.take(c);
			cardsToDraw.add(c);
			
		}
		
		System.out.println("In total, " + cardsToDraw.size() + " cards should be drawn");
		return cardsToDraw;
		
	}
	public int getStake() {
		return stake;
	}
	
	public void setStake(int stake) {
		this.stake = stake;
	}
	
	public int getWinnings() {
		return winnings;
	}
	
	public int getBalance() {
		return balance;
	}
	
	public int getScore() {
		return player.getScore();
	}
	
	public void reset() {
		balance = 600;
	}
	
	public boolean isBetting() {
		return betting;
	}
	
	public void setBetting(boolean betting) {
		this.betting = betting;
	}
	
	public FileManager getFileManager() {
		return fileManager;
	}

	public int readHighestProfit() {
		return fileManager.readHighestProfit();
	}
	
	
	
}
