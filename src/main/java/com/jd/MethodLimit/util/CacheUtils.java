package com.jd.MethodLimit.util;

import java.util.concurrent.TimeUnit;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;
import com.jd.MethodLimit.iface.HandRemovalNotificationIface;

public class CacheUtils {

   public static <K,V> LoadingCache<K,V> cached(CacheLoader<K , V> cacheLoader ,int expireAfterWriteMin ,final HandRemovalNotificationIface<K, V> handRemoveNotifycation) {
          LoadingCache<K , V> cache = CacheBuilder
          .newBuilder()
       /*   .weakKeys()
          .softValues()*/
          .expireAfterWrite(expireAfterWriteMin, TimeUnit.SECONDS)        
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
