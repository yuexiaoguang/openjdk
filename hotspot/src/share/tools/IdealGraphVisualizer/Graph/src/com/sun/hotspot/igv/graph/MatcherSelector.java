package com.sun.hotspot.igv.graph;

import com.sun.hotspot.igv.data.Properties;
import com.sun.hotspot.igv.data.Properties.PropertyMatcher;
import java.util.List;

public class MatcherSelector implements Selector {

    private PropertyMatcher matcher;

    public MatcherSelector(PropertyMatcher matcher) {
        this.matcher = matcher;
    }

    public List<Figure> selected(Diagram d) {
        Properties.PropertySelector<Figure> selector = new Properties.PropertySelector<Figure>(d.getFigures());
        List<Figure> list = selector.selectMultiple(matcher);
        return list;
    }
}
