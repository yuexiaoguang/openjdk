package jdk.nashorn.internal.runtime.regexp.joni.constants;

// we dont need this ATM
public interface RegexState {
    final int NORMAL          = 0;
    final int SEARCHING       = 1;
    final int COMPILING       = -1;
    final int MODIFY          = -2;
}
