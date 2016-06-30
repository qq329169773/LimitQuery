package oso.com.LimitQuery.limit;

import java.util.Random;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import oso.com.LimitQuery.OrderServers;
import oso.com.LimitQuery.OrderServers2;


class MethodExe implements Runnable{
	OrderServers orderServers1 = null ;
	
	
	public MethodExe(OrderServers orderServers1) {
		super();
		this.orderServers1 = orderServers1;
	}


	@Override
	public void run() {
		ThreadLocalParams.add(MethodLimitContants.IP, "127.0.0.1");
		orderServers1.addOrder();
	}
}

public class ATest {

	public static void main(String[] args) throws InterruptedException {
		 
		ClassPathXmlApplicationContext appContext = new ClassPathXmlApplicationContext("/applicationContext.xml");
		OrderServers orderServers = appContext.getBean(OrderServers.class);
		ThreadLocalParams.add(MethodLimitContants.IP, "127.0.0.1"+ new Random().nextInt(10000));
		orderServers.addOrder();
		 
		for(int index = 0 ; index < 100000000 ; index++){
 			new Thread(new MethodExe(orderServers)).start();
		}
	}
}
