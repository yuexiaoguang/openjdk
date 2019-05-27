package com.sun.hotspot.igv.graph;

import java.util.List;

public interface Selector {

    List<Figure> selected(Diagram d);
}
