package com.jd.MethodLimit.iface;

import java.util.List;

import com.jd.MethodLimit.bean.MethodExecuteLimitBean;
import com.jd.MethodLimit.bean.MethodExecuteLogBean;
import com.jd.MethodLimit.exception.LimitSystemException;

public interface HandleMethodExecuteLogIface{
	
	void handMethodExecuteLog(MethodExecuteLogBean methodExecuteLogBean) throws LimitSystemException;
	
	void handMethodExecuteLog(List<MethodExecuteLogBean> methodExecuteLogBeans) throws LimitSystemException;
	
	void handMethodLimitLog(MethodExecuteLimitBean limitBean) throws LimitSystemException ;
	
	void handMethodLimitLog(List<MethodExecuteLimitBean> limitBeans) throws LimitSystemException ;

}
