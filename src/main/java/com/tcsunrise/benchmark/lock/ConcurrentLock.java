package com.tcsunrise.benchmark.lock;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@State(Scope.Group)
public class ConcurrentLock implements SafeMap {

    private static final Map<String, String> map = new ConcurrentHashMap<>();

    public ConcurrentLock() {
        Keys keys = new Keys();
        for (int i = 0; i < 50; i++) {
            map.put(keys.next(), keys.next());
        }
    }

    @Override
    public String put(String k, String v) {
        return map.put(k, v);
    }

    @Override
    public String get(String k) {
        return map.get(k);
    }

    @Override
    public String remove(String k) {
        return map.remove(k);
    }
}
