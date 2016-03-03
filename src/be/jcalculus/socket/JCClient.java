package be.jcalculus.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class JCClient extends Thread {

	public int portClient = 8080;

	private Socket clientToServer;
	private Socket serverToClient;

	public static void main(String[] args) {
		JCClient client = new JCClient();
		client.start();
	}

	@Override
	public void run() {
		Scanner scanner = new Scanner(System.in);

		do {
			System.out.print("Enter IP:port of the server [localhost:8080] please ? ");

			String input = scanner.nextLine();
			if ("".equals(input)) {
				input = "localhost:8080";
			}

			try {

				String[] split = input.split(":");
				String host = split[0];
				String port = split[1];

				System.out.println("Try to connect to server " + host + ":" + port);
				serverToClient = new Socket(host, Integer.parseInt(port));
				System.out.println("Linked to the Server(" + serverToClient + ") for receiving");
			} catch (Exception e) {
				System.err.println("ERROR: " + e.getMessage());
			}
		} while (serverToClient == null);

		ServerSocket serverClient = null;

		int countTry = 0;
		do {
			try {
				System.out.println("Expose this client (port " + portClient + ") to the server");
				serverClient = new ServerSocket(portClient);
				System.out.println("Sync this client (port " + portClient + ") with the server");
			} catch (IOException e) {
				System.out.println("ERROR: " + e.getMessage());
				portClient++;
				System.out.println("trying next port : " + portClient);
				countTry++;
			}
		} while (serverClient == null && countTry <= 10);

		if (serverClient != null) {
			String ip = JCUtils.getMyip();
			System.out.println("\tSend to the server this client infos (" + ip + ":" + portClient + ")");
			JCUtils.writeTo(serverToClient, ip + ":" + portClient);
			try {
				clientToServer = serverClient.accept();
				System.out.println("Linked to the server(" + clientToServer + ") for sending");
			} catch (IOException e) {
				System.out.println("ERROR: " + e.getMessage());
			} finally {
				JCUtils.close(serverClient);
			}

			while (JCUtils.isConnected(clientToServer, serverToClient)) {
				System.out.print("Please enter a message for the server > ");
				String msg = scanner.nextLine();
				if (!"".equals(msg)) {
					System.out.println(String.format(">>> \"%s\" towards server %s", msg, clientToServer));
					JCUtils.writeTo(clientToServer, msg);

					String resp = JCUtils.readAndWaitFrom(serverToClient);
					System.out.println(String.format("<<< \"%s\" from server %s", resp, serverClient));
				}
			}
		}

		System.out.println(">>> this client disconnecting <<<");
		JCUtils.close(clientToServer, serverToClient);
		scanner.close();
	}

	public String sendToServer(String request) {
		if (JCUtils.isConnected(clientToServer, serverToClient)) {
			if (!"".equals(request)) {
				System.out.println("- sending : " + request + " towards server (" + serverToClient + ") -");
				JCUtils.writeTo(serverToClient, request);
				String resp = JCUtils.readAndWaitFrom(clientToServer);
				System.out.println("Server response : " + resp);
				return resp;
			}
		}
		return "";
	}
}
