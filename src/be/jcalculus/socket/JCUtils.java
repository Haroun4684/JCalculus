package be.jcalculus.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Iterator;

public class JCUtils {

	public static String readAndWaitFrom(Socket soc) {
		try {
			InputStream in = soc.getInputStream();
			int size = 0;
			do {
				size = in.available();
			} while (size == 0);
			byte[] bytes = new byte[size];
			in.read(bytes);
			return new String(bytes);
		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
		}
		return null;
	}

	public static void writeTo(Socket soc, String msg) {
		try {
			OutputStream out = soc.getOutputStream();
			out.write(msg.getBytes());
			out.flush();
		} catch (IOException e) {
			System.out.println("ERROR: " + e.getMessage());
		}
	}

	public static String getMyip() {
		try {
			return InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			System.out.println("ERROR: " + e.getMessage());
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
						System.out.println("ERROR: " + e.getMessage());
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
					System.out.println("ERROR: " + e.getMessage());
				}
			}
		}
	}

	public static void sleep(long ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			System.out.println("ERROR: " + e.getMessage());
		}
	}
}
