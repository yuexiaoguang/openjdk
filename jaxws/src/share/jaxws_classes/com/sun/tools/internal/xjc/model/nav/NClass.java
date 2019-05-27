package com.sun.tools.internal.xjc.model.nav;

import com.sun.codemodel.internal.JClass;
import com.sun.tools.internal.xjc.outline.Aspect;
import com.sun.tools.internal.xjc.outline.Outline;

public interface NClass extends NType {
    JClass toType(Outline o, Aspect aspect);

    boolean isAbstract();
}
