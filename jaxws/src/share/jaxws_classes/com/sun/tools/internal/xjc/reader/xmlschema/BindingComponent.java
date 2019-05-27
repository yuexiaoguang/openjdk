package com.sun.tools.internal.xjc.reader.xmlschema;

import com.sun.tools.internal.xjc.reader.Ring;

/**
 * Component accessible from {@link Ring}.
 */
public abstract class BindingComponent {
    protected BindingComponent() {
        Ring.add(this);
    }

//
//
// Accessor to common components.
//
//

    protected final ErrorReporter getErrorReporter() {
        return Ring.get(ErrorReporter.class);
    }
    protected final ClassSelector getClassSelector() {
        return Ring.get(ClassSelector.class);
    }
}
