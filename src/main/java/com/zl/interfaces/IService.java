package com.zl.interfaces;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

public interface IService {
	public void start();
	public HttpMethod getHttpMethod();
	public String getUri();
	public String constructRequestUrl();
	public HttpEntity<String> constructRequestHttpEntity();
	public boolean isSuccess(ResponseEntity<String> response);
	public void onSuccess(ResponseEntity<String> response);
	public void onFailure(ResponseEntity<String> response);
}
