package com.zl.interfaces;

public interface IServiceCallback <T> {
	public void onSuccess(T response);
	public void onFailure(T response);
}
