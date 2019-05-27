package com.sun.codemodel.internal;

/**
 * This helps enable whether the JDefinedClass is a Class or Interface or
 * AnnotationTypeDeclaration or Enum
 */
public final class ClassType {

    /**
     * The keyword used to declare this type.
     */
    final String declarationToken;

    private ClassType(String token) {
        this.declarationToken = token;
    }

    public static final ClassType CLASS = new ClassType("class");
    public static final ClassType INTERFACE = new ClassType("interface");
    public static final ClassType ANNOTATION_TYPE_DECL = new ClassType("@interface");
    public static final ClassType ENUM = new ClassType("enum");
}
