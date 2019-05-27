package com.sun.hotspot.igv.data.services;

import com.sun.hotspot.igv.data.InputGraph;
import com.sun.hotspot.igv.data.InputNode;
import java.util.Set;

public interface InputGraphProvider {

    InputGraph getGraph();

    void setSelectedNodes(Set<InputNode> nodes);
}
