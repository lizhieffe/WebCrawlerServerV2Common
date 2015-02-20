package com.zl.server.nodes;

public class SlaveNode {
	private String ip;
	private int port;
	private int socketPort;
	
	public void setIp(String ip) {
		this.ip = ip;
	}
	
	public void setPort(int port) {
		this.port = port;
	}
	
	public String getIp() {
		return this.ip;
	}
	
	public int getPort() {
		return this.port;
	}
	
	public String getDomain() {
		return ServerNodeHelper.constructHost(ip, port);
	}

	public int getSocketPort() {
		return socketPort;
	}

	public void setSocketPort(int socketPort) {
		this.socketPort = socketPort;
	}
}
