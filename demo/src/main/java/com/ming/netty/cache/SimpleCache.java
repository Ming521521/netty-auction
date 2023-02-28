package com.ming.netty.cache;

import com.sun.org.apache.xerces.internal.util.DatatypeMessageFormatter;

import java.nio.charset.MalformedInputException;
import java.util.Map;
import java.util.concurrent.*;

public class SimpleCache {
    public static final Map<String,Entity> MAP=new ConcurrentHashMap<>();
    public static final ScheduledExecutorService EXECUTOR_SERVICE= Executors.newSingleThreadScheduledExecutor();

    public synchronized  static  void  put(String key,Object data,long expire){
        SimpleCache.remove(key);
        if (expire>0) {
            Future<?> future = EXECUTOR_SERVICE.schedule(() -> {
                synchronized (Cache.class) {
                    MAP.remove(key);
                }
            }, expire, TimeUnit.MILLISECONDS);
            MAP.put(key, new Entity(data, future));
        }else {
            MAP.put(key,new Entity(data,null));
        }
    }

    private synchronized static <T> T remove(String key) {
        Entity entity=MAP.remove(key);
        if (entity==null){
            return null;
        }
        if (entity.future!=null){
            entity.future.cancel(true);
        }
        return (T) entity.value;
    }
    public synchronized static <T> T get(String key){
        Entity entity=MAP.get(key);
        return entity== null?null: (T) entity.value;
    }
    public synchronized  static  int size(){
        return MAP.size();
    }

    private static class Entity {
        /**
         * 键值对的value
         */
        public Object value;
        /**
         * 定时器Future
         */
        public Future future;

        public Entity(Object value, Future future) {
            this.value = value;
            this.future = future;
        }
    }
}
