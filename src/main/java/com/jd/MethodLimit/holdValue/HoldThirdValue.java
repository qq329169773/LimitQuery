package com.jd.MethodLimit.holdValue;

/**
 * HoldDoubleValue
 * @author zhangrui25
 * @param <A>
 * @param <B>
 */
public class HoldThirdValue<A,B,C> extends HoldDoubleValue<A, B>{
	 
	public C c ;
	public HoldThirdValue(A a, B b , C c) {
		super(a,b);
		this.c = c;
 	}

	@Override
	public int hashCode() {
 		return super.hashCode() + c.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof HoldThirdValue){
			HoldThirdValue<A,B,C> other = (HoldThirdValue<A,B,C>) obj;
	 		return other.a.equals(a) && other.b.equals(b) && other.c.equals(c);
		}
		return false ;
	}
	
	/*public static void main(String[] args) {
		HoldThirdValue<String, String,String> a = new HoldThirdValue<String, String,String>("san", "zhang","li");
		System.out.println(a.equals( new HoldThirdValue<String, String,String>("san", "zhang","lis")));
	}*/
}
