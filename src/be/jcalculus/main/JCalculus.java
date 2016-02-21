package be.jcalculus.main;

import javax.swing.JFrame;

import be.jcalculus.gui.JCalFrame;
import be.jcalculus.pojos.Game;
import be.jcalculus.pojos.Player;

public class JCalculus {

	public static void main2(String[] args) {
		JCalFrame frame = new JCalFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		Player player1=new Player("Haroun", "h");
		Game.getInstance().setPlayer1(player1);
		Player player2=new Player("Raph", "r");
		Game.getInstance().setPlayer2(player2);

		JCalFrame frame = new JCalFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.displayPlayers();
		frame.setVisible(true);
	}

}
