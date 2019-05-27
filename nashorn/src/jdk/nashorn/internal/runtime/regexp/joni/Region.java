package jdk.nashorn.internal.runtime.regexp.joni;

public final class Region {
    static final int REGION_NOTPOS = -1;

    public final int numRegs;
    public final int[]beg;
    public final int[]end;

    public Region(int num) {
        this.numRegs = num;
        this.beg = new int[num];
        this.end = new int[num];
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Region: \n");
        for (int i=0; i<beg.length; i++) sb.append(" " + i + ": (" + beg[i] + "-" + end[i] + ")");
        return sb.toString();
    }

    void clear() {
        for (int i=0; i<beg.length; i++) {
            beg[i] = end[i] = REGION_NOTPOS;
        }
    }
}
