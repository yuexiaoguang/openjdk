package com.sun.hotspot.igv.layout;

import java.awt.Dimension;
import java.awt.Point;

public interface Vertex extends Comparable<Vertex> {

    public Cluster getCluster();

    public Dimension getSize();

    public Point getPosition();

    public void setPosition(Point p);

    public boolean isRoot();
}
