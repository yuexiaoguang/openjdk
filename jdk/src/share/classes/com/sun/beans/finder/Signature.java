package com.sun.beans.finder;

/**
 * This class is designed to be a key of a cache
 * of constructors or methods.
 */
final class Signature {
    private final Class<?> type;
    private final String name;
    private final Class<?>[] args;

    private volatile int code;

    /**
     * Constructs signature for constructor.
     *
     * @param type  the class that contains constructor
     * @param args  the types of constructor's parameters
     */
    Signature(Class<?> type, Class<?>[] args) {
        this(type, null, args);
    }

    /**
     * Constructs signature for method.
     *
     * @param type  the class that contains method
     * @param name  the name of the method
     * @param args  the types of method's parameters
     */
    Signature(Class<?> type, String name, Class<?>[] args) {
        this.type = type;
        this.name = name;
        this.args = args;
    }

    Class<?> getType() {
        return this.type;
    }

    String getName() {
        return this.name;
    }

    Class<?>[] getArgs() {
        return this.args;
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param object  the reference object with which to compare
     * @return {@code true} if this object is the same as the
     *         {@code object} argument, {@code false} otherwise
     * @see #hashCode()
     */
    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object instanceof Signature) {
            Signature signature = (Signature) object;
            return isEqual(signature.type, this.type)
                && isEqual(signature.name, this.name)
                && isEqual(signature.args, this.args);
        }
        return false;
    }

    /**
     * Indicates whether some object is "equal to" another one.
     * This method supports {@code null} values.
     *
     * @param obj1  the first reference object that will compared
     * @param obj2  the second reference object that will compared
     * @return {@code true} if first object is the same as the second object,
     *         {@code false} otherwise
     */
    private static boolean isEqual(Object obj1, Object obj2) {
        return (obj1 == null)
                ? obj2 == null
                : obj1.equals(obj2);
    }

    /**
     * Indicates whether some array is "equal to" another one.
     * This method supports {@code null} values.
     *
     * @param args1 the first reference array that will compared
     * @param args2 the second reference array that will compared
     * @return {@code true} if first array is the same as the second array,
     *         {@code false} otherwise
     */
    private static boolean isEqual(Class<?>[] args1, Class<?>[] args2) {
        if ((args1 == null) || (args2 == null)) {
            return args1 == args2;
        }
        if (args1.length != args2.length) {
            return false;
        }
        for (int i = 0; i < args1.length; i++) {
            if (!isEqual(args1[i], args2[i])) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns a hash code value for the object.
     * This method is supported for the benefit of hashtables
     * such as {@link java.util.HashMap} or {@link java.util.HashSet}.
     * Hash code computed using algorithm
     * suggested in Effective Java, Item 8.
     *
     * @return a hash code value for this object
     * @see #equals(Object)
     */
    @Override
    public int hashCode() {
        if (this.code == 0) {
            int code = 17;
            code = addHashCode(code, this.type);
            code = addHashCode(code, this.name);

            if (this.args != null) {
                for (Class<?> arg : this.args) {
                    code = addHashCode(code, arg);
                }
            }
            this.code = code;
        }
        return this.code;
    }

    /**
     * Adds hash code value if specified object.
     * This is a part of the algorithm
     * suggested in Effective Java, Item 8.
     *
     * @param code    current hash code value
     * @param object  object that updates hash code value
     * @return updated hash code value
     * @see #hashCode()
     */
    private static int addHashCode(int code, Object object) {
        code *= 37;
        return (object != null)
                ? code + object.hashCode()
                : code;
    }
}
