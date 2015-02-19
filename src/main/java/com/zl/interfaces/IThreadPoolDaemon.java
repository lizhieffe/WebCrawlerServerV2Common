package com.zl.interfaces;

import java.util.concurrent.ExecutorService;

public interface IThreadPoolDaemon {
	public void start();
	public void stop();
	public void submit(Runnable task);
	public ExecutorService getExecutorService();
}
