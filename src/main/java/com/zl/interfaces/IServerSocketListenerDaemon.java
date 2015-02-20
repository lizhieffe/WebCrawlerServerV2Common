package com.zl.interfaces;



public interface IServerSocketListenerDaemon {
	public void start();
	public void addSocketListenerCallback(ISocketListenerCallback callback);
}
