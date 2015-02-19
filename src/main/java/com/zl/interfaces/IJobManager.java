package com.zl.interfaces;

import com.zl.abstracts.AJob;

public interface IJobManager {
	public boolean addJob(AJob job);
	public boolean moveJobToWaitingStatus(AJob job);
	public boolean moveJobToRunningStatus(AJob job);
	public boolean removeJobFromRunningStatus(AJob job);

//	public abstract List<AJob> getWaitingJobs();
//	public abstract List<AJob> getRunningJobs();
//	public boolean hasJobWaiting();
//	public boolean hasJobRunning();
}
