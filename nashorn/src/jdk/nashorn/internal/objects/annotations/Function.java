package jdk.nashorn.internal.objects.annotations;

import static jdk.nashorn.internal.objects.annotations.Attribute.DEFAULT_ATTRIBUTES;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation to specify a JavaScript "function" property.
 * Note that -1 means varargs. So, -2 is used as invalid arity.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Function {
    /**
     * Name of the property. If empty, the name is inferred.
     */
    public String name() default "";

    /**
     * Attribute flags for this function.
     */
    public int attributes() default DEFAULT_ATTRIBUTES;

    /**
     * The arity of the function. By default computed from the method signature
     */
    public int arity() default -2;

    /**
     * where this function lives
     */
    public Where where() default Where.PROTOTYPE;
}
