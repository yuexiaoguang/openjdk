package jdk.nashorn.internal.objects.annotations;

import static jdk.nashorn.internal.objects.annotations.Attribute.DEFAULT_ATTRIBUTES;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation to specify a JavaScript "data" property.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Property {
    /**
     * Name of the script property. If empty, the name is inferred.
     */
    public String name() default "";

    /**
     * Attribute flags for this function.
     */
    public int attributes() default DEFAULT_ATTRIBUTES;

    /**
     * Initialize this property with the object of given class.
     */
    public String clazz() default "";

    /**
     * Where this property lives?
     */
    public Where where() default Where.INSTANCE;
}
