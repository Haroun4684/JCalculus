package be.jcalculus.pojos;

import javax.swing.JOptionPane;

import be.jcalculus.gui.JCalFrame;

public class Game {

	private JCalFrame parent;

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

	public void setParent(JCalFrame parent) {
		this.parent = parent;
	}

	public void getPlayers() {
		boolean error = false;
		do {
			String name = JOptionPane.showInputDialog("Enter Player 1's name ?");
			String key = JOptionPane.showInputDialog("Enter Player 1's key ?");
			try {
				Player player = new Player(name, key);
				Game.getInstance().setPlayer1(player);
			} catch (Exception e) {
				error = true;
				JOptionPane.showMessageDialog(this.parent, e.getMessage(), "ERROR !!!", JOptionPane.ERROR_MESSAGE);
				System.err.println("Error : " + e.getMessage());
			}
		} while (error);

		error = false;
		do {
			String name = JOptionPane.showInputDialog("Enter Player 2's name ?");
			String key = JOptionPane.showInputDialog("Enter Player 2's key ?");
			try {
				Player player = new Player(name, key);
				Game.getInstance().setPlayer2(player);
			} catch (Exception e) {
				error = true;
				JOptionPane.showMessageDialog(this.parent, e.getMessage(), "ERROR !!!", JOptionPane.ERROR_MESSAGE);
				System.err.println("Error : " + e.getMessage());
			}
		} while (error);
	}

}
