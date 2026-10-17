package com.tcsunrise.benchmark.lock;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

@State(Scope.Thread)
public class Keys {
    String[] keys = new String[1024];
    int i;

    public Keys() {
        init();
    }

    private void init() {
        for (int j = 0; j < keys.length; j++) keys[j] = "k" + j;
    }

    public String next() {
        return keys[(i++) & 1023];
    }
}
