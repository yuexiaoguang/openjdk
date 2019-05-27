package jdk.nashorn.internal.runtime.regexp.joni.ast;

import jdk.nashorn.internal.runtime.regexp.joni.EncodingHelper;
import jdk.nashorn.internal.runtime.regexp.joni.constants.StringType;

public final class StringNode extends Node implements StringType {

    private static final int NODE_STR_MARGIN = 16;
    private static final int NODE_STR_BUF_SIZE = 24;
    public static final StringNode EMPTY = new StringNode(null, Integer.MAX_VALUE, Integer.MAX_VALUE);

    public char[] chars;
    public int p;
    public int end;

    public int flag;

    public StringNode() {
        this.chars = new char[NODE_STR_BUF_SIZE];
    }

    public StringNode(char[] chars, int p, int end) {
        this.chars = chars;
        this.p = p;
        this.end = end;
        setShared();
    }

    public StringNode(char c) {
        this();
        chars[end++] = c;
    }

    /* Ensure there is ahead bytes available in node's buffer
     * (assumes that the node is not shared)
     */
    public void ensure(int ahead) {
        int len = (end - p) + ahead;
        if (len >= chars.length) {
            char[] tmp = new char[len + NODE_STR_MARGIN];
            System.arraycopy(chars, p, tmp, 0, end - p);
            chars = tmp;
        }
    }

    /* COW and/or ensure there is ahead bytes available in node's buffer
     */
    private void modifyEnsure(int ahead) {
        if (isShared()) {
            int len = (end - p) + ahead;
            char[] tmp = new char[len + NODE_STR_MARGIN];
            System.arraycopy(chars, p, tmp, 0, end - p);
            chars = tmp;
            end = end - p;
            p = 0;
            clearShared();
        } else {
            ensure(ahead);
        }
    }

    @Override
    public int getType() {
        return STR;
    }

    @Override
    public String getName() {
        return "String";
    }

    @Override
    public String toString(int level) {
        StringBuilder value = new StringBuilder();
        value.append("\n  bytes: '");
        for (int i=p; i<end; i++) {
            if (chars[i] >= 0x20 && chars[i] < 0x7f) {
                value.append(chars[i]);
            } else {
                value.append(String.format("[0x%04x]", (int)chars[i]));
            }
        }
        value.append("'");
        return value.toString();
    }

    public int length() {
        return end - p;
    }

    public StringNode splitLastChar() {
        StringNode n = null;

        if (end > p) {
            int prev = EncodingHelper.prevCharHead(p, end);
            if (prev != -1 && prev > p) { /* can be splitted. */
                n = new StringNode(chars, prev, end);
                if (isRaw()) n.setRaw();
                end = prev;
            }
        }
        return n;
    }

    public boolean canBeSplit() {
        return end > p && 1 < (end - p);
    }

    public void set(char[] chars, int p, int end) {
        this.chars = chars;
        this.p = p;
        this.end = end;
        setShared();
    }

    public void cat(char[] cat, int catP, int catEnd) {
        int len = catEnd - catP;
        modifyEnsure(len);
        System.arraycopy(cat, catP, chars, end, len);
        end += len;
    }

    public void cat(char c) {
        modifyEnsure(1);
        chars[end++] = c;
    }

    public void catCode(int code) {
        cat((char)code);
    }

    public void clear() {
        if (chars.length > NODE_STR_BUF_SIZE) chars = new char[NODE_STR_BUF_SIZE];
        flag = 0;
        p = end = 0;
    }

    public void setRaw() {
        flag |= NSTR_RAW;
    }

    public void clearRaw() {
        flag &= ~NSTR_RAW;
    }

    public boolean isRaw() {
        return (flag & NSTR_RAW) != 0;
    }

    public void setAmbig() {
        flag |= NSTR_AMBIG;
    }

    public void clearAmbig() {
        flag &= ~NSTR_AMBIG;
    }

    public boolean isAmbig() {
        return (flag & NSTR_AMBIG) != 0;
    }

    public void setDontGetOptInfo() {
        flag |= NSTR_DONT_GET_OPT_INFO;
    }

    public void clearDontGetOptInfo() {
        flag &= ~NSTR_DONT_GET_OPT_INFO;
    }

    public boolean isDontGetOptInfo() {
        return (flag & NSTR_DONT_GET_OPT_INFO) != 0;
    }

    public void setShared() {
        flag |= NSTR_SHARED;
    }

    public void clearShared() {
        flag &= ~NSTR_SHARED;
    }

    public boolean isShared() {
        return (flag & NSTR_SHARED) != 0;
    }
}