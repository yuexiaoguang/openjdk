package jdk.nashorn.internal.runtime.regexp.joni.constants;

public interface StringType {
    final int NSTR_RAW               = 1<<0;
    final int NSTR_AMBIG             = 1<<1;
    final int NSTR_DONT_GET_OPT_INFO = 1<<2;
    final int NSTR_SHARED            = 1<<3;
}
