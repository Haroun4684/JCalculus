package be.jcalculus.socket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class JCClient extends JCAbsClient {

	private Socket socket;

	public static void main(String[] args) {
		JCClient client = new JCClient();
		client.start();
	}

	@Override
	public void run() {
		try {
			System.out.println(String.format("Try to connect to server %s:%s", getHost(), getPort()));
			socket = new Socket(getHost(), getPort());
			System.out.println(String.format("Linked to the Server (%s) for receiving", socket));

			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())));

			while (JCUtils.isConnected(socket)) {

				while (getRequest() == null || "".equals(getRequest())) {
					JCUtils.sleep(100);
				}

				out.println(getRequest());
				setRequest("");
				out.flush();

				String resp = in.readLine();

				if (resp == null) {
					System.out.println("- the server is disconnected !!! -");
					break;
				} else {
					System.out.println(String.format("%s", resp));
					setResponse(resp);
				}
			}
		} catch (IOException e) {
			JCUtils.error(e);
		}
		System.out.println(">>> disconnecting <<<");
		JCUtils.close(socket);
	}

	// @Override
	// public void run() {
	// Scanner scanner = new Scanner(System.in);
	//
	// do {
	// System.out.print("Enter IP:port of the server [[localhost]:8080] please ?
	// ");
	//
	// String input = scanner.nextLine();
	//
	// if ("".equals(input)) {
	// input = "localhost:8080";
	// } else if (input != null && input.startsWith(":")) {
	// input = "localhost" + input;
	// } else if (input != null && input.matches("\\d+")) {
	// input = "localhost:" + input;
	// }
	//
	// if (input != null && input.matches("[^:]+:\\d+")) {
	// try {
	// String[] split = input.split(":");
	// String host = split[0];
	// String port = split[1];
	//
	// System.out.println(String.format("Try to connect to server %s:%s", host,
	// port));
	// socket = new Socket(host, Integer.parseInt(port));
	// System.out.println(String.format("Linked to the Server (%s) for
	// receiving", socket));
	// } catch (Exception e) {
	// JCUtils.error(e);
	// }
	// } else {
	// JCUtils.error(new Exception("bad host and port, please enter
	// host:port"));
	// }
	// } while (socket == null);
	//
	// try {
	// BufferedReader in = new BufferedReader(new
	// InputStreamReader(socket.getInputStream()));
	// PrintWriter out = new PrintWriter(new BufferedWriter(new
	// OutputStreamWriter(socket.getOutputStream())));
	//
	// while (JCUtils.isConnected(socket)) {
	// System.out.println("");
	// System.out.print("Please enter a message for the server > ");
	// String msg = scanner.nextLine();
	// if (!"".equals(msg)) {
	// out.println(msg);
	// out.flush();
	//
	// String resp = in.readLine();
	//
	// if (resp == null) {
	// System.out.println("- the server is disconnected !!! -");
	// break;
	// } else {
	// System.out.println(String.format("%s", resp));
	// }
	//
	// }
	// }
	// } catch (IOException e) {
	// JCUtils.error(e);
	// }
	// System.out.println(">>> disconnecting <<<");
	// JCUtils.close(socket);
	// scanner.close();
	//
	// }

}
