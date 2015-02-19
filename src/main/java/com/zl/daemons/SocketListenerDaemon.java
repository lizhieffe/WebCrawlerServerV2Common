package com.zl.daemons;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.zl.interfaces.IDaemon;
import com.zl.interfaces.ISocketListenerDaemon;
import com.zl.interfaces.IThreadPoolDaemon;
import com.zl.utils.SimpleLogger;

@Component
public class SocketListenerDaemon implements IDaemon, ISocketListenerDaemon {

	private IThreadPoolDaemon threadPoolService;
	private boolean started;
	private ServerSocket serverSocket;
	
	@Value("${server-object-port}")
	private String port;
	
	private Map<InetAddress, Socket> sockets = new ConcurrentHashMap<InetAddress, Socket>();
	
	@Override
	public void start(IThreadPoolDaemon threadPoolService) {
		this.threadPoolService = threadPoolService;
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isStarted() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void addSocket(Socket socket) {
		this.sockets.put(socket.getInetAddress(), socket);
	}
	
	@Override
	public void removeSocket(Socket socket) {
		this.sockets.remove(socket.getInetAddress());
	}
	
	@Async
	@Override
	public void startListening() {
		try {
			serverSocket = new ServerSocket(Integer.parseInt(port));
			started = true;
			SimpleLogger.logServiceStartSucceed(this);
		} catch (IOException e) {			
			e.printStackTrace();
			this.port = "0";
//			return 0;
		}

		while (started) {
			try {
				Socket socket = serverSocket.accept();
				new Thread(new ConnectionListenerDaemon(this, socket)).start();
			} catch (IOException e) {
				e.printStackTrace();				
//				return 2;
			}
		}

		try {
			serverSocket.close();
		} catch (IOException e) {	
			SimpleLogger.error("Exception when closing server socket");
//			return 3;
		}

//		return 1;
	}
}
