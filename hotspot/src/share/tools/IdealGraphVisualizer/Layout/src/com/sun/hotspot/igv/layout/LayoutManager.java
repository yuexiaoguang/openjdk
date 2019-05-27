package com.sun.hotspot.igv.layout;

import java.util.Set;

public interface LayoutManager {

    public void doLayout(LayoutGraph graph);

    public void doLayout(LayoutGraph graph, Set<? extends Vertex> firstLayerHint, Set<? extends Vertex> lastLayerHint, Set<? extends Link> importantLinks);

    public void doRouting(LayoutGraph graph);
}
