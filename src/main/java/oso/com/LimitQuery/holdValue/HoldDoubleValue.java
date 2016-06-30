package oso.com.LimitQuery.holdValue;

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
	
/*	public static void main(String[] args) {
		HoldDoubleValue<String, String> a = new HoldDoubleValue<String, String>("san", "zhang");
		System.out.println(a.equals( new HoldDoubleValue<String, String>("san", "zhang")));
	}*/
}
