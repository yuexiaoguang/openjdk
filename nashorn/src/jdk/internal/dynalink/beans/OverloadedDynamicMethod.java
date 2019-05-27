package jdk.internal.dynalink.beans;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import jdk.internal.dynalink.CallSiteDescriptor;
import jdk.internal.dynalink.beans.ApplicableOverloadedMethods.ApplicabilityTest;
import jdk.internal.dynalink.linker.LinkerServices;
import jdk.internal.dynalink.support.TypeUtilities;

/**
 * Represents a group of {@link SingleDynamicMethod} objects that represents all overloads of a particular name (or all
 * constructors) for a particular class. Correctly handles overload resolution, variable arity methods, and caller
 * sensitive methods within the overloads.
 */
class OverloadedDynamicMethod extends DynamicMethod {
    /**
     * Holds a list of all methods.
     */
    private final LinkedList<SingleDynamicMethod> methods;
    private final ClassLoader classLoader;

    /**
     * Creates a new overloaded dynamic method.
     *
     * @param clazz the class this method belongs to
     * @param name the name of the method
     */
    OverloadedDynamicMethod(Class<?> clazz, String name) {
        this(new LinkedList<SingleDynamicMethod>(), clazz.getClassLoader(), getClassAndMethodName(clazz, name));
    }

    private OverloadedDynamicMethod(LinkedList<SingleDynamicMethod> methods, ClassLoader classLoader, String name) {
        super(name);
        this.methods = methods;
        this.classLoader = classLoader;
    }

    @Override
    SingleDynamicMethod getMethodForExactParamTypes(String paramTypes) {
        final LinkedList<SingleDynamicMethod> matchingMethods = new LinkedList<>();
        for(SingleDynamicMethod method: methods) {
            final SingleDynamicMethod matchingMethod = method.getMethodForExactParamTypes(paramTypes);
            if(matchingMethod != null) {
                matchingMethods.add(matchingMethod);
            }
        }
        switch(matchingMethods.size()) {
            case 0: {
                return null;
            }
            case 1: {
                return matchingMethods.getFirst();
            }
            default: {
                throw new BootstrapMethodError("Can't choose among " + matchingMethods + " for argument types "
                        + paramTypes + " for method " + getName());
            }
        }
    }

    @SuppressWarnings("fallthrough")
    @Override
    public MethodHandle getInvocation(final CallSiteDescriptor callSiteDescriptor, final LinkerServices linkerServices) {
        final MethodType callSiteType = callSiteDescriptor.getMethodType();
        // First, find all methods applicable to the call site by subtyping (JLS 15.12.2.2)
        final ApplicableOverloadedMethods subtypingApplicables = getApplicables(callSiteType,
                ApplicableOverloadedMethods.APPLICABLE_BY_SUBTYPING);
        // Next, find all methods applicable by method invocation conversion to the call site (JLS 15.12.2.3).
        final ApplicableOverloadedMethods methodInvocationApplicables = getApplicables(callSiteType,
                ApplicableOverloadedMethods.APPLICABLE_BY_METHOD_INVOCATION_CONVERSION);
        // Finally, find all methods applicable by variable arity invocation. (JLS 15.12.2.4).
        final ApplicableOverloadedMethods variableArityApplicables = getApplicables(callSiteType,
                ApplicableOverloadedMethods.APPLICABLE_BY_VARIABLE_ARITY);

        // Find the methods that are maximally specific based on the call site signature
        List<SingleDynamicMethod> maximallySpecifics = subtypingApplicables.findMaximallySpecificMethods();
        if(maximallySpecifics.isEmpty()) {
            maximallySpecifics = methodInvocationApplicables.findMaximallySpecificMethods();
            if(maximallySpecifics.isEmpty()) {
                maximallySpecifics = variableArityApplicables.findMaximallySpecificMethods();
            }
        }

        // Now, get a list of the rest of the methods; those that are *not* applicable to the call site signature based
        // on JLS rules. As paradoxical as that might sound, we have to consider these for dynamic invocation, as they
        // might match more concrete types passed in invocations. That's why we provisionally call them "invokables".
        // This is typical for very generic signatures at call sites. Typical example: call site specifies
        // (Object, Object), and we have a method whose parameter types are (String, int). None of the JLS applicability
        // rules will trigger, but we must consider the method, as it can be the right match for a concrete invocation.
        @SuppressWarnings({ "unchecked", "rawtypes" })
        final List<SingleDynamicMethod> invokables = (List)methods.clone();
        invokables.removeAll(subtypingApplicables.getMethods());
        invokables.removeAll(methodInvocationApplicables.getMethods());
        invokables.removeAll(variableArityApplicables.getMethods());
        for(final Iterator<SingleDynamicMethod> it = invokables.iterator(); it.hasNext();) {
            final SingleDynamicMethod m = it.next();
            if(!isApplicableDynamically(linkerServices, callSiteType, m)) {
                it.remove();
            }
        }

        // If no additional methods can apply at invocation time, and there's more than one maximally specific method
        // based on call site signature, that is a link-time ambiguity. In a static scenario, javac would report an
        // ambiguity error.
        if(invokables.isEmpty() && maximallySpecifics.size() > 1) {
            throw new BootstrapMethodError("Can't choose among " + maximallySpecifics + " for argument types "
                    + callSiteType);
        }

        // Merge them all.
        invokables.addAll(maximallySpecifics);
        switch(invokables.size()) {
            case 0: {
                // No overloads can ever match the call site type
                return null;
            }
            case 1: {
                // Very lucky, we ended up with a single candidate method handle based on the call site signature; we
                // can link it very simply by delegating to the SingleDynamicMethod.
                invokables.iterator().next().getInvocation(callSiteDescriptor, linkerServices);
            }
            default: {
                // We have more than one candidate. We have no choice but to link to a method that resolves overloads on
                // every invocation (alternatively, we could opportunistically link the one method that resolves for the
                // current arguments, but we'd need to install a fairly complex guard for that and when it'd fail, we'd
                // go back all the way to candidate selection. Note that we're resolving any potential caller sensitive
                // methods here to their handles, as the OverloadedMethod instance is specific to a call site, so it
                // has an already determined Lookup.
                final List<MethodHandle> methodHandles = new ArrayList<>(invokables.size());
                final MethodHandles.Lookup lookup = callSiteDescriptor.getLookup();
                for(SingleDynamicMethod method: invokables) {
                    methodHandles.add(method.getTarget(lookup));
                }
                return new OverloadedMethod(methodHandles, this, callSiteType, linkerServices).getInvoker();
            }
        }

    }

