package oso.com.LimitQuery.iface.impl;

import org.springframework.stereotype.Component;

import com.google.common.cache.RemovalNotification;

import oso.com.LimitQuery.iface.HandRemovalNotificationIface;

@Component
public class PrintNotifycation<K,V> implements HandRemovalNotificationIface<K, V>{

	@Override
	public void handNotifycation(RemovalNotification<K, V> notifycation) {
		System.out.println("remove : {key : +"+notifycation.getKey()+" , value:"+notifycation.getValue()+"}");
	}
}
