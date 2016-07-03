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
 		} catch (Exception e) {
 			System.out.println("exception.........");
 			e.printStackTrace();
		}
		 
 	}
	@MethodLimit( limitUnavailable=1000 , limitWarning = 500)
	public void addOrder1(){
		try {
			TimeUnit.MILLISECONDS.sleep(1000+new Random().nextInt(100));
			addOrder2();
		} catch (InterruptedException e) {
 			e.printStackTrace();
		}
 	}
	
	@MethodLimit( limitUnavailable=1000 , limitWarning = 500)
	public void addOrder2(){
		try {
			TimeUnit.MILLISECONDS.sleep(1000+new Random().nextInt(100));
			addOrder3();
		} catch (InterruptedException e) {
 			e.printStackTrace();
		}
 	}
	@MethodLimit( limitUnavailable=1000 , limitWarning = 500)
	public void addOrder3(){
		try {
			addOrder4();
			TimeUnit.MILLISECONDS.sleep(1000+new Random().nextInt(100));
		} catch (InterruptedException e) {
 			e.printStackTrace();
		}
 	}
	@MethodLimit( limitUnavailable=1000 , limitWarning = 500)
	public void addOrder4(){
		try {
			TimeUnit.MILLISECONDS.sleep(1000+new Random().nextInt(100));
		} catch (InterruptedException e) {
 			e.printStackTrace();
		}
 	}
	@MethodLimit( limitUnavailable=1000 , limitWarning = 500)
	public void addOrder5(){
		try {
			TimeUnit.MILLISECONDS.sleep(1000+new Random().nextInt(100));
			addOrder6();
			addOrder10();
		} catch (InterruptedException e) {
 			e.printStackTrace();
		}
 	}
	@MethodLimit( limitUnavailable=1000 , limitWarning = 500)
	public void addOrder6(){
		try {
			TimeUnit.MILLISECONDS.sleep(1000+new Random().nextInt(100));
		} catch (InterruptedException e) {
 			e.printStackTrace();
		}
 	}
	@MethodLimit( limitUnavailable=1000 , limitWarning = 500)
	public void addOrder7(){
		try {
			addOrder10();
			TimeUnit.MILLISECONDS.sleep(1000+new Random().nextInt(100));
		} catch (InterruptedException e) {
 			e.printStackTrace();
		}
 	}
	@MethodLimit( limitUnavailable=1000 , limitWarning = 500)
	public void addOrder8(){
		try {
			addOrder12();
			addOrder13();
			TimeUnit.MILLISECONDS.sleep(1000+new Random().nextInt(100));
		} catch (InterruptedException e) {
 			e.printStackTrace();
		}
 	}
	@MethodLimit( limitUnavailable=1000 , limitWarning = 500)
	public void addOrder9(){
		try {
			addOrder14();
 			TimeUnit.MILLISECONDS.sleep(1000+new Random().nextInt(100));
		} catch (InterruptedException e) {
 			e.printStackTrace();
		}
 	}
	@MethodLimit( limitUnavailable=1000 , limitWarning = 500)
	public void addOrder10(){
		try {
			TimeUnit.MILLISECONDS.sleep(1000+new Random().nextInt(100));
		} catch (InterruptedException e) {
 			e.printStackTrace();
		}
 	}
	@MethodLimit( limitUnavailable=1000 , limitWarning = 500)
	public void addOrder11(){
		try {
			TimeUnit.MILLISECONDS.sleep(1000+new Random().nextInt(100));
		} catch (InterruptedException e) {
 			e.printStackTrace();
		}
 	}
	@MethodLimit( limitUnavailable=1000 , limitWarning = 500)
	public void addOrder13(){
		try {
			TimeUnit.MILLISECONDS.sleep(1000+new Random().nextInt(100));
		} catch (InterruptedException e) {
 			e.printStackTrace();
		}
 	}
	@MethodLimit( limitUnavailable=1000 , limitWarning = 500)
	public void addOrder12(){
		try {
			addOrder17();
			addOrder18();
			TimeUnit.MILLISECONDS.sleep(1000+new Random().nextInt(100));
		} catch (InterruptedException e) {
 			e.printStackTrace();
		}
 	}
	@MethodLimit( limitUnavailable=1000 , limitWarning = 500)
	public void addOrder14(){
		try {
			addOrder18();
			TimeUnit.MILLISECONDS.sleep(1000+new Random().nextInt(100));
		} catch (InterruptedException e) {
 			e.printStackTrace();
		}
 	}
	@MethodLimit( limitUnavailable=1000 , limitWarning = 500)
	public void addOrder15(){
		try {
			addOrder18();
			TimeUnit.MILLISECONDS.sleep(1000+new Random().nextInt(100));
		} catch (InterruptedException e) {
 			e.printStackTrace();
		}
 	}
	@MethodLimit( limitUnavailable=1000 , limitWarning = 500)
	public void addOrder16(){
		try {
			addOrder18();
			TimeUnit.MILLISECONDS.sleep(1000+new Random().nextInt(100));
		} catch (InterruptedException e) {
 			e.printStackTrace();
		}
 	}
	@MethodLimit( limitUnavailable=1000 , limitWarning = 500)
	public void addOrder17(){
		try {
			TimeUnit.MILLISECONDS.sleep(1000+new Random().nextInt(100));
		} catch (InterruptedException e) {
 			e.printStackTrace();
		}
 	}
	@MethodLimit( limitUnavailable=1000 , limitWarning = 500)
	public void addOrder18(){
		try {
			TimeUnit.MILLISECONDS.sleep(1000+new Random().nextInt(100));
		} catch (InterruptedException e) {
 			e.printStackTrace();
		}
 	}
	@MethodLimit( limitUnavailable=1000 , limitWarning = 500)
	public void addOrder19(){
		try {
			TimeUnit.MILLISECONDS.sleep(1000+new Random().nextInt(100));
		} catch (InterruptedException e) {
 			e.printStackTrace();
		}
 	}
	@MethodLimit( limitUnavailable=1000 , limitWarning = 500)
	public void addOrder20(){
		try {
			TimeUnit.MILLISECONDS.sleep(1000+new Random().nextInt(100));
		} catch (InterruptedException e) {
 			e.printStackTrace();
		}
 	}
	@MethodLimit( limitUnavailable=1000 , limitWarning = 500)
	public void addOrder21(){
		try {
			TimeUnit.MILLISECONDS.sleep(1000+new Random().nextInt(100));
		} catch (InterruptedException e) {
 			e.printStackTrace();
		}
 	}
	@MethodLimit( limitUnavailable=1000 , limitWarning = 500)
	public void addOrder22(){
		try {
			TimeUnit.MILLISECONDS.sleep(1000+new Random().nextInt(100));
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
