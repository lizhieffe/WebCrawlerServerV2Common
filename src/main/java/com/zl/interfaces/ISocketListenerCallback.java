package com.zl.interfaces;

import java.net.Socket;

public interface ISocketListenerCallback {
	public void onReceiveObject(Socket socket, Object object);
}
