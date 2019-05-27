package com.sun.hotspot.igv.filter;

import com.sun.hotspot.igv.graph.Diagram;

public class NullScriptEngine implements ScriptEngineAbstraction {

    public boolean initialize(String jsHelperText) {
        return true;
    }

    public void execute(Diagram d, String code) {
    }
}
