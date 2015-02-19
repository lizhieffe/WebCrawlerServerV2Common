package com.zl.resources;

import java.util.List;

public class RSlaves extends RResponse {
	private List<RSlave> slaves;

	public List<RSlave> getSlaves() {
		return slaves;
	}

	public void setSlaves(List<RSlave> slaves) {
		this.slaves = slaves;
	}
}
