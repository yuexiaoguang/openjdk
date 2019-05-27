package com.sun.org.glassfish.external.statistics;

/**
 * Specifies standard measurements of the upper and lower limits of the value of an attribute.
 */
public interface BoundaryStatistic extends Statistic {
    /**
     * The upper limit of the value of this attribute.
     */
    long getUpperBound();

    /**
     * The lower limit of the value of this attribute.The upper limit of the value of this attribute.
     */
    long getLowerBound();
}
