package oso.com.LimitQuery.iface;

import java.util.List;

import com.google.common.cache.RemovalNotification;

import oso.com.LimitQuery.bean.MethodExecuteLimitBean;
import oso.com.LimitQuery.bean.MethodExecuteLogBean;
import oso.com.LimitQuery.exception.LimitSystemException;

public interface HandleMethodExecuteLogIface{
	
	void handMethodExecuteLog(MethodExecuteLogBean methodExecuteLogBean) throws LimitSystemException;
	
	void handMethodExecuteLog(List<MethodExecuteLogBean> methodExecuteLogBeans) throws LimitSystemException;
	
	void handMethodLimitLog(MethodExecuteLimitBean limitBean) throws LimitSystemException ;
	
	void handMethodLimitLog(List<MethodExecuteLimitBean> limitBeans) throws LimitSystemException ;

}
