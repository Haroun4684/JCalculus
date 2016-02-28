package be.jcalculus.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import be.jcalculus.pojos.Game;

public class JCServer extends Thread {

	public static int portClient = 8080;

	private ServerSocket server;

	private List<JCServerThread> clients = new ArrayList<JCServerThread>();

	private Game game;

	@Override
	public void run() {
		try {
			server = new ServerSocket(portClient);
			// TODO Auto-generated catch block
			while (true) {
				System.out.println("- server IP " + JCUtils.getMyip() + " is waiting for new client -");
				Socket client = server.accept();

				JCServerThread threadClient = new JCServerThread(client, this);
				clients.add(threadClient);
				threadClient.start();

				System.out.println("- socket " + client + " accepted -");
				System.out.println("total of sockets : " + clients.size());
			}
		} catch (IOException e) {
		}
	}

	public String submit(String requestFromClient) {
		String serverReturned = "";
		switch (requestFromClient) {
		case "getplayer1name":
			serverReturned = this.game.getPlayer1().getName();
			break;
		case "getplayer1key":
			serverReturned = this.game.getPlayer1().getEventKey();
			break;
		case "getplayer2name":
			serverReturned = this.game.getPlayer2().getName();
			break;
		case "getplayer2key":
			serverReturned = this.game.getPlayer2().getEventKey();
			break;

		default:
			break;
		}
		return serverReturned;
	}

	public void setGame(Game game) {
		this.game = game;
	}

}
