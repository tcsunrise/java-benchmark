package com.tcsunrise.benchmark.lock;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// ReentrantLock
@State(Scope.Group)
public class ExplicitLock implements SafeMap {

    private final Lock lock = new ReentrantLock();
    private final Map<String, String> map = new HashMap<>();

    public ExplicitLock() {
        Keys keys = new Keys();
        for (int i = 0; i < 50; i++) {
            map.put(keys.next(), keys.next());
        }
    }

    @Override
    public String put(String k, String v) {
        lock.lock();
        try {
            return map.put(k, v);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public String get(String k) {
        lock.lock();
        try {
            return map.get(k);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public String remove(String k) {
        lock.lock();
        try {
            return map.remove(k);
        } finally {
            lock.unlock();
        }
    }
}
