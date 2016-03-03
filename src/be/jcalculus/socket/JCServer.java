package be.jcalculus.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import be.jcalculus.pojos.Game;

public class JCServer extends Thread {

	public int portServer = 8080;

	private List<JCServerThread> clients = new ArrayList<JCServerThread>();

	private Game game;

	public static void main(String[] args) {
		JCServer server = new JCServer();
		server.start();
	}

	@Override
	public void run() {
		String host = null;
		ServerSocket server = null;
		int countTry = 0;
		do {
			try {
				host = JCUtils.getMyip();
				System.out.println("Starting server(" + host + ":" + portServer + ")...");
				server = new ServerSocket(portServer);
				System.out.println("Server started on " + host + ":" + portServer);
			} catch (IOException e) {
				System.out.println("ERREUR: " + e.getMessage());
				portServer++;
				System.out.println("trying next port : " + portServer);
				countTry++;
			}
		} while (server == null && countTry <= 1000);

		if (server != null) {
			try {
				while (true) {
					System.out.println("- server " + host + ":" + portServer + " is waiting for new client -");
					Socket serverToClient = server.accept();
					System.out.println("- client " + serverToClient + " accepted -");

					JCServerThread threadClient = new JCServerThread(this, serverToClient);
					clients.add(threadClient);
					threadClient.start();

					System.out.println("- total of clients : " + clients.size() + " -");
				}
			} catch (Exception e) {
				System.out.println("ERREUR: " + e.getMessage());
			} finally {
				JCUtils.close(server);
			}
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
			serverReturned = requestFromClient.toUpperCase();
			break;
		}
		return serverReturned;
	}

	public void setGame(Game game) {
		this.game = game;
	}

}
