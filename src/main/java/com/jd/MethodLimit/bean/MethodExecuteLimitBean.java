package com.jd.MethodLimit.bean;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 1.读取MethodLimit注解(里面设置)
 * @author zhangrui25
 *
 */
public class MethodExecuteLimitBean {

	private AtomicLong qps = new AtomicLong(0) ; 
 	private long startSeconds  ;
 	private long endSeconds  ;
	private long warnLimit ;
	private long unavailableLimit ;
	private String ip ;
	private String hostIp ;
	private String methodSign ;
	
	
	public MethodExecuteLimitBean(long warnLimit, long unavailableLimit , long startSeconds) {
		super();
		this.warnLimit = warnLimit;
		this.unavailableLimit = unavailableLimit;
		this.startSeconds = startSeconds ;
		this.endSeconds = startSeconds + 1 ;
	}

	
	public void addQps(){
		 qps.incrementAndGet() ;
	}

	@Override
	public String toString() {
		return "{startSeconds:"+startSeconds+","
				+ "endSeconds:"+endSeconds+","
				+ "warnLimit:"+warnLimit+","
				+ "unavailableLimit:"+unavailableLimit+","
				+ "qps:"+qps+"}";
	}
	
	public long getStartSeconds() {
		return startSeconds;
	}

	public long getEndSeconds() {
		return endSeconds;
	}

	public long getWarnLimit() {
		return warnLimit;
	}

	public long getUnavailableLimit() {
		return unavailableLimit;
	}


	public String getIp() {
		return ip;
	}


	public AtomicLong getQps() {
		return qps;
	}


	public void setIp(String ip) {
		this.ip = ip;
	}

	

	public String getHostIp() {
		return hostIp;
	}


	public void setHostIp(String hostIp) {
		this.hostIp = hostIp;
	}


	public String getMethodSign() {
		return methodSign;
	}


	public void setMethodSign(String methodSign) {
		this.methodSign = methodSign;
	}
	
}
