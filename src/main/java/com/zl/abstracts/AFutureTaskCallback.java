package com.zl.abstracts;

import com.google.common.util.concurrent.FutureCallback;
import com.zl.utils.SimpleLogger;

public class AFutureTaskCallback<T> implements FutureCallback<T> {
	
	private int localId;
	
	public AFutureTaskCallback(int id) {
		this.localId = id;
	}
	
	@Override
	public void onSuccess(T result) {
		SimpleLogger.info("[" + localId + "]FutureCallback.onSuccess(T) is being called");
	}
	
	@Override
	public void onFailure(Throwable thrown) {
		SimpleLogger.info("[" + localId + "]FutureCallback.onFailure(Throwable) is being called");
	}
}
