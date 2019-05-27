package com.sun.org.glassfish.external.statistics.impl;

import com.sun.org.glassfish.external.statistics.BoundedRangeStatistic;
import java.util.Map;
import java.lang.reflect.*;


public final class BoundedRangeStatisticImpl extends StatisticImpl
    implements BoundedRangeStatistic, InvocationHandler {

    private long lowerBound = 0L;
    private long upperBound = 0L;
    private long currentVal = 0L;
    private long highWaterMark = Long.MIN_VALUE;
    private long lowWaterMark = Long.MAX_VALUE;

    private final long initLowerBound;
    private final long initUpperBound;
    private final long initCurrentVal;
    private final long initHighWaterMark;
    private final long initLowWaterMark;

    private final BoundedRangeStatistic bs =
            (BoundedRangeStatistic) Proxy.newProxyInstance(
            BoundedRangeStatistic.class.getClassLoader(),
            new Class[] { BoundedRangeStatistic.class },
            this);

    public synchronized String toString() {
        return super.toString() + NEWLINE +
            "Current: " + getCurrent() + NEWLINE +
            "LowWaterMark: " + getLowWaterMark() + NEWLINE +
            "HighWaterMark: " + getHighWaterMark() + NEWLINE +
            "LowerBound: " + getLowerBound() + NEWLINE +
            "UpperBound: " + getUpperBound();
    }


    public BoundedRangeStatisticImpl(long curVal, long highMark, long lowMark,
                                     long upper, long lower, String name,
                                     String unit, String desc, long startTime,
                                     long sampleTime) {
        super(name, unit, desc, startTime, sampleTime);
        currentVal = curVal;
        initCurrentVal = curVal;
        highWaterMark = highMark;
        initHighWaterMark = highMark;
        lowWaterMark = lowMark;
        initLowWaterMark = lowMark;
        upperBound = upper;
        initUpperBound = upper;
        lowerBound = lower;
        initLowerBound = lower;
    }

    public synchronized BoundedRangeStatistic getStatistic() {
        return bs;
    }

    public synchronized Map getStaticAsMap() {
        Map m = super.getStaticAsMap();
        m.put("current", getCurrent());
        m.put("lowerbound", getLowerBound());
        m.put("upperbound", getUpperBound());
        m.put("lowwatermark", getLowWaterMark());
        m.put("highwatermark", getHighWaterMark());
        return m;
    }

    public synchronized long getCurrent() {
        return currentVal;
    }

    public synchronized void setCurrent(long curVal) {
        currentVal = curVal;
        lowWaterMark = (curVal >= lowWaterMark ? lowWaterMark : curVal);
        highWaterMark = (curVal >= highWaterMark ? curVal : highWaterMark);
        sampleTime = System.currentTimeMillis();
    }

    public synchronized long getHighWaterMark() {
        return highWaterMark;
    }

    public synchronized void setHighWaterMark(long hwm) {
        highWaterMark = hwm;
    }

    public synchronized long getLowWaterMark() {
        return lowWaterMark;
    }

    public synchronized void setLowWaterMark(long lwm) {
        lowWaterMark = lwm;
    }

    public synchronized long getLowerBound() {
        return lowerBound;
    }

    public synchronized long getUpperBound() {
        return upperBound;
    }

    @Override
    public synchronized void reset() {
        super.reset();
        lowerBound = initLowerBound;
        upperBound = initUpperBound;
        currentVal = initCurrentVal;
        highWaterMark = initHighWaterMark;
        lowWaterMark = initLowWaterMark;
        sampleTime = -1L;
    }

    // todo: equals implementation
    public Object invoke(Object proxy, Method m, Object[] args) throws Throwable {
        checkMethod(m);

        Object result;
        try {
            result = m.invoke(this, args);
        } catch (InvocationTargetException e) {
            throw e.getTargetException();
        } catch (Exception e) {
            throw new RuntimeException("unexpected invocation exception: " +
                       e.getMessage());
        }
        return result;
    }
}
