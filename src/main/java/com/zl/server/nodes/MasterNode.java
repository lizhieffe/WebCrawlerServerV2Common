package com.zl.server.nodes;

import com.zl.interfaces.IServerNode;
import com.zl.utils.ConfigUtil;

public class MasterNode implements IServerNode {

	@Override
	public void setIp(String ip) {
		throw new RuntimeException(
				"Please change the master node ip in configuration file");
	}

	@Override
	public void setPort(int port) {
		throw new RuntimeException(
				"Please change the master node port in configuration file");
	}

	@Override
	public String getIp() {
		return ConfigUtil.getMasterIp();
	}

	@Override
	public int getPort() {
		return ConfigUtil.getMasterPort();
	}

	@Override
	public String getDomain() {
		return ServerNodeHelper.constructHost(getIp(), getPort());
	}
}
