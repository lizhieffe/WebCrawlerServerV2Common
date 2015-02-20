package com.zl.resources;

import com.zl.abstracts.AResource;

public class RSlave extends AResource {
	private String ip;
	private int port;
	private int socket_port;
	
	public String getIp() {
		return ip;
	}
	
	public void setIp(String ip) {
		this.ip = ip;
	}
	
	public int getPort() {
		return port;
	}
	
	public void setPort(int port) {
		this.port = port;
	}

	public int getSocket_port() {
		return socket_port;
	}

	public void setSocket_port(int socketPort) {
		this.socket_port = socketPort;
	}
}
