package com.sun.hotspot.igv.filter;

import com.sun.hotspot.igv.graph.Diagram;

public interface ScriptEngineAbstraction {

    public boolean initialize(String jsHelperText);

    public void execute(Diagram d, String code);
}
