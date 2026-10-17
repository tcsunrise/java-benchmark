package com.tcsunrise.benchmark.lock;

public interface SafeMap {
    String put(String k, String v);

    String get(String k);

    String remove(String k);
}
