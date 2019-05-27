package com.sun.xml.internal.bind.v2.model.core;

import com.sun.xml.internal.bind.v2.runtime.IllegalAnnotationException;

/**
 * listen to static errors found during building a JAXB model from a set of classes.
 * Implemented by the client of {@link com.sun.xml.internal.bind.v2.model.impl.ModelBuilder}.
 *
 * <p>
 * All the static errors have to be reported while constructing a
 * model, not when a model is used (IOW, until the {@link com.sun.xml.internal.bind.v2.model.impl.ModelBuilder#link} completes.
 * Internally, {@link com.sun.xml.internal.bind.v2.model.impl.ModelBuilder} wraps an {@link ErrorHandler} and all the model
 * components should report errors through it.
 *
 * <p>
 * {@link IllegalAnnotationException} is a checked exception to remind
 * the model classes to report it rather than to throw it.
 */
public interface ErrorHandler {
    /**
     * Receives a notification for an error in the annotated code.
     */
    void error( IllegalAnnotationException e );
}
