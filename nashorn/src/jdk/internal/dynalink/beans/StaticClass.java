package jdk.internal.dynalink.beans;

import java.io.Serializable;

/**
 * Object that represents the static facet of a class (its static methods, properties, and fields, as well as
 * construction of instances using "dyn:new"). Objects of this class are recognized by the {@link BeansLinker} as being
 * special, and operations on them will be linked against the represented class' static facet. The "class" synthetic
 * property is additionally recognized and returns the Java {@link Class} object, as per {@link #getRepresentedClass()}
 * method. Conversely, {@link Class} objects exposed through {@link BeansLinker} expose the "static" synthetic property
 * which returns an instance of this class.
 */
public class StaticClass implements Serializable {
    private static final ClassValue<StaticClass> staticClasses = new ClassValue<StaticClass>() {
        @Override
        protected StaticClass computeValue(Class<?> type) {
            return new StaticClass(type);
        }
    };

    private static final long serialVersionUID = 1L;

    private final Class<?> clazz;

    /*private*/ StaticClass(Class<?> clazz) {
        clazz.getClass(); // NPE check
        this.clazz = clazz;
    }

    /**
     * Retrieves the {@link StaticClass} instance for the specified class.
     * @param clazz the class for which the static facet is requested.
     * @return the {@link StaticClass} instance representing the specified class.
     */
    public static StaticClass forClass(Class<?> clazz) {
        return staticClasses.get(clazz);
    }

    /**
     * Returns the represented Java class.
     * @return the represented Java class.
     */
    public Class<?> getRepresentedClass() {
        return clazz;
    }

    @Override
    public String toString() {
        return "JavaClassStatics[" + clazz.getName() + "]";
    }

    private Object readResolve() {
        return forClass(clazz);
    }
}
