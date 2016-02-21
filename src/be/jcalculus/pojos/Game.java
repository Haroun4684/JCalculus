package be.jcalculus.pojos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.Timer;

import be.jcalculus.core.CalculusProposal;
import be.jcalculus.gui.JCalFrame;

public class Game {

	private JCalFrame parent;

	private Player current;

	private Player player1;
	private Player player2;

	private static Game instance = null;

	private CalculusProposal currentCalcul;

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

	public void start() {
		System.out.println("START");
		Game.getInstance().getPlayers();
		System.out.println(Game.getInstance());
		this.parent.displayPlayers();
		CalculusProposal cp = new CalculusProposal();
		this.parent.display(cp);
	}

	public void startTest() {
		System.out.println("START");

		Player player1 = new Player("Haroun", "h");
		Game.getInstance().setPlayer1(player1);
		Player player2 = new Player("Raph", "r");
		Game.getInstance().setPlayer2(player2);

		System.out.println(Game.getInstance());
		this.parent.displayPlayers();
		currentCalcul = new CalculusProposal();
		this.parent.display(currentCalcul);

		Timer timer = new Timer(5000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (getCurrent() == null) {
					currentCalcul = new CalculusProposal();
					parent.display(currentCalcul);
				}
			}
		});

		timer.start();
	}

	public void getPlayers() {
		boolean error = false;
		do {
			error = false;
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
			error = false;
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

	public void setPlayer(String key) {
		if (getPlayer1().getEventKey().equals(key)) {
			setCurrent(getPlayer1());
		} else if (getPlayer2().getEventKey().equals(key)) {
			setCurrent(getPlayer2());
		}

		String response = JOptionPane.showInputDialog(String.format("Your response %s ?", getCurrent().getName()));

		if (currentCalcul.isResponseCorrect(response)) {
			JOptionPane.showConfirmDialog(parent, "Correct :)");
			getCurrent().setScore(getCurrent().getScore() + 1);
		} else {
			JOptionPane.showConfirmDialog(parent, "Bad :(");
			getCurrent().setScore(getCurrent().getScore() - 1);
		}
		parent.displayPlayers();
		current = null;
		currentCalcul = new CalculusProposal();
		parent.display(currentCalcul);
	}

	public Player getCurrent() {
		return current;
	}

	public void setCurrent(Player current) {
		this.current = current;
	}

}
