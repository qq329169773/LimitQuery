package oso.com.LimitQuery.limit;

import java.util.HashSet;
import java.util.Random;

public class A {

	public static void main(String[] args) {
		HashSet<Integer> set = new HashSet<Integer>();
		while(set.size() < 10){
			set.add(new Random().nextInt(10));
		}
		System.out.println(set);
	}
}
