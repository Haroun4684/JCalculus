package be.jcalculus.socket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class JCServerThread extends Thread {

	private JCServer server;
	private Socket socket;

	public JCServerThread(JCServer server, Socket socket) {
		super();
		this.server = server;
		this.socket = socket;
	}

	@Override
	public void run() {
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())));

			while (JCUtils.isConnected(socket)) {
				String requestFromClient = in.readLine();

				if (requestFromClient == null) {
					System.out.println(String.format("- the client %s is disconnected -", socket));
					break;
				}

				System.out.println(String.format("<<< \"%s\" from client %s ", requestFromClient, socket));

				String response = server.getSubmittable().submit(requestFromClient);

				System.out.println(String.format(">>> \"%s\" towards client %s", response, socket));

				out.println(response);
				out.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		server.removeThread(this);
	}

}
