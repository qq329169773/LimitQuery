package oso.com.LimitQuery.buck;

import java.util.concurrent.TimeUnit;

public class Buck {
	
	public long timeStamp = System.currentTimeMillis();
	public int capacity = 100 ; //可以存放的水量
	public int rate = 20;	   //每秒处理的速度
	public int water = 0;    //当前的水量
	
	
 
	 
	public synchronized boolean grant(){
		//先执行漏水，因为rate是固定的，所以可以认为“时间间隔*rate”即为漏出的水量
		long now = System.currentTimeMillis();
		water = (int) Math.max(0, water- ((now - timeStamp) / 1000 )*rate);
		timeStamp = now;
		if(water < capacity ){
			water++ ;
			return true ;
		}else{
			return false ;
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		 Buck buck = new Buck();
		 for(int index = 0  ; index < 6 ; index++){
			new Thread(new ConsumerBuck(buck)).start();
		 }
	}
	

/*
    long timeStamp=getNowTime();
    int capacity; // 桶的容量
    int rate ; //令牌放入速度
    int tokens; //当前水量

    bool grant() {
      //先执行添加令牌的操作
      long now = getNowTime();
      tokens = max(capacity, tokens+ (now - timeStamp)*rate);
      timeStamp = now;
      //令牌已用完，拒绝访问
      if(tokens<1){
        return false;
      }else{//还有令牌，领取令牌
        tokens--;
        retun true;
      }
    }*/
	//http://blog.chinaunix.net/uid-26874207-id-4661792.html
	//http://iamzhongyong.iteye.com/blog/1742829
}
