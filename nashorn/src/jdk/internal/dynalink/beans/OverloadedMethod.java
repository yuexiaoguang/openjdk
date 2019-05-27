package jdk.internal.dynalink.beans;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import jdk.internal.dynalink.linker.LinkerServices;
import jdk.internal.dynalink.support.Lookup;

/**
 * Represents a subset of overloaded methods for a certain method name on a certain class. It can be either a fixarg or
 * a vararg subset depending on the subclass. The method is for a fixed number of arguments though (as it is generated
 * for a concrete call site). As such, all methods in the subset can be invoked with the specified number of arguments
 * (exactly matching for fixargs, or having less than or equal fixed arguments, for varargs).
 */
class OverloadedMethod {
    private final Map<ClassString, MethodHandle> argTypesToMethods = new ConcurrentHashMap<>();
    private final OverloadedDynamicMethod parent;
    private final MethodType callSiteType;
    private final MethodHandle invoker;
    private final LinkerServices linkerServices;
    private final ArrayList<MethodHandle> fixArgMethods;
    private final ArrayList<MethodHandle> varArgMethods;

    OverloadedMethod(List<MethodHandle> methodHandles, OverloadedDynamicMethod parent, MethodType callSiteType,
            LinkerServices linkerServices) {
        this.parent = parent;
        this.callSiteType = callSiteType;
        this.linkerServices = linkerServices;

        fixArgMethods = new ArrayList<>(methodHandles.size());
        varArgMethods = new ArrayList<>(methodHandles.size());
        final int argNum = callSiteType.parameterCount();
        for(MethodHandle mh: methodHandles) {
            if(mh.isVarargsCollector()) {
                final MethodHandle asFixed = mh.asFixedArity();
                if(argNum == asFixed.type().parameterCount()) {
                    fixArgMethods.add(asFixed);
                }
                varArgMethods.add(mh);
            } else {
                fixArgMethods.add(mh);
            }
        }
        fixArgMethods.trimToSize();
        varArgMethods.trimToSize();

        final MethodHandle bound = SELECT_METHOD.bindTo(this);
        final MethodHandle collecting = SingleDynamicMethod.collectArguments(bound, argNum).asType(
                callSiteType.changeReturnType(MethodHandle.class));
        invoker = MethodHandles.foldArguments(MethodHandles.exactInvoker(callSiteType), collecting);
    }

    MethodHandle getInvoker() {
        return invoker;
    }

    private static final MethodHandle SELECT_METHOD = Lookup.findOwnSpecial(MethodHandles.lookup(), "selectMethod",
            MethodHandle.class, Object[].class);

    @SuppressWarnings("unused")
    private MethodHandle selectMethod(Object[] args) throws NoSuchMethodException {
        final Class<?>[] argTypes = new Class[args.length];
        for(int i = 0; i < argTypes.length; ++i) {
            final Object arg = args[i];
            argTypes[i] = arg == null ? ClassString.NULL_CLASS : arg.getClass();
        }
        final ClassString classString = new ClassString(argTypes);
        MethodHandle method = argTypesToMethods.get(classString);
        if(method == null) {
            List<MethodHandle> methods = classString.getMaximallySpecifics(fixArgMethods, linkerServices, false);
            if(methods.isEmpty()) {
                methods = classString.getMaximallySpecifics(varArgMethods, linkerServices, true);
            }
            switch(methods.size()) {
                case 0: {
                    method = getNoSuchMethodThrower(argTypes);
                    break;
                }
                case 1: {
                    method = SingleDynamicMethod.getInvocation(methods.get(0), callSiteType, linkerServices);
                    break;
                }
                default: {
                    // This is unfortunate - invocation time ambiguity. We can still save the day if
                    method = getAmbiguousMethodThrower(argTypes, methods);
                    break;
                }
            }
            // Avoid keeping references to unrelated classes; this ruins the performance a bit, but avoids class loader
            // memory leaks.
            if(classString.isVisibleFrom(parent.getClassLoader())) {
                argTypesToMethods.put(classString, method);
            }
        }
        return method;
    }

    private MethodHandle getNoSuchMethodThrower(Class<?>[] argTypes) {
        return adaptThrower(MethodHandles.insertArguments(THROW_NO_SUCH_METHOD, 0, this, argTypes));
    }

    private static final MethodHandle THROW_NO_SUCH_METHOD = Lookup.findOwnSpecial(MethodHandles.lookup(),
            "throwNoSuchMethod", void.class, Class[].class);

    @SuppressWarnings("unused")
    private void throwNoSuchMethod(Class<?>[] argTypes) throws NoSuchMethodException {
        if(varArgMethods.isEmpty()) {
            throw new NoSuchMethodException("None of the fixed arity signatures " + getSignatureList(fixArgMethods) +
                    " of method " + parent.getName() + " match the argument types " + argTypesString(argTypes));
        }
        throw new NoSuchMethodException("None of the fixed arity signatures " + getSignatureList(fixArgMethods) +
                " or the variable arity signatures " + getSignatureList(varArgMethods) + " of the method " +
                parent.getName() + " match the argument types " + argTypesString(argTypes));
    }

    private MethodHandle getAmbiguousMethodThrower(Class<?>[] argTypes, List<MethodHandle> methods) {
        return adaptThrower(MethodHandles.insertArguments(THROW_AMBIGUOUS_METHOD, 0, this, argTypes, methods));
    }

    private MethodHandle adaptThrower(MethodHandle rawThrower) {
        return MethodHandles.dropArguments(rawThrower, 0, callSiteType.parameterList()).asType(callSiteType);
    }

    private static final MethodHandle THROW_AMBIGUOUS_METHOD = Lookup.findOwnSpecial(MethodHandles.lookup(),
            "throwAmbiguousMethod", void.class, Class[].class, List.class);

    @SuppressWarnings("unused")
    private void throwAmbiguousMethod(Class<?>[] argTypes, List<MethodHandle> methods) throws NoSuchMethodException {
        final String arity = methods.get(0).isVarargsCollector() ? "variable" : "fixed";
        throw new NoSuchMethodException("Can't unambiguously select between " + arity + " arity signatures " +
                getSignatureList(methods) + " of the method " + parent.getName() + " for argument types " +
                argTypesString(argTypes));
    }

    private static String argTypesString(Class<?>[] classes) {
        final StringBuilder b = new StringBuilder().append('[');
        appendTypes(b, classes, false);
        return b.append(']').toString();
    }

    private static String getSignatureList(List<MethodHandle> methods) {
        final StringBuilder b = new StringBuilder().append('[');
        final Iterator<MethodHandle> it = methods.iterator();
        if(it.hasNext()) {
            appendSig(b, it.next());
            while(it.hasNext()) {
                appendSig(b.append(", "), it.next());
            }
        }
        return b.append(']').toString();
    }

    private static void appendSig(StringBuilder b, MethodHandle m) {
        b.append('(');
        appendTypes(b, m.type().parameterArray(), m.isVarargsCollector());
        b.append(')');
    }

    private static void appendTypes(StringBuilder b, Class<?>[] classes, boolean varArg) {
        final int l = classes.length;
        if(!varArg) {
            if(l > 1) {
                b.append(classes[1].getCanonicalName());
                for(int i = 2; i < l; ++i) {
                    b.append(", ").append(classes[i].getCanonicalName());
                }
            }
        } else {
            for(int i = 1; i < l - 1; ++i) {
                b.append(classes[i].getCanonicalName()).append(", ");
            }
            b.append(classes[l - 1].getComponentType().getCanonicalName()).append("...");
        }
    }
}
