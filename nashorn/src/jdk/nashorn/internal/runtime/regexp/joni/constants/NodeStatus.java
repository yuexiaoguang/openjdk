package jdk.nashorn.internal.runtime.regexp.joni.constants;

public interface NodeStatus {
    /* status bits */
    final int NST_MIN_FIXED            = (1<<0);
    final int NST_MAX_FIXED            = (1<<1);
    final int NST_CLEN_FIXED           = (1<<2);
    final int NST_MARK1                = (1<<3);
    final int NST_MARK2                = (1<<4);
    final int NST_MEM_BACKREFED        = (1<<5);
    final int NST_STOP_BT_SIMPLE_REPEAT= (1<<6);
    final int NST_RECURSION            = (1<<7);
    final int NST_CALLED               = (1<<8);
    final int NST_ADDR_FIXED           = (1<<9);
    final int NST_NAMED_GROUP          = (1<<10);
    final int NST_NAME_REF             = (1<<11);
    final int NST_IN_REPEAT            = (1<<12);   /* STK_REPEAT is nested in stack. */
    final int NST_NEST_LEVEL           = (1<<13);
    final int NST_BY_NUMBER            = (1<<14);   /* {n,m} */
}
