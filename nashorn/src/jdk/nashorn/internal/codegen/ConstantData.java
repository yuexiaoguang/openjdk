package jdk.nashorn.internal.codegen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Manages constants needed by code generation.  Objects are maintained in an
 * interning maps to remove duplicates.
 */
class ConstantData {
    /** Constant table. */
    final List<Object> constants;

    /** Constant table string interning map. */
    final Map<String, Integer> stringMap;

    /** Constant table object interning map. */
    final Map<Object, Integer> objectMap;

    private static class ArrayWrapper {
        private final Object array;
        private final int    hashCode;

        public ArrayWrapper(final Object array) {
            this.array    = array;
            this.hashCode = calcHashCode();
        }

        /**
         * Calculate a shallow hashcode for the array.
         * @return Hashcode with elements factored in.
         */
        private int calcHashCode() {
            final Class<?> cls = array.getClass();

            if (cls == Object[].class) {
                return Arrays.hashCode((Object[])array);
            } else if (cls == double[].class) {
                return Arrays.hashCode((double[])array);
            } if (cls == long[].class) {
                return Arrays.hashCode((long[])array);
            } if (cls == int[].class) {
                return Arrays.hashCode((int[])array);
            }

            throw new AssertionError("ConstantData doesn't support " + cls);
        }

        @Override
        public boolean equals(final Object other) {
            if (!(other instanceof ArrayWrapper)) {
                return false;
            }

            final Object otherArray = ((ArrayWrapper)other).array;

            if (array == otherArray) {
                return true;
            }

            final Class<?> cls = array.getClass();

            if (cls == otherArray.getClass()) {
                if (cls == Object[].class) {
                    return Arrays.equals((Object[])array, (Object[])otherArray);
                } else if (cls == double[].class) {
                    return Arrays.equals((double[])array, (double[])otherArray);
                } else if (cls == long[].class) {
                    return Arrays.equals((long[])array, (long[])otherArray);
                } else if (cls == int[].class) {
                    return Arrays.equals((int[])array, (int[])otherArray);
                }
            }

            return false;
        }

        @Override
        public int hashCode() {
            return hashCode;
        }
    }

    /**
     * Constructor
     */
    ConstantData() {
        this.constants = new ArrayList<>();
        this.stringMap = new HashMap<>();
        this.objectMap = new HashMap<>();
    }

    /**
     * Add a string to the constant data
     *
     * @param string the string to add
     * @return the index in the constant pool that the string was given
     */
    public int add(final String string) {
        final Integer value = stringMap.get(string);

        if (value != null) {
            return value.intValue();
        }

        constants.add(string);
        final int index = constants.size() - 1;
        stringMap.put(string, index);

        return index;
    }

    /**
     * Add an object to the constant data
     *
     * @param object the string to add
     * @return the index in the constant pool that the object was given
     */
    public int add(final Object object) {
        final Object  entry = object.getClass().isArray() ? new ArrayWrapper(object) : object;
        final Integer value = objectMap.get(entry);

        if (value != null) {
            return value.intValue();
        }

        constants.add(object);
        final int index = constants.size() - 1;
        objectMap.put(entry, index);

        return index;
    }

    Object[] toArray() {
        return constants.toArray();
    }
}
