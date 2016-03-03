package be.jcalculus.gui;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

import be.jcalculus.core.CalculusProposal;
import be.jcalculus.pojos.Game;
import java.awt.Font;
import java.awt.Color;
import javax.swing.ImageIcon;

public class JCalFrame extends JFrame {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JTextField calcul;
	private JTextField score1;
	private JTextField score2;

	private JButton btnStart;

	private JLabel lblPlayer1;

	private JLabel lblPlayer2;
	private JLabel lblScore;
	private JLabel lblScore_1;
	private JLabel lblWhosTheFaster;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		// TODO remove me
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JCalFrame frame = new JCalFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public JCalFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 0, 0, 0, 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 0.0, 1.0, 1.0, 1.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		lblWhosTheFaster = new JLabel("WHO'S THE FASTER ?");
		lblWhosTheFaster.setFont(new Font("Segoe UI Black", Font.BOLD, 15));
		GridBagConstraints gbc_lblWhosTheFaster = new GridBagConstraints();
		gbc_lblWhosTheFaster.gridwidth = 3;
		gbc_lblWhosTheFaster.insets = new Insets(0, 0, 5, 5);
		gbc_lblWhosTheFaster.gridx = 1;
		gbc_lblWhosTheFaster.gridy = 0;
		contentPane.add(lblWhosTheFaster, gbc_lblWhosTheFaster);

		calcul = new JTextField();
		calcul.setEditable(false);
		GridBagConstraints gbc_calcul = new GridBagConstraints();
		gbc_calcul.fill = GridBagConstraints.HORIZONTAL;
		gbc_calcul.gridwidth = 4;
		gbc_calcul.insets = new Insets(0, 0, 5, 0);
		gbc_calcul.gridx = 0;
		gbc_calcul.gridy = 1;
		contentPane.add(calcul, gbc_calcul);
		calcul.setColumns(10);

		lblScore = new JLabel("Score ");
		lblScore.setFont(new Font("Berlin Sans FB", Font.PLAIN, 15));
		GridBagConstraints gbc_lblScore = new GridBagConstraints();
		gbc_lblScore.insets = new Insets(0, 0, 5, 5);
		gbc_lblScore.gridx = 1;
		gbc_lblScore.gridy = 2;
		contentPane.add(lblScore, gbc_lblScore);

		JLabel lblJoueurs = new JLabel("Players");
		lblJoueurs.setToolTipText("");
		lblJoueurs.setIcon(new ImageIcon("C:\\Users\\harou\\OneDrive\\Pictures\\icons\\players32.png"));
		lblJoueurs.setBackground(Color.GRAY);
		lblJoueurs.setFont(new Font("Agency FB", Font.BOLD, 15));
		GridBagConstraints gbc_lblJoueurs = new GridBagConstraints();
		gbc_lblJoueurs.insets = new Insets(0, 0, 5, 5);
		gbc_lblJoueurs.gridx = 2;
		gbc_lblJoueurs.gridy = 2;
		contentPane.add(lblJoueurs, gbc_lblJoueurs);

		lblScore_1 = new JLabel("Score");
		lblScore_1.setFont(new Font("Berlin Sans FB", Font.PLAIN, 15));
		GridBagConstraints gbc_lblScore_1 = new GridBagConstraints();
		gbc_lblScore_1.insets = new Insets(0, 0, 5, 0);
		gbc_lblScore_1.gridx = 3;
		gbc_lblScore_1.gridy = 2;
		contentPane.add(lblScore_1, gbc_lblScore_1);

		lblPlayer1 = new JLabel("");
		GridBagConstraints gbc_lblPlayer1 = new GridBagConstraints();
		gbc_lblPlayer1.insets = new Insets(0, 0, 5, 5);
		gbc_lblPlayer1.gridx = 0;
		gbc_lblPlayer1.gridy = 3;
		contentPane.add(lblPlayer1, gbc_lblPlayer1);

		score1 = new JTextField();
		score1.setEditable(false);
		GridBagConstraints gbc_score1 = new GridBagConstraints();
		gbc_score1.insets = new Insets(0, 0, 5, 5);
		gbc_score1.fill = GridBagConstraints.HORIZONTAL;
		gbc_score1.gridx = 1;
		gbc_score1.gridy = 3;
		contentPane.add(score1, gbc_score1);
		score1.setColumns(10);

		lblPlayer2 = new JLabel("");
		GridBagConstraints gbc_lblPlayer2 = new GridBagConstraints();
		gbc_lblPlayer2.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblPlayer2.insets = new Insets(0, 0, 5, 5);
		gbc_lblPlayer2.gridx = 2;
		gbc_lblPlayer2.gridy = 3;
		contentPane.add(lblPlayer2, gbc_lblPlayer2);

		score2 = new JTextField();
		score2.setEditable(false);
		GridBagConstraints gbc_score2 = new GridBagConstraints();
		gbc_score2.insets = new Insets(0, 0, 5, 0);
		gbc_score2.fill = GridBagConstraints.HORIZONTAL;
		gbc_score2.gridx = 3;
		gbc_score2.gridy = 3;
		contentPane.add(score2, gbc_score2);
		score2.setColumns(10);

		btnStart = new JButton("START");
		GridBagConstraints gbc_btnStart = new GridBagConstraints();
		gbc_btnStart.gridwidth = 2;
		gbc_btnStart.insets = new Insets(0, 0, 0, 5);
		gbc_btnStart.gridx = 0;
		gbc_btnStart.gridy = 7;
		contentPane.add(btnStart, gbc_btnStart);

		JButton btnNext = new JButton("NEXT");
		GridBagConstraints gbc_btnNext = new GridBagConstraints();
		gbc_btnNext.insets = new Insets(0, 0, 0, 5);
		gbc_btnNext.gridx = 2;
		gbc_btnNext.gridy = 7;
		contentPane.add(btnNext, gbc_btnNext);

		JButton btnQuit = new JButton("QUIT");
		GridBagConstraints gbc_btnQuit = new GridBagConstraints();
		gbc_btnQuit.gridx = 3;
		gbc_btnQuit.gridy = 7;
		contentPane.add(btnQuit, gbc_btnQuit);

		init();
	}

	private class MyDispatcher implements KeyEventDispatcher {
		public boolean dispatchKeyEvent(KeyEvent e) {
			if (e.getID() == KeyEvent.KEY_PRESSED) {
				String key = "" + e.getKeyChar();
				System.out.println("Key pressed : " + key);
				if (Game.getInstance().getCurrent() == null) {
					Game.getInstance().setPlayer(key);
				} else {
					System.out.println("Already player taken !");
				}
			}
			return false;
		}
	}

	private void init() {

		KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
		manager.addKeyEventDispatcher(new MyDispatcher());

		Game.getInstance().setParent(this);

		this.btnStart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				// Game.getInstance().start();
				Game.getInstance().startTest();
			}

		});
	}

	public void display(CalculusProposal cp) {
		this.calcul.setText(cp.getCalcul());
	}

	public void displayPlayers() {
		this.lblPlayer1.setText(Game.getInstance().getPlayer1().getName());
		this.lblPlayer2.setText(Game.getInstance().getPlayer2().getName());
		this.score1.setText("" + Game.getInstance().getPlayer1().getScore());
		this.score2.setText("" + Game.getInstance().getPlayer2().getScore());
	}

	JCalFrame getInstance() {
		return this;
	}

}
