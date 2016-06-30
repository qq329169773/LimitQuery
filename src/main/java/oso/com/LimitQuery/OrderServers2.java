package oso.com.LimitQuery;

import org.springframework.stereotype.Service;

import oso.com.LimitQuery.limit.MethodLimit;

@Service
public class OrderServers2 {

	@MethodLimit( limitUnavailable=1000 , limitWarning = 500)
	public void addOrder(){
		System.out.println("add Order2...");
	}
}
