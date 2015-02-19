package com.zl.interfaces;

public interface IDaemon {
	public void start(IThreadPoolDaemon threadPoolService);
	public void stop();
	public boolean isStarted();
}
