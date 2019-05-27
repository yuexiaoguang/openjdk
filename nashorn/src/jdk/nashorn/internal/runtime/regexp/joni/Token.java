package jdk.nashorn.internal.runtime.regexp.joni;

import jdk.nashorn.internal.runtime.regexp.joni.constants.TokenType;

final class Token {
    TokenType type;
    boolean escaped;
    int backP;

    // union fields
    private int INT1, INT2, INT3, INT4;

    // union accessors
    int getC() {
        return INT1;
    }
    void setC(int c) {
        INT1 = c;
    }

    int getCode() {
        return INT1;
    }
    void setCode(int code) {
        INT1 = code;
    }

    int getAnchor() {
        return INT1;
    }
    void setAnchor(int anchor) {
        INT1 = anchor;
    }

    // repeat union member
    int getRepeatLower() {
        return INT1;
    }
    void setRepeatLower(int lower) {
        INT1 = lower;
    }

    int getRepeatUpper() {
        return INT2;
    }
    void setRepeatUpper(int upper) {
        INT2 = upper;
    }

    boolean getRepeatGreedy() {
        return INT3 != 0;
    }
    void setRepeatGreedy(boolean greedy) {
        INT3 = greedy ? 1 : 0;
    }

    boolean getRepeatPossessive() {
        return INT4 != 0;
    }
    void setRepeatPossessive(boolean possessive) {
        INT4 = possessive ? 1 : 0;
    }

    int getBackrefRef() {
        return INT2;
    }
    void setBackrefRef(int ref1) {
        INT2 = ref1;
    }

    // prop union member
    int getPropCType() {
        return INT1;
    }
    void setPropCType(int ctype) {
        INT1 = ctype;
    }

    boolean getPropNot() {
        return INT2 != 0;
    }
    void setPropNot(boolean not) {
        INT2 = not ? 1 : 0;
    }
}
