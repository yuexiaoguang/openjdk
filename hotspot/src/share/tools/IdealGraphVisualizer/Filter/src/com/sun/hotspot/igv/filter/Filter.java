package com.sun.hotspot.igv.filter;

import com.sun.hotspot.igv.data.ChangedEvent;
import com.sun.hotspot.igv.data.ChangedEventProvider;
import com.sun.hotspot.igv.data.Properties;
import com.sun.hotspot.igv.graph.Diagram;
import org.openide.cookies.OpenCookie;

public interface Filter extends Properties.Provider, ChangedEventProvider<Filter> {

    public String getName();

    public void apply(Diagram d);

    OpenCookie getEditor();

    ChangedEvent<Filter> getChangedEvent();
}
