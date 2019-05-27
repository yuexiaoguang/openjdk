package com.sun.codemodel.internal;

/**
 * Indicates that the class is already created.
 */
public class JClassAlreadyExistsException extends Exception {

        private static final long serialVersionUID = 1L;

        private final JDefinedClass existing;

    public JClassAlreadyExistsException( JDefinedClass _existing ) {
        this.existing = _existing;
    }

    /**
     * Gets a reference to the existing {@link JDefinedClass}.
     *
     * @return
     *      This method always return non-null valid object.
     */
    public JDefinedClass getExistingClass() {
        return existing;
    }
}
