package com.sun.hotspot.igv.util;

import org.netbeans.api.visual.action.WidgetAction.WidgetMouseEvent;
import org.netbeans.api.visual.widget.Widget;

public interface DoubleClickHandler {

    public void handleDoubleClick(Widget w, WidgetMouseEvent e);
}
