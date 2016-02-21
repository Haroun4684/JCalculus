package be.jcalculus.pojos;

public class Game {

	private Player player1;
	private Player player2;

	private static Game instance = null;

	private Game() {
	}

	public static synchronized Game getInstance() {
		if (instance == null) {
			instance = new Game();
		}
		return instance;
	}

	public static void main(String[] args) {

	}

	public Player getPlayer1() {
		return player1;
	}

	public void setPlayer1(Player player1) {
		this.player1 = player1;
	}

	public Player getPlayer2() {
		return player2;
	}

	public void setPlayer2(Player player2) {
		this.player2 = player2;
	}

	@Override
	public String toString() {
		return "Game [player1=" + player1 + ", player2=" + player2 + "]";
	}

}
