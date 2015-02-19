package com.zl.interfaces;

import java.net.Socket;

public interface ISocketListenerDaemon {
	public void startListening();
	public void addSocket(Socket socket);
	public void removeSocket(Socket socket);
}
