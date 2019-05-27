package com.sun.tools.internal.ws.processor.modeler.annotation;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.type.TypeMirror;

public interface TypeMoniker {

    public TypeMirror create(ProcessingEnvironment apEnv);
}
