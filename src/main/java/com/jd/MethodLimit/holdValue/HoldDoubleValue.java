package com.jd.MethodLimit.holdValue;

import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;

import com.jd.MethodLimit.bean.MethodExecuteLimitBean;
import com.jd.MethodLimit.bean.MethodExecuteLogBean;

/**
 * HoldDoubleValue
 * @author zhangrui25
 * @param <A>
 * @param <B>
 */
public class HoldDoubleValue<A,B> {

	public final A a ;
	public final B b ;
	public HoldDoubleValue(A a, B b) {
		super();
		this.a = a;
		this.b = b;
	}

	@Override
	public int hashCode() {
 		return a.hashCode() + b.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof HoldDoubleValue){
			HoldDoubleValue<A,B> other = (HoldDoubleValue<A,B>) obj;
	 		return other.a.equals(a) && other.b.equals(b);
		}
		return false ;
	}
	
	public static void main(String[] args) {
		Logger logger = Logger.getLogger("method_request_log");
		for(int index = 0 ; index < 100000 ; index++ ){
			logger.info(new MethodExecuteLogBean());
		} 
		Logger logger2 = Logger.getLogger("method_limit_log");
		for(int index = 0 ; index < 100000 ; index++ ){
			logger2.info(new MethodExecuteLimitBean(1, 1, 2));
		} 
		
		Logger logger1 = Logger.getLogger(HoldDoubleValue.class.getName());
 		logger1.info("test");
		/*logger.info("343434");
		logger.info("34341111134");
		Logger logger2 = Logger.getLogger(HoldDoubleValue.class.getSimpleName());
		logger2.info("testw");*/
	}
}
