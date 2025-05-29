package blackjack;

public class Blackjack {
	
	final static int H = 700;
	final static int W = 1000;
	
	public static void main(String[] args) {
		
		Hand player = new Hand();
		Hand dealer = new Hand();
		
		Game game = new Game(player, dealer);
		
		Gui table = new Gui(game);

		
				
	}	
}
