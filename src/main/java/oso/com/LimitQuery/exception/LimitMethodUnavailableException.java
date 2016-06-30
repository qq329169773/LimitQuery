package oso.com.LimitQuery.exception;

public class LimitMethodUnavailableException extends RuntimeException{
	/**
	 */
	private static final long serialVersionUID = 1L;
	
	public LimitMethodUnavailableException(){
		super("{msg:'当前服务繁忙,请稍后再试'}");
	}
	
}
