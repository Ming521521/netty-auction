package com.ming.netty.cache;

import org.springframework.stereotype.Component;
import org.springframework.web.util.pattern.PathPattern;

import java.util.concurrent.ConcurrentHashMap;

@Component
//线程共享缓存
public class CacheFactory <K,V> implements  Cache<K,V>{

    private  volatile ConcurrentHashMap<K,V> concurrentHashMap=new ConcurrentHashMap<>();
    @Override
    public V getCache(K k) {
        return concurrentHashMap.get(k);
    }

    @Override
    public void addCache(K k, V v) {
        concurrentHashMap.put(k,v);
    }

    @Override
    public void putIfAbsentCache(K k, V v) {
        concurrentHashMap.putIfAbsent(k,v);
    }

    @Override
    public void remove(K k) {
        concurrentHashMap.remove(k);
    }
}
