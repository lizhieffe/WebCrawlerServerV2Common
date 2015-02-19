package com.zl.jobs;

import java.net.MalformedURLException;
import java.net.URL;

import com.zl.abstracts.AJob;

public class WebCrawlingJob extends AJob {
	
	private URL url;
	private int depth;
	
	public WebCrawlingJob(String url, int depth) {
		super();
		try {
			this.url = new URL(url);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		this.depth = depth;
	}
	
	public URL getUrl() {
		return this.url;
	}
	
	public int getDepth() {
		return this.depth;
	}
}
