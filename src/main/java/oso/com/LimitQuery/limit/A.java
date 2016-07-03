package oso.com.LimitQuery.limit;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import oso.com.LimitQuery.exception.LimitMethodUnavailableException;

/**
 * 漏桶算法实现
 * 
 * @author win7zr
 */
class LimitBuck {

	private double maxCapacity; // 允许最大容量
	private double warnCapacity; // 设置的报警容量
	private double rate;
	private long lastAddTokenTimeStamp =  0l ;
	private long initTimeSeconds = 0l;
	private double tokens;
	private int addTokenTimes = 0 ;
	
	public static LimitBuck initLimitBuck(int maxCapacity, int warnCapacity){
		return new LimitBuck(maxCapacity,warnCapacity);
	}
	private LimitBuck(int maxCapacity, int warnCapacity) {
		super();
		this.maxCapacity = maxCapacity;
		this.warnCapacity = warnCapacity;
		this.rate = maxCapacity / 1000.0;
 		this.initTimeSeconds = System.currentTimeMillis() / 1000;
 		this.addTokenTimes = 0 ;
		this.lastAddTokenTimeStamp = System.currentTimeMillis() ;
	}
	private void clearn(long currentTime){
		this.tokens = 0.0d ;
 		this.initTimeSeconds = currentTime / 1000;
		this.addTokenTimes = 0 ;
		this.lastAddTokenTimeStamp = currentTime ;
	}
	private void createToken(){
		long currentTime = System.currentTimeMillis();
		if(tokens > maxCapacity || currentTime == lastAddTokenTimeStamp){
			return ;
		}else{
			if(initTimeSeconds != (currentTime / 1000)){
				//说明已经不是之前的那一秒钟]
 				 clearn(currentTime);
			}
			addTokenTimes++ ;
			double addTokens = 0.0d ;
			addTokens =  (currentTime- lastAddTokenTimeStamp) * rate ;
 			System.out.println("第"+addTokenTimes+" 次添加Tokens ("+addTokens+")");
			tokens += addTokens ;
			lastAddTokenTimeStamp = currentTime;
		}
	}
	synchronized boolean getToken() {
		 createToken();
		 if(tokens <= 0){
			 return false ;
		 }
		 --tokens;
		 System.out.println("可以执行当前的方法: tokens 还有: " + tokens);
		 return true ;
	}
}

class TokenClient implements Runnable{
	LimitBuck limitBuck  ;

	public TokenClient(LimitBuck limitBuck) {
		super();
		this.limitBuck = limitBuck;
	}
	
	@Override
	public void run() {
		try {
			 TimeUnit.SECONDS.sleep(new Random().nextInt(30));
		} catch (InterruptedException e) {
 			e.printStackTrace();
		}
		if(!limitBuck.getToken()){
			System.out.println("没有足够的Token ， 无法自行方法");
		}
	}
	
	
}
public class A {

	
	public static void main(String[] args) throws InterruptedException {
		LimitBuck limitBuck = LimitBuck.initLimitBuck(800, 300);
		for(int index = 0 ; index < 1000 ;  index++ ){
			new Thread(new TokenClient(limitBuck)).start();
		}
	}
}
