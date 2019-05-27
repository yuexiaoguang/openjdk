package com.sun.hotspot.igv.layout;

import java.awt.Rectangle;
import java.util.Set;

public interface Cluster extends Comparable<Cluster> {

    public Cluster getOuter();

    public void setBounds(Rectangle r);

    public Set<? extends Cluster> getSuccessors();

    public Set<? extends Cluster> getPredecessors();
}
