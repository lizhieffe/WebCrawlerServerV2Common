package com.zl.daemons;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Reader;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.zl.utils.SimpleLogger;

@Component
public class CommandSender {

	private Map<String, Socket> sockets = new HashMap<String, Socket>();
	private Map<String, Boolean> isConnected = new HashMap<String, Boolean>();
	private Socket currentSocket;
	InputStream in = null;
	ObjectOutput out = null;
	private String serverIP;
	private int port;
	boolean bConnected = false;
	private static int RECONNECT_TIME = 15; 
	
	public CommandSender() {
		
	}
	
	public void connect(String ip, int port) {
		this.serverIP = ip;
		this.port = port;
		connect();
	}

	public void connect() {
		while (!bConnected) {
			try {
				// Establish connection with server
				SimpleLogger.info("Try to connect to " + serverIP + ":" + port);
				currentSocket = new Socket(serverIP, port);
				verify();
				bConnected = true;
				String key = serverIP + ":" + String.valueOf(port);
				sockets.put(key, currentSocket);
				isConnected.put(key, true);
				SimpleLogger.logServiceStartSucceed(this.getClass().getName());
			}
			catch(Exception ex) {
				SimpleLogger.error(ex.toString());
				SimpleLogger.logServiceStartFail(this.getClass().getName());
				SimpleLogger.info("Will try to connect to " + serverIP + ":" + port + " in " + RECONNECT_TIME + " seconds");
			}
			try {
				Thread.sleep(RECONNECT_TIME * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	// TODO: This verification method should be tuned
	private void verify() throws IOException {
		// Verify by send one method invocation
		out = new ObjectOutputStream(currentSocket.getOutputStream());
		out.writeObject(new te());
		out.flush();
		in = currentSocket.getInputStream();
		String response = inputStreamToString(in);
		SimpleLogger.info(response);
	}

	public void setCurrentConnection(String ip, int port) throws IOException {
		String key = ip + ":" + String.valueOf(port);
		if (sockets.containsKey(key) && isConnected.containsKey(key)) {
			currentSocket = sockets.get(key);
			bConnected = isConnected.get(key);
			verify();
		}
	}
	
	public void disconnect() {
		try {

			if (bConnected == false)
				return;
			// Send close signal to server
			try {
				out = new ObjectOutputStream(currentSocket.getOutputStream());
				/**
				 * TODO: change the signal object
				 */
				Serializable object = new Serializable() {
					private static final long serialVersionUID = 8298725944745155677L;
				};
				out.writeObject(object);
				out.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}

			out.close();
			in.close();
			bConnected = false;
			for (Iterator<Socket> iterator = sockets.values().iterator(); iterator.hasNext();) {
				Socket socket = (Socket) iterator.next();
				try {
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		sockets.clear();
		isConnected.clear();
	}

	private synchronized Object send(Serializable object) {
		SimpleLogger.info("Send object: " + object);

		if (bConnected == false)
			return null;

		// Send MethodCall object to device
		try {
			out = new ObjectOutputStream(currentSocket.getOutputStream());
			out.writeObject(object);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Get result from device
		try {
			in = currentSocket.getInputStream();
			String result = inputStreamToString(in);

			return result;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String inputStreamToString(final InputStream is) {
		int bufferSize = 1024;
		final char[] buffer = new char[bufferSize];
		final StringBuilder out = new StringBuilder();
		try {
			final Reader in = new InputStreamReader(is, "UTF-8");
			try {
				for (;;) {
					int rsz = in.read(buffer, 0, buffer.length);
					if (rsz < 0)
						break;
					out.append(buffer, 0, rsz);
				}
			}
			finally {
				in.close();
			}
		}
		catch (UnsupportedEncodingException ex) {
	    /* ... */
		}
		catch (IOException ex) {
	      /* ... */
		}
		return out.toString();
	}
}
