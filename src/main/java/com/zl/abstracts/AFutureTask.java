package com.zl.abstracts;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;

import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import com.zl.interfaces.IFutureTask;
import com.zl.utils.AppProperties;
import com.zl.utils.SimpleLogger;

abstract public class AFutureTask <T> implements IFutureTask <T> {
	
	private static int DEFAULT_MAX_THREADS = 50;
	private static int count = 0;
	protected int id = ++count;
			
	protected Callable <T> callable;
	protected static ListeningExecutorService service;
	
	static {
		int maxThreads;
		try {
			maxThreads = Integer.parseInt(AppProperties.getInstance().get("task.max-threads"));
		}
		catch (NumberFormatException ex) {
			maxThreads = DEFAULT_MAX_THREADS;
		}
		
		service = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(maxThreads));
	}
	
	public AFutureTask() {
	}
	
//	@Override
//	public void start() {
//		Future<T> future = es.submit(callable);
//		try {
//			future.get();
//		} catch (InterruptedException | ExecutionException e) {
//		}
//	}
	
	@Override
	public void startWithCallback(AFutureTaskCallback <T> callback) {
		SimpleLogger.info("[" + id + "]Task Starts");
		ListenableFuture<T> future = service.submit(this.callable);
		Futures.addCallback(future, callback);
	}
}
