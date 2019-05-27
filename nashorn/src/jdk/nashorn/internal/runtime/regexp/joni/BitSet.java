package jdk.nashorn.internal.runtime.regexp.joni;

public final class BitSet {
    static final int BITS_PER_BYTE = 8;
    public static final int SINGLE_BYTE_SIZE = (1 << BITS_PER_BYTE);
    private static final int BITS_IN_ROOM = 4 * BITS_PER_BYTE;
    static final int BITSET_SIZE = (SINGLE_BYTE_SIZE / BITS_IN_ROOM);
    static final int ROOM_SHIFT = log2(BITS_IN_ROOM);

    final int[] bits = new int[BITSET_SIZE];

    private static final int BITS_TO_STRING_WRAP = 4;
    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        buffer.append("BitSet");
        for (int i=0; i<SINGLE_BYTE_SIZE; i++) {
            if ((i % (SINGLE_BYTE_SIZE / BITS_TO_STRING_WRAP)) == 0) buffer.append("\n  ");
            buffer.append(at(i) ? "1" : "0");
        }
        return buffer.toString();
    }

    public boolean at(int pos) {
        return (bits[pos >>> ROOM_SHIFT] & bit(pos)) != 0;
    }

    public void set(int pos) {
        bits[pos >>> ROOM_SHIFT] |= bit(pos);
    }

    public void clear(int pos) {
        bits[pos >>> ROOM_SHIFT] &= ~bit(pos);
    }

    public void clear() {
        for (int i=0; i<BITSET_SIZE; i++) bits[i]=0;
    }

    public boolean isEmpty() {
        for (int i=0; i<BITSET_SIZE; i++) {
            if (bits[i] != 0) return false;
        }
        return true;
    }

    public void setRange(int from, int to) {
        for (int i=from; i<=to && i < SINGLE_BYTE_SIZE; i++) set(i);
    }

    public void invert() {
        for (int i=0; i<BITSET_SIZE; i++) bits[i] = ~bits[i];
    }

    public void invertTo(BitSet to) {
        for (int i=0; i<BITSET_SIZE; i++) to.bits[i] = ~bits[i];
    }

    public void and(BitSet other) {
        for (int i=0; i<BITSET_SIZE; i++) bits[i] &= other.bits[i];
    }

    public void or(BitSet other) {
        for (int i=0; i<BITSET_SIZE; i++) bits[i] |= other.bits[i];
    }

    public void copy(BitSet other) {
        for (int i=0; i<BITSET_SIZE; i++) bits[i] = other.bits[i];
    }

    public int numOn() {
        int num = 0;
        for (int i=0; i<SINGLE_BYTE_SIZE; i++) {
            if (at(i)) num++;
        }
        return num;
    }

    static int bit(int pos){
        return 1 << (pos % SINGLE_BYTE_SIZE);
    }

    private static int log2(int n){
        int log = 0;
        while ((n >>>= 1) != 0) log++;
        return log;
    }

}
