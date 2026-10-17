package com.tcsunrise.benchmark.lock;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import java.util.HashMap;
import java.util.Map;

// synchronized
@State(Scope.Group)
public class IntrinsicLock implements SafeMap {

    private final Object lock = new Object();
    private final Map<String, String> map = new HashMap<>();

    public IntrinsicLock() {
        Keys keys = new Keys();
        for (int i = 0; i < 50; i++) {
            map.put(keys.next(), keys.next());
        }
    }

    @Override
    public String put(String k, String v) {
        synchronized (lock) {
            return map.put(k, v);
        }
    }

    @Override
    public String get(String k) {
        synchronized (lock) {
            return map.get(k);
        }
    }

    @Override
    public String remove(String k) {
        synchronized (lock) {
            return map.remove(k);
        }
    }


}