    @Override
    public boolean contains(SingleDynamicMethod m) {
        for(SingleDynamicMethod method: methods) {
            if(method.contains(m)) {
                return true;
            }
        }
        return false;
    }

    ClassLoader getClassLoader() {
        return classLoader;
    }

    private static boolean isApplicableDynamically(LinkerServices linkerServices, MethodType callSiteType,
            SingleDynamicMethod m) {
        final MethodType methodType = m.getMethodType();
        final boolean varArgs = m.isVarArgs();
        final int fixedArgLen = methodType.parameterCount() - (varArgs ? 1 : 0);
        final int callSiteArgLen = callSiteType.parameterCount();

        // Arity checks
        if(varArgs) {
            if(callSiteArgLen < fixedArgLen) {
                return false;
            }
        } else if(callSiteArgLen != fixedArgLen) {
            return false;
        }

        // Fixed arguments type checks, starting from 1, as receiver type doesn't participate
        for(int i = 1; i < fixedArgLen; ++i) {
            if(!isApplicableDynamically(linkerServices, callSiteType.parameterType(i), methodType.parameterType(i))) {
                return false;
            }
        }
        if(!varArgs) {
            // Not vararg; both arity and types matched.
            return true;
        }

        final Class<?> varArgArrayType = methodType.parameterType(fixedArgLen);
        final Class<?> varArgType = varArgArrayType.getComponentType();

        if(fixedArgLen == callSiteArgLen - 1) {
            // Exactly one vararg; check both array type matching and array component type matching.
            final Class<?> callSiteArgType = callSiteType.parameterType(fixedArgLen);
            return isApplicableDynamically(linkerServices, callSiteArgType, varArgArrayType)
                    || isApplicableDynamically(linkerServices, callSiteArgType, varArgType);
        }

        // Either zero, or more than one vararg; check if all actual vararg types match the vararg array component type.
        for(int i = fixedArgLen; i < callSiteArgLen; ++i) {
            if(!isApplicableDynamically(linkerServices, callSiteType.parameterType(i), varArgType)) {
                return false;
            }
        }

        return true;
    }

    private static boolean isApplicableDynamically(LinkerServices linkerServices, Class<?> callSiteType,
            Class<?> methodType) {
        return TypeUtilities.isPotentiallyConvertible(callSiteType, methodType)
                || linkerServices.canConvert(callSiteType, methodType);
    }

    private ApplicableOverloadedMethods getApplicables(MethodType callSiteType, ApplicabilityTest test) {
        return new ApplicableOverloadedMethods(methods, callSiteType, test);
    }

    /**
     * Add a method to this overloaded method's set.
     *
     * @param method a method to add
     */
    public void addMethod(SingleDynamicMethod method) {
        methods.add(method);
    }
}
