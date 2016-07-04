package com.jd.MethodLimit;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import com.google.common.base.Ticker;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;
public class GuavaTest {
	
	
	/**
	 * @param args
	 * @throws InterruptedException
	 * @throws ExecutionException
	 * @throws UnknownHostException
	 */
	public static void main(String[] args) throws InterruptedException, ExecutionException, UnknownHostException {
		//rateLimiterTest();
 		LoadingCache<Long, AtomicLong> counter =
		        CacheBuilder.newBuilder()
		        .ticker(new Ticker() {
					@Override
					public long read() {
 						return 0;
					}
				})
		        .removalListener(new RemovalListener<Long, AtomicLong>() {
		        	@Override
		        	public void onRemoval(RemovalNotification<Long, AtomicLong> notification) {
		        		System.out.println("remove key : " + notification.getKey());
		        	}
				})
                //.expireAfterWrite(1, TimeUnit.SECONDS) //afterWriter 表示的意思是在写入当前Key，之后再接下来的1S，如果没有人Put，那么就会Remove当前的KEY
		        .expireAfterAccess(1, TimeUnit.SECONDS)
		        .build(new CacheLoader<Long, AtomicLong>() {
                    @Override
                    public AtomicLong load(Long seconds) throws Exception {
                        return new AtomicLong(0);
                    }
                });
	  
 		 counter.put(1l, new AtomicLong(23));
 		 while(true){
  	 		 TimeUnit.MILLISECONDS.sleep(900);
 			 if(new Random().nextInt(10) > 8){
 				 System.out.println("loop finish..");
 				 break ;
 			 }
 		 }
 		 TimeUnit.MILLISECONDS.sleep(1500);
 		 System.out.println(counter.get(2l));
 		//counter.put(2l,new AtomicLong(12));
 		 /*counter.get(1l).incrementAndGet();

 		 System.out.println(counter.get(2l));
 
 		 System.out.println(counter.get(1l));
 		 TimeUnit.MILLISECONDS.sleep(1500);
 		 System.out.println(counter.get(1l));*/

 
		/* while(true) {
		    //得到当前秒
		    long currentSeconds = System.currentTimeMillis() / 1000;
		    if(counter.get(currentSeconds).incrementAndGet() > limit) {
		        System.out.println("限流了:" + currentSeconds);
		        TimeUnit.SECONDS.sleep(1);
		        continue;
		    }
		    //业务处理
		    System.out.println("call Method....");
		}
		*/
	}

	private static void rateLimiterTest() throws InterruptedException, UnknownHostException {
		/*RateLimiter limiter = RateLimiter.create(1500);
 
   		System.out.println(limiter.acquire(100));
   	 
   		System.out.println(limiter.tryAcquire(4000, 3, TimeUnit.SECONDS));
    	
   		System.out.println(limiter.acquire());*/
		System.out.println( InetAddress.getLocalHost().getHostAddress());
   		while(true){
   			//System.out.println(System.currentTimeMillis() + " : " + new Date().getTime());
   		}
	}
}
