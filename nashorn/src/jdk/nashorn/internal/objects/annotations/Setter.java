package jdk.nashorn.internal.objects.annotations;

import static jdk.nashorn.internal.objects.annotations.Attribute.DEFAULT_ATTRIBUTES;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation to specify the setter method for a JavaScript "data" property.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Setter {
    /**
     * Name of the script property. If empty, the name is inferred.
     */
    public String name() default "";

    /**
     * Attribute flags for this setter.
     */
    public int attributes() default DEFAULT_ATTRIBUTES;

    /**
     * Where this setter lives?
     */
    public Where where() default Where.INSTANCE;
}
