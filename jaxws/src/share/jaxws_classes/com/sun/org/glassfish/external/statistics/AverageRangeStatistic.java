package com.sun.org.glassfish.external.statistics;

/**
 * An interface that Specifies standard measurements of the lowest and highest
 * values an attribute has held as well as its current value.
 * Extending RangeStatistic, it also provides the average value.
 */
public interface AverageRangeStatistic extends RangeStatistic {

    public long getAverage();

}
