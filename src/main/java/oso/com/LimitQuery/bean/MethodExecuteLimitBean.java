package oso.com.LimitQuery.bean;

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
	
}
