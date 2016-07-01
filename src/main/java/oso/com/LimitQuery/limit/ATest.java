package oso.com.LimitQuery.limit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import oso.com.LimitQuery.OrderServers;
import oso.com.LimitQuery.OrderServers2;
import oso.com.LimitQuery.bean.MethodExecuteLogBean;


class MethodExe implements Runnable{
	OrderServers orderServers1 = null ;
	
	
	public MethodExe(OrderServers orderServers1) {
		super();
		this.orderServers1 = orderServers1;
	}


	@Override
	public void run() {
		ThreadLocalParams.add(MethodLimitContants.IP, "127.0.0.1"+ new Random().nextInt(3) );
		if(new Random().nextBoolean()){
			orderServers1.addOrder();
		}else{
			orderServers1.deleteOrder();
		}
	}
}

public class ATest {

	public static void main(String[] args) throws InterruptedException, IOException {
		 
		ClassPathXmlApplicationContext appContext = new ClassPathXmlApplicationContext("/applicationContext.xml");
		final OrderServers orderServers = appContext.getBean(OrderServers.class);
		 
		for(int index = 0 ; index < 1000 ; index++){
 			new Thread(new MethodExe(orderServers)).start();
 		}
		for(int index = 0 ; index < 1000 ; index++){
 			new Thread(new MethodExe(orderServers)).start();
 		}
		for(int index = 0 ; index < 1000 ; index++){
 			new Thread(new MethodExe(orderServers)).start();
 		}
		for(int index = 0 ; index < 1000 ; index++){
 			new Thread(new MethodExe(orderServers)).start();
 		}
		for(int index = 0 ; index < 1000 ; index++){
 			new Thread(new MethodExe(orderServers)).start();
 		}
		for(int index = 0 ; index < 1000 ; index++){
 			new Thread(new MethodExe(orderServers)).start();
 		}
		for(int index = 0 ; index < 1000 ; index++){
 			new Thread(new MethodExe(orderServers)).start();
 		}
		for(int index = 0 ; index < 1000 ; index++){
 			new Thread(new MethodExe(orderServers)).start();
 		}
		for(int index = 0 ; index < 1000 ; index++){
 			new Thread(new MethodExe(orderServers)).start();
 		}
		for(int index = 0 ; index < 1000 ; index++){
 			new Thread(new MethodExe(orderServers)).start();
 		}
		
		System.out.println("000000000000");
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println(reader.readLine() + "readLine "); 
 	}
}
