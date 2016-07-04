package com.jd.MethodLimit.iface;

import com.google.common.cache.RemovalNotification;

public interface HandRemovalNotificationIface<K,V>{

	void handNotifycation(RemovalNotification<K,V> notifycation);
}
