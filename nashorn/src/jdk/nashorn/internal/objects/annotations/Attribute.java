package jdk.nashorn.internal.objects.annotations;

/**
 * Attributes for JavaScript properties. The negative logic "NOT_xxx" is because the
 * common case is to be writable, enumerable and configurable
 */
public interface Attribute {
    /** flag for non writable objects */
    public static final int NOT_WRITABLE     = jdk.nashorn.internal.runtime.Property.NOT_WRITABLE;

    /** flag for non enumerable objects */
    public static final int NOT_ENUMERABLE   = jdk.nashorn.internal.runtime.Property.NOT_ENUMERABLE;

    /** flag for non configurable objects */
    public static final int NOT_CONFIGURABLE = jdk.nashorn.internal.runtime.Property.NOT_CONFIGURABLE;

    /** read-only, non-configurable property */
    public static final int CONSTANT = NOT_WRITABLE | NOT_CONFIGURABLE;

    /** non-enumerable, read-only, non-configurable property */
    public static final int NON_ENUMERABLE_CONSTANT = NOT_ENUMERABLE | CONSTANT;

    /** by default properties are writable, enumerable and configurable */
    public static final int DEFAULT_ATTRIBUTES = 0;
}
