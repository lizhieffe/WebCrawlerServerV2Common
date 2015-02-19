package com.zl.resources;

import com.zl.abstracts.AJob;

public class RWebCrawlingJob extends AJob {
	private String url;
	private int depth;
	private String type;

	public String getUrl() {
		return url;
	}

	public int getDepth() {
		return depth;
	}

	public String getType() {
		return type;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	public void setType(String type) {
		this.type = type;
	}
}
