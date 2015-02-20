package com.zl.daemons;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.zl.interfaces.IDaemon;
import com.zl.interfaces.IServerSocketListenerDaemon;
import com.zl.interfaces.ISocketListenerCallback;
import com.zl.interfaces.IThreadPoolDaemon;
import com.zl.sockets.SocketListener;
import com.zl.utils.SimpleLogger;

@Component
public class ServerSocketListenerDaemon implements IDaemon, IServerSocketListenerDaemon {

	private IThreadPoolDaemon threadPoolService;
	private boolean started;
	private ServerSocket serverSocket;
	private List<ISocketListenerCallback> callbacks;
	
	@Value("${server-socket-port}")
	private String port;
	
	private Map<InetAddress, Socket> sockets;
	
	public ServerSocketListenerDaemon() {
		sockets = new ConcurrentHashMap<InetAddress, Socket>();
		callbacks = new ArrayList<ISocketListenerCallback>();
	}
	
	@Override
	synchronized public void start(IThreadPoolDaemon threadPoolService) {
		this.threadPoolService = threadPoolService;
		Runnable task = new Runnable() {
			public void run() {
				start();
			}
		};
		this.threadPoolService.submit(task);
	}

	@Async
	@Override
	synchronized public void start() {
		if (started)
			return;
		else
			started = true;
		
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
				new Thread(new SocketListener(this, socket, callbacks)).start();
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
	
	@Override
	public void stop() {
		started = false;
	}

	@Override
	public boolean isStarted() {
		return started;
	}
	
	@Override
	public void addSocketListenerCallback(ISocketListenerCallback callback) {
		callbacks.add(callback);
	}
	
	public void addSocket(Socket socket) {
		this.sockets.put(socket.getInetAddress(), socket);
	}
	
	public void removeSocket(Socket socket) {
		this.sockets.remove(socket.getInetAddress());
	}
}
