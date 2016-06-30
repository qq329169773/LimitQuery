package oso.com.LimitQuery;

import org.springframework.stereotype.Service;

import oso.com.LimitQuery.limit.MethodLimit;

@Service
public class OrderServers {

	@MethodLimit( limitUnavailable=1000 , limitWarning = 500)
	public void addOrder(){
		System.out.println("add Order...");
	}
}
