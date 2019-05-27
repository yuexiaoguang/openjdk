package com.sun.hotspot.igv.hierarchicallayout;

import com.sun.hotspot.igv.layout.Link;
import com.sun.hotspot.igv.layout.Port;
import java.awt.Point;
import java.util.List;

public class ClusterEdge implements Link {

    private ClusterNode from;
    private ClusterNode to;
    private List<Point> points;

    public ClusterEdge(ClusterNode from, ClusterNode to) {
        assert from != null;
        assert to != null;
        this.from = from;
        this.to = to;
    }

    public Port getTo() {
        return to.getInputSlot();
    }

    public Port getFrom() {
        return from.getInputSlot();
    }

    public void setControlPoints(List<Point> p) {
        this.points = p;
    }

    public List<Point> getControlPoints() {
        return points;
    }
}
