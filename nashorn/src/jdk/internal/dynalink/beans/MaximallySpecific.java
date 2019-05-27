package jdk.internal.dynalink.beans;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodType;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import jdk.internal.dynalink.linker.ConversionComparator.Comparison;
import jdk.internal.dynalink.linker.LinkerServices;
import jdk.internal.dynalink.support.TypeUtilities;

/**
 * Utility class that encapsulates the algorithm for choosing the maximally specific methods.
 */
class MaximallySpecific {
    /**
     * Given a list of methods, returns a list of maximally specific methods.
     *
     * @param methods the list of methods
     * @param varArgs whether to assume the methods are varargs
     * @return the list of maximally specific methods.
     */
    static List<SingleDynamicMethod> getMaximallySpecificMethods(List<SingleDynamicMethod> methods, boolean varArgs) {
        return getMaximallySpecificSingleDynamicMethods(methods, varArgs, null, null);
    }

    private abstract static class MethodTypeGetter<T> {
        abstract MethodType getMethodType(T t);
    }

    private static final MethodTypeGetter<MethodHandle> METHOD_HANDLE_TYPE_GETTER =
            new MethodTypeGetter<MethodHandle>() {
        @Override
        MethodType getMethodType(MethodHandle t) {
            return t.type();
        }
    };

    private static final MethodTypeGetter<SingleDynamicMethod> DYNAMIC_METHOD_TYPE_GETTER =
            new MethodTypeGetter<SingleDynamicMethod>() {
        @Override
        MethodType getMethodType(SingleDynamicMethod t) {
            return t.getMethodType();
        }
    };

     /**
      * Given a list of methods handles, returns a list of maximally specific methods, applying language-runtime
      * specific conversion preferences.
      *
      * @param methods the list of method handles
      * @param varArgs whether to assume the method handles are varargs
      * @param argTypes concrete argument types for the invocation
      * @return the list of maximally specific method handles.
      */
     static List<MethodHandle> getMaximallySpecificMethodHandles(List<MethodHandle> methods, boolean varArgs,
             Class<?>[] argTypes, LinkerServices ls) {
         return getMaximallySpecificMethods(methods, varArgs, argTypes, ls, METHOD_HANDLE_TYPE_GETTER);
     }

     /**
      * Given a list of methods, returns a list of maximally specific methods, applying language-runtime specific
      * conversion preferences.
      *
      * @param methods the list of methods
      * @param varArgs whether to assume the methods are varargs
      * @param argTypes concrete argument types for the invocation
      * @return the list of maximally specific methods.
      */
     static List<SingleDynamicMethod> getMaximallySpecificSingleDynamicMethods(List<SingleDynamicMethod> methods,
             boolean varArgs, Class<?>[] argTypes, LinkerServices ls) {
         return getMaximallySpecificMethods(methods, varArgs, argTypes, ls, DYNAMIC_METHOD_TYPE_GETTER);
     }

    /**
     * Given a list of methods, returns a list of maximally specific methods, applying language-runtime specific
     * conversion preferences.
     *
     * @param methods the list of methods
     * @param varArgs whether to assume the methods are varargs
     * @param argTypes concrete argument types for the invocation
     * @return the list of maximally specific methods.
     */
    private static <T> List<T> getMaximallySpecificMethods(List<T> methods, boolean varArgs,
            Class<?>[] argTypes, LinkerServices ls, MethodTypeGetter<T> methodTypeGetter) {
        if(methods.size() < 2) {
            return methods;
        }
        final LinkedList<T> maximals = new LinkedList<>();
        for(T m: methods) {
            final MethodType methodType = methodTypeGetter.getMethodType(m);
            boolean lessSpecific = false;
            for(Iterator<T> maximal = maximals.iterator(); maximal.hasNext();) {
                final T max = maximal.next();
                switch(isMoreSpecific(methodType, methodTypeGetter.getMethodType(max), varArgs, argTypes, ls)) {
                    case TYPE_1_BETTER: {
                        maximal.remove();
                        break;
                    }
                    case TYPE_2_BETTER: {
                        lessSpecific = true;
                        break;
                    }
                    case INDETERMINATE: {
                        // do nothing
                        break;
                    }
                    default: {
                        throw new AssertionError();
                    }
                }
            }
            if(!lessSpecific) {
                maximals.addLast(m);
            }
        }
        return maximals;
    }

    private static Comparison isMoreSpecific(MethodType t1, MethodType t2, boolean varArgs, Class<?>[] argTypes,
            LinkerServices ls) {
        final int pc1 = t1.parameterCount();
        final int pc2 = t2.parameterCount();
        assert varArgs || (pc1 == pc2) && (argTypes == null || argTypes.length == pc1);
        assert (argTypes == null) == (ls == null);
        final int maxPc = Math.max(Math.max(pc1, pc2), argTypes == null ? 0 : argTypes.length);
        boolean t1MoreSpecific = false;
        boolean t2MoreSpecific = false;
        // NOTE: Starting from 1 as overloaded method resolution doesn't depend on 0th element, which is the type of
        // 'this'. We're only dealing with instance methods here, not static methods. Actually, static methods will have
        // a fake 'this' of type StaticClass.
        for(int i = 1; i < maxPc; ++i) {
            final Class<?> c1 = getParameterClass(t1, pc1, i, varArgs);
            final Class<?> c2 = getParameterClass(t2, pc2, i, varArgs);
            if(c1 != c2) {
                final Comparison cmp = compare(c1, c2, argTypes, i, ls);
                if(cmp == Comparison.TYPE_1_BETTER && !t1MoreSpecific) {
                    t1MoreSpecific = true;
                    if(t2MoreSpecific) {
                        return Comparison.INDETERMINATE;
                    }
                }
                if(cmp == Comparison.TYPE_2_BETTER && !t2MoreSpecific) {
                    t2MoreSpecific = true;
                    if(t1MoreSpecific) {
                        return Comparison.INDETERMINATE;
                    }
                }
            }
        }
        if(t1MoreSpecific) {
            return Comparison.TYPE_1_BETTER;
        } else if(t2MoreSpecific) {
            return Comparison.TYPE_2_BETTER;
        }
        return Comparison.INDETERMINATE;
    }

    private static Comparison compare(Class<?> c1, Class<?> c2, Class<?>[] argTypes, int i, LinkerServices cmp) {
        if(cmp != null) {
            final Comparison c = cmp.compareConversion(argTypes[i], c1, c2);
            if(c != Comparison.INDETERMINATE) {
                return c;
            }
        }
        if(TypeUtilities.isSubtype(c1, c2)) {
            return Comparison.TYPE_1_BETTER;
        } if(TypeUtilities.isSubtype(c2, c1)) {
            return Comparison.TYPE_2_BETTER;
        }
        return Comparison.INDETERMINATE;
    }

    private static Class<?> getParameterClass(MethodType t, int l, int i, boolean varArgs) {
        return varArgs && i >= l - 1 ? t.parameterType(l - 1).getComponentType() : t.parameterType(i);
    }
}
