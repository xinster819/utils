package org.easycluster.easycluster.cluster.server;

public interface ThreadPoolMessageExecutorMBean {

	int getQueueSize();

	int getCorePoolSize();

	int getMaxPoolSize();

	int getLargestPoolSize();

	int getPoolSize();

	int getActiveCount();

	double getAverageWaitTimeInMillis();

	long getKeepAliveTime();

	double getAverageProcessingTimeInMillis();

	long getRequestCount();

	long getCompletedTaskCount();
}
