package com.zl.sockets;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;

import com.zl.utils.SimpleLogger;
import com.zl.utils.SocketUtil;

public class SocketSender {

	private Socket socket;
	InputStream in = null;
	ObjectOutput out = null;
	private String serverIP;
	private int port;
	boolean connected = false;
	private static int RECONNECT_TIME = 15; 
	
	public SocketSender(String ip, int port) {
		this.serverIP = ip;
		this.port = port;
	}

	synchronized public void connect() {
		while (!connected) {
			try {
				// Establish connection with server
				SimpleLogger.info("Try to connect to " + serverIP + ":" + port);
				socket = new Socket(serverIP, port);
				verifyConnection();
				SimpleLogger.logServiceStartSucceed(this.getClass().getName());
				connected = true;
			}
			catch(Exception ex) {
				SimpleLogger.error(ex.toString());
				SimpleLogger.logServiceStartFail(this.getClass().getName());
				SimpleLogger.info("Will try to connect to " + serverIP + ":" + port + " in " + RECONNECT_TIME + " seconds");
			}
			if (!connected) {
				try {
					Thread.sleep(RECONNECT_TIME * 1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	synchronized public void send(Serializable object) {
		if (!connected)
			connect();
		SimpleLogger.info("[Socket/Sending]" + object);
		try {
			SocketUtil.sendObject(socket, object);
			SimpleLogger.info("[Socket/Sent]" + object.toString());
		}
		catch (IOException ex) {
			connected = false;
		}
	}
	
	// TODO: This verification method should be tuned
	private boolean verifyConnection() throws IOException {
		SocketUtil.sendObject(socket, new Verification());
		Object response = SocketUtil.receiveObject(socket);
		SimpleLogger.info(response.toString());
		if (response == null || !(response instanceof String) || !((String)response).equalsIgnoreCase("true"))
			return false;
		else
			return true;
	}
	
	public void disconnect() {
		try {

			if (connected == false)
				return;
			// Send close signal to server
			try {
				out = new ObjectOutputStream(socket.getOutputStream());
				/**
				 * TODO: change the signal object
				 */
				out.writeObject(new Verification());
				out.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}

			if (socket != null) {
				socket.shutdownInput();
				socket.shutdownOutput();
				socket.close();
			}
			
			connected = false;

		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}
