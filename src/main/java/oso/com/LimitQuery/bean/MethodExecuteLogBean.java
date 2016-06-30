package oso.com.LimitQuery.bean;

/**
 * 1.记录某个时间段的IP+MethodSign的记录
 * 2.需要算出平均时间(先加上所有的时间,然后在除法)
 * @author zhangrui25
 *
 */
public class MethodExecuteLogBean {

	 
	private String uri ;				//如果是Web段访问，那么找到他的访问地址
	private int requestType ;			//(1:web,2:JSF)
	private String ip ;					//访问的IP地址
	private String hostIp;				//本地IP地址
	private String methodSign ;			//方法签名
	private int countExecuteFailed ;	//某个时间段执行失败次数
	private int countExecuteSucess ;	//某个时间段执行成功次数
	private int countExeuceteLimit ;	//某个时间段因为限流导致执行失败次数
	private int countExecute ;			//某个时间段总共执行了多少次
	private int avgTimeMethodExecute ;	//在某个时间段方法执行的时间(avg)
	private int maxTimeMethodExecute ;	//最长执行时间
	private int minTimeMethodExecute ;	//最短执行时间
	
	
	
 }
