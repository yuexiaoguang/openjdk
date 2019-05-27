package com.sun.hotspot.igv.graph;

import java.util.ArrayList;
import java.util.List;

public class PredecessorSelector implements Selector {

    private Selector innerSelector;

    public PredecessorSelector(Selector innerSelector) {
        this.innerSelector = innerSelector;
    }

    public List<Figure> selected(Diagram d) {
        List<Figure> inner = innerSelector.selected(d);
        List<Figure> result = new ArrayList<Figure>();
        for (Figure f : d.getFigures()) {
            boolean saved = false;
            for (Figure f2 : f.getSuccessors()) {
                if (inner.contains(f2)) {
                    saved = true;
                }
            }

            if (saved) {
                result.add(f);
            }
        }

        return result;
    }
}
