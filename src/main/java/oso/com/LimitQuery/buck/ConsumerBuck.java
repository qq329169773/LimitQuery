package oso.com.LimitQuery.buck;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

public class ConsumerBuck implements Runnable {

	private Buck buck ;
	
	public ConsumerBuck(Buck buck) {
		super();
		this.buck = buck;
	}



	@Override
	public void run() {
		while(true){
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
 				e.printStackTrace();
			}
			if(buck.grant()){
				System.out.println("exeucte..." +" water : " +buck.water + " capacity :" + buck.capacity);
			}else{
				System.out.println("Failed");
				try {
					TimeUnit.SECONDS.sleep(6);
				} catch (InterruptedException e) {
	 				e.printStackTrace();
				}
			}
		}
	}
}
