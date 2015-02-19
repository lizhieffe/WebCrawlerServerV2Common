package com.zl.daemons;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import com.zl.interfaces.ISocketListenerDaemon;
import com.zl.utils.SimpleLogger;

public class ConnectionListenerDaemon implements Runnable {
	
	ISocketListenerDaemon socketListenerDaemon;
	Socket socket = null;
	ObjectOutput out;
	ObjectInputStream in;
	private boolean started;

	public ConnectionListenerDaemon(ISocketListenerDaemon socketListenerDaemon, Socket socket) {
		this.socketListenerDaemon = socketListenerDaemon;
		this.socketListenerDaemon.addSocket(socket);
		this.socket = socket;
		this.started = true;
	}

	@Override
	public void run() {
		try {
			while (started) {
				SimpleLogger.info("Handling connection...");
				Object object = receiveObject();
				if (object != null) 
					processObject(object);
			}
			close();
		} catch (Exception e) {
			SimpleLogger.info("ReceiveConnection.run: when receiving connection ");
			e.printStackTrace();
		}
	}
	
	public Object receiveObject() throws IOException {
		in = new ObjectInputStream(socket.getInputStream());
		Object method = null;
		try {
			method = in.readObject();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}	
		return method;
	}

	public void processObject(Object object) {
		if (true)
			sendString("true");
		else
			sendString("false");
	}

	public void sendString(String str) {
	    OutputStreamWriter osw;
	    try {
			osw =new OutputStreamWriter(socket.getOutputStream(), "UTF-8");
	        osw.write(str, 0, str.length());
	        osw.flush();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void close() throws IOException {
		socketListenerDaemon.removeSocket(socket);
		socket.shutdownInput();
		socket.shutdownOutput();
		socket.close();
	}
}
