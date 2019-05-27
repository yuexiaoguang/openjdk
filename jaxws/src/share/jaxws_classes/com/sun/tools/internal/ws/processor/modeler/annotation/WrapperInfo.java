package com.sun.tools.internal.ws.processor.modeler.annotation;

import java.util.ArrayList;
import java.util.List;

public class WrapperInfo {
    public String wrapperName;

    /** Creates a new instance of FaultInfo */
    public WrapperInfo() {
    }
    public WrapperInfo(String wrapperName) {
        this.wrapperName = wrapperName;
    }

    public void setWrapperName(String wrapperName) {
        this.wrapperName = wrapperName;
    }

    public String getWrapperName() {
        return wrapperName;
    }

}
