package com.jd.MethodLimit.bean;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 1.记录某个时间段的IP+MethodSign的记录
 * 2.需要算出平均时间(先加上所有的时间,然后在除法)
 * @author zhangrui25
 *
 */
public class MethodExecuteLogBean {

	 
	private String uri ;				//如果是Web段访问，那么找到他的访问地址
	private String requestType ;		//(WEB,JSF)
	private String ip ;					//访问的IP地址
	private String hostIp;				//本地IP地址
	private String methodSign ;			//方法签名
	private AtomicLong countExecuteFailed = new AtomicLong(0);	//某个时间段执行失败次数
	private AtomicLong countExecuteSucess = new AtomicLong(0); 	//某个时间段执行成功次数
	private AtomicLong countExeuceteLimit = new AtomicLong(0);	//某个时间段因为限流导致执行失败次数
	private AtomicLong countExecute = new AtomicLong(0);			//某个时间段总共执行了多少次
	private long avgTimeMethodExecute ;	//在某个时间段方法执行的时间(avg)
	private long maxTimeMethodExecute ;	//最长执行时间
	private long minTimeMethodExecute ;	//最短执行时间
	private long startTime  ;
	private long endTime ;
	
	
	 
	
	@Override
	public String toString() {
 		return "{ip:"+ip
 				+",hostIp:"+hostIp
 				+",methodSign:"+methodSign
 				+",countExecute:"+countExecute
 				+",minTimeMethodExecute:"+minTimeMethodExecute
 				+",maxTimeMethodExecute:"+maxTimeMethodExecute
 				+",totalTimeMethodExecute:"+(avgTimeMethodExecute / countExecute.get())
 				+",countExecuteSucess:"+countExecuteSucess
 				+",countExecuteFailed:"+countExecuteFailed
 			+"}";
	}
	
	public synchronized void appendMethodExecuteTime(long durationTime){
		if(durationTime > maxTimeMethodExecute){
			maxTimeMethodExecute = durationTime ;
		}
		if( durationTime < minTimeMethodExecute || minTimeMethodExecute == 0  ){
			minTimeMethodExecute = durationTime ;
		}
		avgTimeMethodExecute += durationTime ;
	}
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	
	public String getRequestType() {
		return requestType;
	}
	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}
	public String getIp() {
		return ip;
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
	 
	public long getAvgTimeMethodExecute() {
		return avgTimeMethodExecute;
	}
	public void setAvgTimeMethodExecute(long avgTimeMethodExecute) {
		this.avgTimeMethodExecute = avgTimeMethodExecute;
	}
	public long getMaxTimeMethodExecute() {
		return maxTimeMethodExecute;
	}
	public void setMaxTimeMethodExecute(int maxTimeMethodExecute) {
		this.maxTimeMethodExecute = maxTimeMethodExecute;
	}
	public long getMinTimeMethodExecute() {
		return minTimeMethodExecute;
	}
	public void setMinTimeMethodExecute(int minTimeMethodExecute) {
		this.minTimeMethodExecute = minTimeMethodExecute;
	}
	
	public AtomicLong getCountExecuteFailed() {
		return countExecuteFailed;
	}
	public AtomicLong getCountExecuteSucess() {
		return countExecuteSucess;
	}
	public AtomicLong getCountExeuceteLimit() {
		return countExeuceteLimit;
	}
	public AtomicLong getCountExecute() {
		return countExecute;
	}
	public synchronized  long addCountExecute(){
		return countExecute.incrementAndGet();
	}
	public long addcountExecuteFailed(){
		return countExecuteFailed.incrementAndGet();
	}
	public long addCountExecuteSucess(){
		return countExecuteSucess.incrementAndGet();
	}
	public long addCountExeuceteLimit(){
		return countExeuceteLimit.incrementAndGet();
	}

	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	public long getEndTime() {
		return endTime;
	}

	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}

	 
 }
