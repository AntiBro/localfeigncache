package com.cached.simplecache.cache;

import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Service
public class DefaultCache implements LocalCache {
    private Logger log= LoggerFactory.getLogger(getClass());

    private ConcurrentHashMap<String,Object> localCache=new ConcurrentHashMap<>();

    private ConcurrentHashMap<String, ProceedingJoinPoint> proceedingJoinPointMaps=new ConcurrentHashMap<>();

    private ThreadPoolExecutor threadPoolExecutor=new ThreadPoolExecutor(8,16,500, TimeUnit.MICROSECONDS, new LinkedBlockingQueue<>(100));

    @Override
    public void setCache(String key, Object val) {
        log.debug("set feign cache key={}",key);
        localCache.put(key,val);
    }

    @Override
    public Object getCache(String key) {
        Object val=localCache.get(key);

        return val;
    }
}
