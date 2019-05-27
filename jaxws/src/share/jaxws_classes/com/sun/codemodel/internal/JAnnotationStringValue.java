package com.sun.codemodel.internal;

/**
 * Captures the value of the annotation.
 */
final class JAnnotationStringValue extends JAnnotationValue {

    /**
     * The value of the Annotation member
     */
    private final JExpression value;

    JAnnotationStringValue(JExpression value) {
        this.value = value;
    }

    public void generate(JFormatter f) {
        f.g(value);
    }
}
