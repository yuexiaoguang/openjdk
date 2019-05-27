package jdk.nashorn.internal.runtime.regexp.joni;

final class BitStatus {
    public static final int BIT_STATUS_BITS_NUM = 4 * 8;

    public static int bsClear() {
        return 0;
    }

    public static int bsAll() {
        return -1;
    }

    public static boolean bsAt(int stats, int n) {
        return (n < BIT_STATUS_BITS_NUM ? stats & (1 << n) : (stats & 1)) != 0;
    }

    public static int bsOnAt(int stats, int n) {
        if (n < BIT_STATUS_BITS_NUM) {
            stats |= (1 << n);
        } else {
            stats |= 1;
        }
        return stats;
    }

    public static int bsOnOff(int v, int f, boolean negative) {
        if (negative) {
            v &= ~f;
        } else {
            v |= f;
        }
        return v;
    }
}
