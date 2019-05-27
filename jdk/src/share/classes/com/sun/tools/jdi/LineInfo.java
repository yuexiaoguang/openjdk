package com.sun.tools.jdi;

import com.sun.jdi.*;

interface LineInfo {

    String liStratum();

    int liLineNumber();

    String liSourceName() throws AbsentInformationException;

    String liSourcePath() throws AbsentInformationException;
}
