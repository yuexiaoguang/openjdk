package jdk.nashorn.internal.runtime.regexp.joni.constants;

public interface MetaChar {
    final int ESCAPE            = 0;
    final int ANYCHAR           = 1;
    final int ANYTIME           = 2;
    final int ZERO_OR_ONE_TIME  = 3;
    final int ONE_OR_MORE_TIME  = 4;
    final int ANYCHAR_ANYTIME   = 5;

    final int INEFFECTIVE_META_CHAR = 0;
}
