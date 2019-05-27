package com.sun.source.doctree;

import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;

/**
 * A tree node to stand in for a malformed text
 */
@jdk.Exported
public interface ErroneousTree extends TextTree {
    /**
     * Gets a diagnostic object giving details about
     * the reason the body text is in error.
     *
     * @return a diagnostic
     */
    Diagnostic<JavaFileObject> getDiagnostic();
}
