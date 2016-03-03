package be.jcalculus.socket;

import java.net.Socket;

public class JCServerThread extends Thread {

	private JCServer server;
	private Socket serverToClient;

	public JCServerThread(JCServer server, Socket serverToClient) {
		super();
		this.server = server;
		this.serverToClient = serverToClient;
	}

	@Override
	public void run() {
		System.out.println("\tSync with client(" + serverToClient + ")");
		String initFromClient = JCUtils.readAndWaitFrom(serverToClient);

		String[] split = initFromClient.split(":");
		String hostClient = split[0];
		String portClient = split[1];

		Socket clientToServer = null;
		do {
			try {
				System.out.println("\tWait for client (" + hostClient + ":" + portClient + ")");
				clientToServer = new Socket(hostClient, Integer.parseInt(portClient));
				System.out.println("\tLinked to client(" + clientToServer + ")");
			} catch (Exception e) {
				System.out.println("\tERROR: " + e.getMessage());
				JCUtils.sleep(250L);
			}
		} while (!JCUtils.isConnected(clientToServer));

		while (JCUtils.isConnected(clientToServer, serverToClient)) {
			String requestFromClient = JCUtils.readAndWaitFrom(clientToServer);
			System.out.println(String.format("<<< <<< \"%s\" from client %s", requestFromClient, clientToServer));

			String response = server.submit(requestFromClient);

			if ("".equals(response)) {
				response = new String(new byte[] { Character.CONTROL });
			}

			System.out.println(String.format(">>> >>> \"%s\" towards client %s", response, serverToClient));
			JCUtils.writeTo(serverToClient, response);
		}
	}

}
