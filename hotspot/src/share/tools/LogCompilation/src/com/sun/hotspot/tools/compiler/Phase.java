package com.sun.hotspot.tools.compiler;

import java.io.PrintStream;

public class Phase extends BasicLogEvent {

    private final int startNodes;
    private int endNodes;
    private final int startLiveNodes;
    private int endLiveNodes;

    Phase(String n, double s, int nodes, int live) {
        super(s, n);
        startNodes = nodes;
        startLiveNodes = live;
    }

    int getNodes() {
        return getEndNodes() - getStartNodes();
    }

    void setEndNodes(int n) {
        endNodes = n;
    }

    public String getName() {
        return getId();
    }

    public int getStartNodes() {
        return startNodes;
    }

    public int getEndNodes() {
        return endNodes;
    }
    /* Number of live nodes added by the phase */
    int getLiveNodes() {
        return getEndLiveNodes() - getStartLiveNodes();
    }

    void setEndLiveNodes(int n) {
        endLiveNodes = n;
    }

    public int getStartLiveNodes() {
        return startLiveNodes;
    }

    public int getEndLiveNodes() {
        return endLiveNodes;
    }

    @Override
    public void print(PrintStream stream) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
