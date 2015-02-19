package com.zl.interfaces;

public interface IFutureTaskCallback <T> {
	public void onSuccess(T result);
	public void onFailure(Throwable thrown);
}
