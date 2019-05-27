package jdk.internal.dynalink.beans;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodType;
import java.util.LinkedList;
import java.util.List;
import jdk.internal.dynalink.linker.LinkerServices;
import jdk.internal.dynalink.support.Guards;
import jdk.internal.dynalink.support.TypeUtilities;

final class ClassString {
    /**
     * An anonymous inner class used solely to represent the "type" of null values for method applicability checking.
     */
    static final Class<?> NULL_CLASS = (new Object() { /* Intentionally empty */ }).getClass();

    private final Class<?>[] classes;
    private int hashCode;

    ClassString(Class<?>[] classes) {
        this.classes = classes;
    }

    ClassString(MethodType type) {
        this(type.parameterArray());
    }

    @Override
    public boolean equals(Object other) {
        if(!(other instanceof ClassString)) {
            return false;
        }
        final Class<?>[] otherClasses = ((ClassString)other).classes;
        if(otherClasses.length != classes.length) {
            return false;
        }
        for(int i = 0; i < otherClasses.length; ++i) {
            if(otherClasses[i] != classes[i]) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        if(hashCode == 0) {
            int h = 0;
            for(int i = 0; i < classes.length; ++i) {
                h ^= classes[i].hashCode();
            }
            hashCode = h;
        }
        return hashCode;
    }

    boolean isVisibleFrom(final ClassLoader classLoader) {
        for(int i = 0; i < classes.length; ++i) {
            if(!Guards.canReferenceDirectly(classLoader, classes[i].getClassLoader())) {
                return false;
            }
        }
        return true;
    }

    List<MethodHandle> getMaximallySpecifics(List<MethodHandle> methods, LinkerServices linkerServices, boolean varArg) {
        return MaximallySpecific.getMaximallySpecificMethodHandles(getApplicables(methods, linkerServices, varArg),
                varArg, classes, linkerServices);
    }

    /**
     * Returns all methods that are applicable to actual parameter classes represented by this ClassString object.
     */
    LinkedList<MethodHandle> getApplicables(List<MethodHandle> methods, LinkerServices linkerServices, boolean varArg) {
        final LinkedList<MethodHandle> list = new LinkedList<>();
        for(final MethodHandle member: methods) {
            if(isApplicable(member, linkerServices, varArg)) {
                list.add(member);
            }
        }
        return list;
    }

    /**
     * Returns true if the supplied method is applicable to actual parameter classes represented by this ClassString
     * object.
     *
     */
    private boolean isApplicable(MethodHandle method, LinkerServices linkerServices, boolean varArg) {
        final Class<?>[] formalTypes = method.type().parameterArray();
        final int cl = classes.length;
        final int fl = formalTypes.length - (varArg ? 1 : 0);
        if(varArg) {
            if(cl < fl) {
                return false;
            }
        } else {
            if(cl != fl) {
                return false;
            }
        }
        // Starting from 1 as we ignore the receiver type
        for(int i = 1; i < fl; ++i) {
            if(!canConvert(linkerServices, classes[i], formalTypes[i])) {
                return false;
            }
        }
        if(varArg) {
            final Class<?> varArgType = formalTypes[fl].getComponentType();
            for(int i = fl; i < cl; ++i) {
                if(!canConvert(linkerServices, classes[i], varArgType)) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean canConvert(LinkerServices ls, Class<?> from, Class<?> to) {
        if(from == NULL_CLASS) {
            return !to.isPrimitive();
        }
        return ls == null ? TypeUtilities.isMethodInvocationConvertible(from, to) : ls.canConvert(from, to);
    }
}
