package oso.com.LimitQuery.utils;

import java.util.concurrent.TimeUnit;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;

import oso.com.LimitQuery.iface.HandRemovalNotificationIface;

public class CacheUtils {

   public static <K,V> LoadingCache<K,V> cached(CacheLoader<K , V> cacheLoader ,int expireAfterWriteMin ,final HandRemovalNotificationIface<K, V> handRemoveNotifycation) {
          LoadingCache<K , V> cache = CacheBuilder
          .newBuilder()
          .weakKeys()
          .softValues()
          .expireAfterWrite(expireAfterWriteMin, TimeUnit.MINUTES)        
          .removalListener(new RemovalListener<K, V>(){
			@Override
			public void onRemoval(RemovalNotification<K, V> n) {
				if(handRemoveNotifycation != null){
					handRemoveNotifycation.handNotifycation(n);
				}
			}})
          .build(cacheLoader);
          return cache;
    }
}
