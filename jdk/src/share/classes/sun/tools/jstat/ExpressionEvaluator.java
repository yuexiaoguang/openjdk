package sun.tools.jstat;

import sun.jvmstat.monitor.MonitorException;

/**
 * An interface to allow an object to visit an Expression object and
 * evaluate based on some context.
 */
interface ExpressionEvaluator {
    Object evaluate(Expression e) throws MonitorException;
}
