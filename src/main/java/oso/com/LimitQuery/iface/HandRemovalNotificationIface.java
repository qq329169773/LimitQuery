package oso.com.LimitQuery.iface;

import com.google.common.cache.RemovalNotification;

public interface HandRemovalNotificationIface<K,V>{

	void handNotifycation(RemovalNotification<K,V> notifycation);
}
