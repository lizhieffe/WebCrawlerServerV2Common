package com.zl.abstracts;

import java.util.Random;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.zl.factories.RestTemplateFactory;
import com.zl.interfaces.IService;
import com.zl.utils.ResponseUtil;
import com.zl.utils.SimpleLogger;

abstract public class AService implements IService {
	
	protected RestTemplate restTemplate;
	private static Random rand = new Random(47);

	public AService() {
		this.restTemplate = RestTemplateFactory.getRestTemplateWithTimeout();
	}
	
	@Override
	public void start() {
		try {
			Thread.sleep(rand.nextInt(500));
		}
		catch (InterruptedException ex) {
			SimpleLogger.info(ex.toString());
		}
		
		String url = constructRequestUrl();
		HttpEntity<String> entity = constructRequestHttpEntity();
		
		String infoHead = (getUri() == null || getUri().equals("")) ? url : getUri();
		String requestInfoBody = entity.getBody().toString();
		if (requestInfoBody != null && requestInfoBody.length() > 100)
			requestInfoBody = "......";
		SimpleLogger.info("[" + infoHead + "] Request: [" + url + "] [" + requestInfoBody + "]");
		
		ResponseEntity<String> response = restTemplate.exchange(url, getHttpMethod(), entity, String.class);		
		boolean isSuccess = isSuccess(response);
		if (isSuccess)
			onSuccess(response);
		else
			onFailure(response);		
		
		String responseInfoBody = response.getBody().toString();
		if (responseInfoBody != null && responseInfoBody.length() > 100)
			responseInfoBody = "......";
		SimpleLogger.info("[" + infoHead + "] Response: [" + isSuccess + "] [" + url + "] [" + responseInfoBody + "]");
	}
	
	/**
	 * how to deal with timeout????
	 */
	@Override
	public boolean isSuccess(ResponseEntity<String> response) {
		return response.getStatusCode() == HttpStatus.OK && ResponseUtil.isSuccess(response);
	}
}
