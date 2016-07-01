package oso.com.LimitQuery;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

import oso.com.LimitQuery.limit.MethodLimit;

@Service
public class OrderServers {

	@MethodLimit( limitUnavailable=1000 , limitWarning = 500)
	public void addOrder(){
		try {
			TimeUnit.MILLISECONDS.sleep(1000+new Random().nextInt(5000));
		} catch (InterruptedException e) {
 			e.printStackTrace();
		}
 	}
	@MethodLimit( limitUnavailable=1000 , limitWarning = 500)
	public void deleteOrder(){
		try {
			TimeUnit.MILLISECONDS.sleep(2000+new Random().nextInt(5000));
			if(new Random().nextBoolean()){
 			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
 	}
}
