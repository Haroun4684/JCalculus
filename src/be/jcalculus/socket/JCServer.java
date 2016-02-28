package be.jcalculus.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class JCServer {

	public static int portClient = 8080;

	private ServerSocket server;

	private List<JCServerThread> clients = new ArrayList<JCServerThread>();

	public static void main(String[] args) {
		JCServer server = new JCServer();
		try {
			server.start();
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}

	public void start() throws IOException {
		server = new ServerSocket(portClient);
		while (true) {
			System.out.println("- server is waiting for new client -");
			Socket client = server.accept();

			JCServerThread threadClient = new JCServerThread(client, this);
			clients.add(threadClient);
			threadClient.start();

			System.out.println("- socket " + client + " accepted -");
			System.out.println("total of sockets : " + clients.size());
		}
	}

	public String submit(String requestFromClient) {
		return requestFromClient.toUpperCase();
	}

}
