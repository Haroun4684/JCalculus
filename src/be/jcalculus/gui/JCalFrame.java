package be.jcalculus.gui;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import be.jcalculus.core.CalculusProposal;
import be.jcalculus.pojos.Game;
import be.jcalculus.pojos.Player;

public class JCalFrame extends JFrame {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JTextField calcul;
	private JTextField response;
	private JTextField score1;
	private JTextField score2;

	private JButton btnStart;

	private JLabel lblPlayer1;

	private JLabel lblPlayer2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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
		gbl_contentPane.rowHeights = new int[] { 0, 0, 0, 0, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 0.0, 1.0, 1.0, 1.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		calcul = new JTextField();
		GridBagConstraints gbc_calcul = new GridBagConstraints();
		gbc_calcul.fill = GridBagConstraints.HORIZONTAL;
		gbc_calcul.gridwidth = 4;
		gbc_calcul.insets = new Insets(0, 0, 5, 0);
		gbc_calcul.gridx = 0;
		gbc_calcul.gridy = 0;
		contentPane.add(calcul, gbc_calcul);
		calcul.setColumns(10);

		response = new JTextField();
		GridBagConstraints gbc_response = new GridBagConstraints();
		gbc_response.fill = GridBagConstraints.HORIZONTAL;
		gbc_response.gridwidth = 4;
		gbc_response.insets = new Insets(0, 0, 5, 0);
		gbc_response.gridx = 0;
		gbc_response.gridy = 1;
		contentPane.add(response, gbc_response);
		response.setColumns(10);

		JLabel lblJoueurs = new JLabel("Joueurs");
		GridBagConstraints gbc_lblJoueurs = new GridBagConstraints();
		gbc_lblJoueurs.gridwidth = 4;
		gbc_lblJoueurs.insets = new Insets(0, 0, 5, 0);
		gbc_lblJoueurs.gridx = 0;
		gbc_lblJoueurs.gridy = 2;
		contentPane.add(lblJoueurs, gbc_lblJoueurs);

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
		gbc_btnStart.insets = new Insets(0, 0, 0, 5);
		gbc_btnStart.gridx = 0;
		gbc_btnStart.gridy = 4;
		contentPane.add(btnStart, gbc_btnStart);

		JButton btnNext = new JButton("NEXT");
		GridBagConstraints gbc_btnNext = new GridBagConstraints();
		gbc_btnNext.insets = new Insets(0, 0, 0, 5);
		gbc_btnNext.gridx = 1;
		gbc_btnNext.gridy = 4;
		contentPane.add(btnNext, gbc_btnNext);

		JButton btnQuit = new JButton("QUIT");
		GridBagConstraints gbc_btnQuit = new GridBagConstraints();
		gbc_btnQuit.gridx = 3;
		gbc_btnQuit.gridy = 4;
		contentPane.add(btnQuit, gbc_btnQuit);

		init();
	}

	private void init() {

		Game.getInstance().setParent(this);

		this.btnStart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				System.out.println("START");
				Game.getInstance().getPlayers();
				System.out.println(Game.getInstance());
				displayPlayers();
				CalculusProposal cp=new CalculusProposal();
			}

		});
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
