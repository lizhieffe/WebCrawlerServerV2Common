package com.zl.interfaces;

import com.zl.abstracts.AFutureTaskCallback;

public interface IFutureTask <T> {
//	public void start();
	public void startWithCallback(AFutureTaskCallback <T> task);
}
