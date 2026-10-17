/*
 * Copyright (c) 2005, 2014, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */

package com.tcsunrise.benchmark;

import com.tcsunrise.benchmark.lock.*;
import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

@Fork(3)
@Warmup(iterations = 3, time = 2, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 3, timeUnit = TimeUnit.SECONDS)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class LockBenchmark {

    @Threads(10)
    @Benchmark
    public void baseline() {
    }

    @Group("concurrentMap")
    @GroupThreads(5)
    @Benchmark
    public String concurrentMapW(ConcurrentLock safeMap, Keys k) {
        return mapPut(safeMap, k);
    }

    @Group("concurrentMap")
    @GroupThreads(5)
    @Benchmark
    public String concurrentMapR(ConcurrentLock safeMap, Keys k) {
        return mapGet(safeMap, k);
    }

    @Group("explicitLock")
    @GroupThreads(5)
    @Benchmark
    public String explicitLockW(ExplicitLock safeMap, Keys k) {
        return mapPut(safeMap, k);
    }

    @Group("explicitLock")
    @GroupThreads(5)
    @Benchmark
    public String explicitLockR(ExplicitLock safeMap, Keys k) {
        return mapGet(safeMap, k);
    }

    @Group("intrinsicLock")
    @GroupThreads(5)
    @Benchmark
    public String intrinsicLockW(IntrinsicLock safeMap, Keys k) {
        return mapPut(safeMap, k);
    }

    @Group("intrinsicLock")
    @GroupThreads(5)
    @Benchmark
    public String intrinsicLockR(IntrinsicLock safeMap, Keys k) {
        return mapGet(safeMap, k);
    }

    private String mapPut(SafeMap safeMap, Keys k) {
        String next = k.next();
        return safeMap.put(next, next);
    }

    private String mapGet(SafeMap safeMap, Keys k) {
        return safeMap.get(k.next());
    }

}
