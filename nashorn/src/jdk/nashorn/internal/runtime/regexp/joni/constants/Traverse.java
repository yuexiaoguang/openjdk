package jdk.nashorn.internal.runtime.regexp.joni.constants;

public interface Traverse {
    final int TRAVERSE_CALLBACK_AT_FIRST = 1;
    final int TRAVERSE_CALLBACK_AT_LAST = 2;
    final int TRAVERSE_CALLBACK_AT_BOTH = TRAVERSE_CALLBACK_AT_FIRST | TRAVERSE_CALLBACK_AT_LAST;
}
