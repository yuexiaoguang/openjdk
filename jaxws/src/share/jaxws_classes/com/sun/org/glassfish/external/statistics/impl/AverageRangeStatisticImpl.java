package com.sun.org.glassfish.external.statistics.impl;

import java.util.Map;
import java.lang.reflect.*;
import com.sun.org.glassfish.external.statistics.AverageRangeStatistic;

/**
 * An implementation of AverageRangeStatistic that provides ways to change the
 * state externally through mutators.  Convenience class that is useful for
 * components that gather the statistical data.
 * By merely changing the count (which is a mandatory measurement), rest of the statistical
 * information could be deduced.
 */
public final class AverageRangeStatisticImpl extends StatisticImpl implements
        AverageRangeStatistic, InvocationHandler {

    private long currentVal = 0L;
    private long highWaterMark = Long.MIN_VALUE;
    private long lowWaterMark = Long.MAX_VALUE;
    private long numberOfSamples = 0L;
    private long runningTotal = 0L;

    private final long initCurrentVal;
    private final long initHighWaterMark;
    private final long initLowWaterMark;
    private final long initNumberOfSamples;
    private final long initRunningTotal;

    private final AverageRangeStatistic as =
            (AverageRangeStatistic) Proxy.newProxyInstance(
            AverageRangeStatistic.class.getClassLoader(),
            new Class[] { AverageRangeStatistic.class },
            this);

    public AverageRangeStatisticImpl(long curVal, long highMark, long lowMark,
                                     String name, String unit, String desc,
                                     long startTime, long sampleTime) {
        super(name, unit, desc, startTime, sampleTime);
        currentVal = curVal;
        initCurrentVal = curVal;
        highWaterMark = highMark;
        initHighWaterMark = highMark;
        lowWaterMark = lowMark;
        initLowWaterMark = lowMark;
        numberOfSamples = 0L;
        initNumberOfSamples = numberOfSamples;
        runningTotal = 0L;
        initRunningTotal = runningTotal;
    }

    public synchronized AverageRangeStatistic getStatistic() {
        return as;
    }

    public synchronized String toString() {
        return super.toString() + NEWLINE +
            "Current: " + getCurrent() + NEWLINE +
            "LowWaterMark: " + getLowWaterMark() + NEWLINE +
            "HighWaterMark: " + getHighWaterMark() + NEWLINE +
            "Average:" + getAverage();
    }

    public synchronized Map getStaticAsMap() {
        Map m = super.getStaticAsMap();
        m.put("current", getCurrent());
        m.put("lowwatermark", getLowWaterMark());
        m.put("highwatermark", getHighWaterMark());
        m.put("average", getAverage());
        return m;
    }

    public synchronized void reset() {
        super.reset();
        currentVal = initCurrentVal;
        highWaterMark = initHighWaterMark;
        lowWaterMark = initLowWaterMark;
        numberOfSamples = initNumberOfSamples;
        runningTotal = initRunningTotal;
        sampleTime = -1L;
    }

    public synchronized long getAverage() {
        if(numberOfSamples == 0) {
            return -1;
        } else {
            return runningTotal / numberOfSamples;
        }
    }

    public synchronized long getCurrent() {
        return currentVal;
    }

    public synchronized void setCurrent(long curVal) {
        currentVal = curVal;
        lowWaterMark = (curVal >= lowWaterMark ? lowWaterMark : curVal);
        highWaterMark = (curVal >= highWaterMark ? curVal : highWaterMark);
        numberOfSamples++;
        runningTotal += curVal;
        sampleTime = System.currentTimeMillis();
    }

    public synchronized long getHighWaterMark() {
        return highWaterMark;
    }

    public synchronized long getLowWaterMark() {
        return lowWaterMark;
    }

    // todo: equals implementation
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        checkMethod(method);

        Object result;
        try {
            result = method.invoke(this, args);
        } catch (InvocationTargetException e) {
            throw e.getTargetException();
        } catch (Exception e) {
            throw new RuntimeException("unexpected invocation exception: " +
                       e.getMessage());
        }
        return result;
    }

}
