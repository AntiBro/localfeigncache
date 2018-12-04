package com.cached.simplecache.cache;

public interface LocalCache {
    void setCache(String key, Object val);

    Object getCache(String key);
}
