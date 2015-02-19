package com.zl.jobs;

import com.zl.resources.RWebCrawlingJob;

public class WebCrawlingJobFactory {
	
	public static WebCrawlingJob create(String url, String depth) {
		if (!JobHelper.isValidUrl(url) || !JobHelper.isValidDepth(depth))
			return null;
		WebCrawlingJob job = new WebCrawlingJob(url, JobHelper.getDepthInteger(depth));
		return job;
	}
	
	public static WebCrawlingJob create(String url, int depth) {
		return create(url, String.valueOf(depth));
	}
	
	public static WebCrawlingJob create(RWebCrawlingJob job) {
		return create(job.getUrl(), job.getDepth());
	}
}
