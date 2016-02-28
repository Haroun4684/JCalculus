package be.jcalculus.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class JCUtils {

	public static String readSocket(Socket soc) {
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
			e.printStackTrace();
		}
		return null;
	}

	public static void write(Socket soc, String msg) {
		try {
			OutputStream out = soc.getOutputStream();
			out.write(msg.getBytes());
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
