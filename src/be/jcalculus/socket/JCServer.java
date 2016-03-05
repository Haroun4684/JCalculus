package be.jcalculus.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class JCServer extends Thread implements Submittable {

	public int portServer = 8080;

	private List<JCServerThread> clients = new ArrayList<JCServerThread>();

	private Submittable submittable;

	public static void main(String[] args) {
		JCServer server = new JCServer();
		server.setSubmittable(server);
		server.start();
	}

	@Override
	public void run() {
		ServerSocket server = null;
		String host = null;

		int countTry = 0;
		do {
			try {
				host = JCUtils.getMyip();
				System.out.println("Starting server(" + host + ":" + portServer + ")...");
				server = new ServerSocket(portServer);
				System.out.println("Server started on " + host + ":" + portServer);
			} catch (IOException e) {
				JCUtils.error(e);
				portServer++;
				System.out.println("trying next port : " + portServer);
				countTry++;
			}
		} while (server == null && countTry <= 1000);

		if (server != null) {
			try {
				while (server.isBound()) {
					System.out.println(String.format("- server %s:%d is waiting for new client -", host, portServer));
					Socket socket = server.accept();
					System.out.println(String.format("- client %s accepted -", socket));

					JCServerThread threadClient = new JCServerThread(this, socket);
					clients.add(threadClient);
					threadClient.start();

					System.out.println(String.format("- total of clients : %d -", clients.size()));
				}
			} catch (Exception e) {
				JCUtils.error(e);
			} finally {
				for (JCServerThread t : clients) {
					clients.remove(t);
				}
				JCUtils.close(server);
			}
		}
	}

	public void removeThread(JCServerThread t) {
		this.clients.remove(t);
		System.out.println("- total of clients : " + clients.size() + " -");
	}

	public Submittable getSubmittable() {
		return submittable;
	}

	public void setSubmittable(Submittable submittable) {
		this.submittable = submittable;
	}

}
