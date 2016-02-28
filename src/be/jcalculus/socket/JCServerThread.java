package be.jcalculus.socket;

import java.net.Socket;

public class JCServerThread extends Thread {

	private Socket socketServer;
	private JCServer server;

	public JCServerThread(Socket socket, JCServer server) {
		super();
		this.socketServer = socket;
		this.server = server;
	}

	@Override
	public void run() {
		try {
			String hostClient = JCUtils.readSocket(socketServer);

			Socket socketClient = null;
			do {
				System.out.println("Client Socket sync :S");
				try {
					socketClient = new Socket(hostClient, JCClient.portClient);
				} catch (Exception e) {
					System.err.println("Warning : Wait Sync socket client");
					Thread.sleep(250L);
				}
			} while (socketClient == null || !socketClient.isConnected());

			System.out.println("Client Socket intercepted :)");

			while (true) {
				String requestFromClient = JCUtils.readSocket(socketServer);
				System.out.println("I (the server) just received from client (" + socketServer + ") : \""
						+ requestFromClient + "\"");
				String response = server.submit(requestFromClient);
				System.out.println("I (the server) response to the client : \"" + response + "\"");
				JCUtils.write(socketClient, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
