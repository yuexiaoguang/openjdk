package com.sun.tools.internal.ws.processor.generator;

import com.sun.tools.internal.ws.processor.ProcessorException;

public class GeneratorException extends ProcessorException {

    public GeneratorException(String key, Object... args) {
        super(key, args);
    }

    public GeneratorException(Throwable throwable) {
        super(throwable);
    }

    public String getDefaultResourceBundleName() {
        return "com.sun.tools.internal.ws.resources.generator";
    }
}
