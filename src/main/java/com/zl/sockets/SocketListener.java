package com.zl.sockets;

import java.io.IOException;
import java.net.Socket;
import java.util.List;

import com.zl.daemons.ServerSocketListenerDaemon;
import com.zl.interfaces.ISocketListenerCallback;
import com.zl.utils.SimpleLogger;
import com.zl.utils.SocketUtil;

public class SocketListener implements Runnable {
	
	ServerSocketListenerDaemon socketListenerDaemon;
	Socket socket = null;
	private boolean started;
	private final List<ISocketListenerCallback> callbacks;
	
	public SocketListener(ServerSocketListenerDaemon socketListenerDaemon, Socket socket
			, List<ISocketListenerCallback> callbacks) {
		this.socketListenerDaemon = socketListenerDaemon;
		this.socketListenerDaemon.addSocket(socket);
		this.socket = socket;
		this.callbacks = callbacks;
		this.started = true;
	}

	@Override
	public void run() {
		try {
			while (started) {
				SimpleLogger.info("Handling connection...");
				Object obj = SocketUtil.receiveObject(socket);
				if (obj != null && callbacks != null)
					for (ISocketListenerCallback callback : callbacks)
						callback.onReceiveObject(obj);
			}
			close();
		} catch (Exception e) {
			SimpleLogger.info("ReceiveConnection.run: when receiving connection ");
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
