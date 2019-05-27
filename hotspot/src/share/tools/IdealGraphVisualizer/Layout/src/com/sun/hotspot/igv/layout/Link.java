package com.sun.hotspot.igv.layout;

import java.awt.Point;
import java.util.List;

public interface Link {

    public Port getFrom();

    public Port getTo();

    public List<Point> getControlPoints();

    public void setControlPoints(List<Point> list);
}
