package jdk.nashorn.internal.runtime.regexp.joni.constants;

public interface EncloseType {
    final int MEMORY                = 1<<0;
    final int OPTION                = 1<<1;
    final int STOP_BACKTRACK        = 1<<2;

    final int ALLOWED_IN_LB         = MEMORY;
    final int ALLOWED_IN_LB_NOT     = 0;
}
