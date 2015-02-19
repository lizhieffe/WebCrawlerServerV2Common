package com.zl.utils;

import java.util.ArrayList;
import java.util.List;

import com.zl.resources.RSlave;
import com.zl.resources.RSlaves;
import com.zl.server.nodes.SlaveNode;

public class ResourceUtil {
	public static RSlave convertToRSlave(SlaveNode node) {
		RSlave slave = new RSlave();
		slave.setIp(node.getIp());
		slave.setPort(node.getPort());
		return slave;
	}
	
	public static RSlaves converToRSlaves(List<SlaveNode> nodes) {
		List<RSlave> slaves = new ArrayList<RSlave>();
		for (SlaveNode node : nodes) {
			slaves.add(convertToRSlave(node));
		}
		RSlaves result = new RSlaves();
		result.setSlaves(slaves);
		return result;
	}
}
