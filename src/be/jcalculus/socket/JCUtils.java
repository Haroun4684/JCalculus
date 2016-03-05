package be.jcalculus.socket;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Iterator;

public class JCUtils {

	public static String getMyip() {
		try {
			return InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			error(e);
		}
		return "";
	}

	public static boolean isConnected(Socket... sockets) {
		boolean ret = true;
		Iterator<Socket> it = Arrays.asList(sockets).iterator();
		while (it.hasNext() && ret) {
			ret &= it.next().isConnected();
		}
		return ret && sockets.length > 0;
	}

	public static void close(Socket... sockets) {
		Iterator<Socket> it = Arrays.asList(sockets).iterator();
		while (it.hasNext()) {
			Socket socket = it.next();
			if (socket != null) {
				if (!socket.isClosed()) {
					try {
						socket.close();
					} catch (IOException e) {
						error(e);
					}
				}
			}
		}
	}

	public static void close(ServerSocket serverSocket) {
		if (serverSocket != null) {
			if (!serverSocket.isClosed()) {
				try {
					serverSocket.close();
				} catch (IOException e) {
					error(e);
				}
			}
		}
	}

	public static void sleep(long ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			error(e);
		}
	}

	public static void error(Exception e) {
		System.err.println(String.format("ERROR: %s%n", e.getMessage()));
	}
}
