package org.easycluster.easycluster.cluster;

import org.easycluster.easycluster.cluster.common.SystemUtil;
import org.easycluster.easycluster.cluster.netty.codec.ProtocolCodecConfig;
import org.easycluster.easycluster.cluster.netty.endpoint.EndpointListener;

public class NetworkServerConfig {

	private String				applicationName					= null;

	private String				serviceName						= null;

	private String				zooKeeperConnectString			= null;

	/**
	 * The ZooKeeper session timeout in milliseconds.
	 */
	private int					zooKeeperSessionTimeoutMillis	= ClusterDefaults.ZOOKEEPER_SESSION_TIMEOUT_MILLIS;

	/**
	 * The number of milliseconds to wait when opening a socket.
	 */
	private int					idleTime						= NetworkDefaults.ALLIDLE_TIMEOUT_MILLIS;

	/**
	 * The number of core request threads.
	 */
	private int					requestThreadCorePoolSize		= NetworkDefaults.REQUEST_THREAD_CORE_POOL_SIZE;

	/**
	 * The max number of core request threads.
	 */
	private int					requestThreadMaxPoolSize		= NetworkDefaults.REQUEST_THREAD_MAX_POOL_SIZE;

	/**
	 * The request thread timeout in seconds.
	 */
	private int					requestThreadKeepAliveTimeSecs	= NetworkDefaults.REQUEST_THREAD_KEEP_ALIVE_TIME_SECS;

	private ProtocolCodecConfig	protocolCodecConfig				= null;

	private EndpointListener	endpointListener				= null;

	private Integer[]			partitions						= new Integer[0];

	private String				version							= null;

	private String				ip								= SystemUtil.getIpAddress();

	private int					port							= -1;

	private String				url								= null;

	public String getApplicationName() {
		return applicationName;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getZooKeeperConnectString() {
		return zooKeeperConnectString;
	}

	public void setZooKeeperConnectString(String zooKeeperConnectString) {
		this.zooKeeperConnectString = zooKeeperConnectString;
	}

	public int getZooKeeperSessionTimeoutMillis() {
		return zooKeeperSessionTimeoutMillis;
	}

	public void setZooKeeperSessionTimeoutMillis(int zooKeeperSessionTimeoutMillis) {
		this.zooKeeperSessionTimeoutMillis = zooKeeperSessionTimeoutMillis;
	}

	public int getRequestThreadCorePoolSize() {
		return requestThreadCorePoolSize;
	}

	public void setRequestThreadCorePoolSize(int requestThreadCorePoolSize) {
		this.requestThreadCorePoolSize = requestThreadCorePoolSize;
	}

	public int getRequestThreadMaxPoolSize() {
		return requestThreadMaxPoolSize;
	}

	public void setRequestThreadMaxPoolSize(int requestThreadMaxPoolSize) {
		this.requestThreadMaxPoolSize = requestThreadMaxPoolSize;
	}

	public int getRequestThreadKeepAliveTimeSecs() {
		return requestThreadKeepAliveTimeSecs;
	}

	public void setRequestThreadKeepAliveTimeSecs(int requestThreadKeepAliveTimeSecs) {
		this.requestThreadKeepAliveTimeSecs = requestThreadKeepAliveTimeSecs;
	}

	public EndpointListener getEndpointListener() {
		return endpointListener;
	}

	public void setEndpointListener(EndpointListener endpointListener) {
		this.endpointListener = endpointListener;
	}

	public int getIdleTime() {
		return idleTime;
	}

	public void setIdleTime(int idleTime) {
		this.idleTime = idleTime;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public Integer[] getPartitions() {
		return partitions;
	}

	public void setPartitions(Integer[] partitions) {
		this.partitions = partitions;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public ProtocolCodecConfig getProtocolCodecConfig() {
		return protocolCodecConfig;
	}

	public void setProtocolCodecConfig(ProtocolCodecConfig protocolCodecConfig) {
		this.protocolCodecConfig = protocolCodecConfig;
	}

}
