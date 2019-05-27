package sun.java2d.jules;

import sun.java2d.xr.GrowableIntArray;

class TileTrapContainer {
    int tileAlpha;
    GrowableIntArray traps;

    public TileTrapContainer(GrowableIntArray traps) {
        this.traps = traps;
    }

    public void setTileAlpha(int tileAlpha) {
        this.tileAlpha = tileAlpha;
    }

    public int getTileAlpha() {
        return tileAlpha;
    }

    public GrowableIntArray getTraps() {
        return traps;
    }
}
