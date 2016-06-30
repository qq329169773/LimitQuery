package oso.com.LimitQuery.limit;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import oso.com.LimitQuery.OrderServers;
import oso.com.LimitQuery.OrderServers2;


public class ATest {

	public static void main(String[] args) {
		 
		ClassPathXmlApplicationContext appContext = new ClassPathXmlApplicationContext("/applicationContext.xml");
		OrderServers orderServers = appContext.getBean(OrderServers.class);
		ThreadLocalParams.add(MethodLimitContants.IP, "127.0.0.1");
		orderServers.addOrder();
		OrderServers2 orderServers2 = appContext.getBean(OrderServers2.class);
		orderServers2.addOrder();

	}
}
