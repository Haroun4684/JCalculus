package be.jcalculus.pojos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.Timer;

import be.jcalculus.core.CalculusProposal;
import be.jcalculus.gui.JCalFrame;
import be.jcalculus.socket.JCClient;
import be.jcalculus.socket.JCServer;
import be.jcalculus.socket.Submittable;

public class Game implements Submittable {

	private JCServer server;
	private JCClient client;

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

		String server = JOptionPane.showInputDialog("Are you the server y-[n]?");

		if ("y".equals(server)) {
			this.server = new JCServer();
			this.player1 = new Player("Haroun", "h");
			this.player2 = new Player("Raph", "r");
			this.server.setSubmittable(this);
			this.server.start();
		} else {
			String host = JOptionPane.showInputDialog("Connect to server host?");
			String portStr = JOptionPane.showInputDialog("Connect to server port?");
			int port = Integer.parseInt(portStr);
			this.client = new JCClient();
			this.client.setHost(host);
			this.client.setPort(port);
			this.client.start();
			this.player1 = new Player(this.client.request("pname1"), this.client.request("pkey1"));
			this.player2 = new Player(this.client.request("pname2"), this.client.request("pkey2"));
		}

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
		} else {
			setCurrent(null);
		}

		if (getCurrent() != null) {
			String response = JOptionPane.showInputDialog(String.format("Your response %s ?", getCurrent().getName()));

			if (currentCalcul.isResponseCorrect(response)) {
				JOptionPane.showConfirmDialog(parent, "Nice!");
				getCurrent().setScore(getCurrent().getScore() + 1);
			} else {
				JOptionPane.showConfirmDialog(parent, "Maybe the next time :(");
				getCurrent().setScore(getCurrent().getScore() - 1);
			}
			parent.displayPlayers();
			current = null;
			currentCalcul = new CalculusProposal();
			parent.display(currentCalcul);
		}
	}

	public Player getCurrent() {
		return current;
	}

	public void setCurrent(Player current) {
		this.current = current;
	}

	public String submit(String request) {
		String ret = "I have not understood your request \"" + request + "\" !!!";
		switch (request) {
		case "pname1":
			ret = this.player1.getName();
			break;
		case "pname2":
			ret = this.player2.getName();
			break;
		case "pkey1":
			ret = this.player1.getEventKey();
			break;
		case "pkey2":
			ret = this.player2.getEventKey();
			break;
		default:
			break;
		}
		return ret;
	}
}
