package oso.com.LimitQuery.iface.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import oso.com.LimitQuery.bean.MethodExecuteLogBean;
import oso.com.LimitQuery.exception.LimitSystemException;
import oso.com.LimitQuery.iface.HandleMethodExecuteLogIface;

@Component
public class InsertHbaseHandleMethodExecuteLog implements HandleMethodExecuteLogIface{
	
	
	
	@Override
	public void handMethodExecuteLog(MethodExecuteLogBean methodExecuteLogBean) throws LimitSystemException {
 		
	}

	@Override
	public void handMethodExecuteLog(List<MethodExecuteLogBean> methodExecuteLogBeans) throws LimitSystemException {
 		
	}
}
