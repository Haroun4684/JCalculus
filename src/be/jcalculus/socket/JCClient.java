package be.jcalculus.socket;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class JCClient {

	public static int portClient = 8081;

	private static Socket socketClient;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Socket socketServer = null;
		do {
			System.out.print("Enter IP of server [localhost] please ? ");
			String host = scanner.nextLine();
			if ("".equals(host)) {
				host = "localhost";
			}
			System.out.println("Server defined at " + host);
			try {
				socketServer = new Socket(host, JCServer.portClient);
			} catch (UnknownHostException e) {
				System.err.println(e.getMessage());
			} catch (IOException e) {
				System.err.println(e.getMessage());
			}
		} while (socketServer == null);

		System.out.println("Server Socket accepted : " + socketServer);

		String ip = "";
		try {
			ip = InetAddress.getLocalHost().getHostAddress();
			System.out.println("My ip is " + ip);
		} catch (UnknownHostException e2) {
			e2.printStackTrace();
		}

		JCUtils.write(socketServer, ip);

		ServerSocket serverSocketClient;
		try {
			serverSocketClient = new ServerSocket(portClient);
			System.out.println("Waiting for socket client com");
			socketClient = serverSocketClient.accept();
			System.out.println("Socket client com accepted");
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		String msg = "";
		while (socketServer.isConnected()) {
			System.out.print("Please enter a message for the server > ");
			msg = scanner.nextLine();
			if (!"".equals(msg)) {
				System.out.println("- sending : " + msg + " towards server (" + socketServer + ") -");
				JCUtils.write(socketServer, msg);
				String resp = JCUtils.readSocket(socketClient);
				System.out.println("Server response : " + resp);
			}

		}
		System.out.println("- disconnecting -");
		try {
			socketServer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		scanner.close();
	}

}
