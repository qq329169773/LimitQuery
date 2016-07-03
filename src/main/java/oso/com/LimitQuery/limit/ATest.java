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
		ThreadLocalParams.add(MethodLimitContants.IP, "127.0.0.1"+ new Random().nextInt(2));
	 

		switch (new Random().nextInt(2)) {
			case 0: orderServers1.addOrder1(); break;
			case 1: orderServers1.addOrder1(); break;
			case 2: orderServers1.addOrder2(); break;
			case 3: orderServers1.addOrder3(); break;
			case 4: orderServers1.addOrder4(); break;
			case 5: orderServers1.addOrder5(); break;
			case 6: orderServers1.addOrder6(); break;
			case 7: orderServers1.addOrder7(); break;
			case 8: orderServers1.addOrder8(); break;
			case 9: orderServers1.addOrder9(); break;
			case 10: orderServers1.addOrder10(); break;
			case 11: orderServers1.addOrder11(); break;
			case 12: orderServers1.addOrder12(); break;
			case 13: orderServers1.addOrder13(); break;
			case 14: orderServers1.addOrder14(); break;
			case 15: orderServers1.addOrder15(); break;
			case 16: orderServers1.addOrder16(); break;
			case 17: orderServers1.addOrder17(); break;
			case 18: orderServers1.addOrder18(); break;
			case 19: orderServers1.addOrder19(); break;
			case 20: orderServers1.addOrder20(); break;
			case 21: orderServers1.addOrder21(); break;
			case 22: orderServers1.addOrder22(); break;
			case 23: orderServers1.addOrder(); break;
			case 24: orderServers1.deleteOrder(); break;
		}
	}
}

public class ATest {

	public static void main(String[] args) throws InterruptedException, IOException {
		 
		ClassPathXmlApplicationContext appContext = new ClassPathXmlApplicationContext("/applicationContext.xml");
		final OrderServers orderServers = appContext.getBean(OrderServers.class);
		 
		for(int index = 0 ; index < 10 ; index++){
 			new Thread(new MethodExe(orderServers)).start();
 		}
	 	/*for(int index = 0 ; index < 100000 ; index++){
 			new Thread(new MethodExe(orderServers)).start();
 		}
		for(int index = 0 ; index < 100000 ; index++){
 			new Thread(new MethodExe(orderServers)).start();
 		}
		for(int index = 0 ; index < 100000 ; index++){
 			new Thread(new MethodExe(orderServers)).start();
 		}
		for(int index = 0 ; index < 100000 ; index++){
 			new Thread(new MethodExe(orderServers)).start();
 		}
		for(int index = 0 ; index < 100000 ; index++){
 			new Thread(new MethodExe(orderServers)).start();
 		}
		for(int index = 0 ; index < 100000 ; index++){
 			new Thread(new MethodExe(orderServers)).start();
 		}
		for(int index = 0 ; index < 100000 ; index++){
 			new Thread(new MethodExe(orderServers)).start();
 		}
		for(int index = 0 ; index < 100000 ; index++){
 			new Thread(new MethodExe(orderServers)).start();
 		}
		for(int index = 0 ; index < 100000 ; index++){
 			new Thread(new MethodExe(orderServers)).start();
 		}
		*/
		System.out.println("000000000000");
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println(reader.readLine() + "readLine "); 
 	}
}
