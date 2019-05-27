package jdk.internal.dynalink.beans;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Array;
import java.util.StringTokenizer;
import jdk.internal.dynalink.CallSiteDescriptor;
import jdk.internal.dynalink.linker.LinkerServices;
import jdk.internal.dynalink.support.Guards;
import jdk.internal.dynalink.support.Lookup;

/**
 * Base class for dynamic methods that dispatch to a single target Java method or constructor. Handles adaptation of the
 * target method to a call site type (including mapping variable arity methods to a call site signature with different
 * arity).
 */
abstract class SingleDynamicMethod extends DynamicMethod {

    private static final MethodHandle CAN_CONVERT_TO = Lookup.findOwnStatic(MethodHandles.lookup(), "canConvertTo", boolean.class, LinkerServices.class, Class.class, Object.class);

    SingleDynamicMethod(String name) {
        super(name);
    }

    /**
     * Returns true if this method is variable arity.
     * @return true if this method is variable arity.
     */
    abstract boolean isVarArgs();

    /**
     * Returns this method's native type.
     * @return this method's native type.
     */
    abstract MethodType getMethodType();

    /**
     * Given a specified lookup, returns a method handle to this method's target.
     * @param lookup the lookup to use.
     * @return the handle to this method's target method.
     */
    abstract MethodHandle getTarget(MethodHandles.Lookup lookup);

    @Override
    MethodHandle getInvocation(CallSiteDescriptor callSiteDescriptor, LinkerServices linkerServices) {
        return getInvocation(getTarget(callSiteDescriptor.getLookup()), callSiteDescriptor.getMethodType(),
                linkerServices);
    }

    @Override
    SingleDynamicMethod getMethodForExactParamTypes(String paramTypes) {
        return typeMatchesDescription(paramTypes, getMethodType()) ? this : null;
    }

    @Override
    boolean contains(SingleDynamicMethod method) {
        return getMethodType().parameterList().equals(method.getMethodType().parameterList());
    }

    static String getMethodNameWithSignature(MethodType type, String methodName) {
        final String typeStr = type.toString();
        final int retTypeIndex = typeStr.lastIndexOf(')') + 1;
        int secondParamIndex = typeStr.indexOf(',') + 1;
        if(secondParamIndex == 0) {
            secondParamIndex = retTypeIndex - 1;
        }
        return typeStr.substring(retTypeIndex) + " " + methodName + "(" + typeStr.substring(secondParamIndex, retTypeIndex);
    }

    /**
     * Given a method handle and a call site type, adapts the method handle to the call site type. Performs type
     * conversions as needed using the specified linker services, and in case that the method handle is a vararg
     * collector, matches it to the arity of the call site.
     * @param target the method handle to adapt
     * @param callSiteType the type of the call site
     * @param linkerServices the linker services used for type conversions
     * @return the adapted method handle.
     */
    static MethodHandle getInvocation(MethodHandle target, MethodType callSiteType, LinkerServices linkerServices) {
        final MethodType methodType = target.type();
        final int paramsLen = methodType.parameterCount();
        final boolean varArgs = target.isVarargsCollector();
        final MethodHandle fixTarget = varArgs ? target.asFixedArity() : target;
        final int fixParamsLen = varArgs ? paramsLen - 1 : paramsLen;
        final int argsLen = callSiteType.parameterCount();
        if(argsLen < fixParamsLen) {
            // Less actual arguments than number of fixed declared arguments; can't invoke.
            return null;
        }
        // Method handle has the same number of fixed arguments as the call site type
        if(argsLen == fixParamsLen) {
            // Method handle that matches the number of actual arguments as the number of fixed arguments
            final MethodHandle matchedMethod;
            if(varArgs) {
                // If vararg, add a zero-length array of the expected type as the last argument to signify no variable
                // arguments.
                matchedMethod = MethodHandles.insertArguments(fixTarget, fixParamsLen, Array.newInstance(
                        methodType.parameterType(fixParamsLen).getComponentType(), 0));
            } else {
                // Otherwise, just use the method
                matchedMethod = fixTarget;
            }
            return createConvertingInvocation(matchedMethod, linkerServices, callSiteType);
        }

        // What's below only works for varargs
        if(!varArgs) {
            return null;
        }

        final Class<?> varArgType = methodType.parameterType(fixParamsLen);
        // Handle a somewhat sinister corner case: caller passes exactly one argument in the vararg position, and we
        // must handle both a prepacked vararg array as well as a genuine 1-long vararg sequence.
        if(argsLen == paramsLen) {
            final Class<?> callSiteLastArgType = callSiteType.parameterType(fixParamsLen);
            if(varArgType.isAssignableFrom(callSiteLastArgType)) {
                // Call site signature guarantees we'll always be passed a single compatible array; just link directly
                // to the method, introducing necessary conversions. Also, preserve it being a variable arity method.
                return createConvertingInvocation(target, linkerServices, callSiteType).asVarargsCollector(
                        callSiteLastArgType);
            }

            // This method handle takes the single argument and packs it into a newly allocated single-element array. It
            // will be used when the incoming argument can't be converted to the vararg array type (the "vararg packer"
            // method).
            final MethodHandle varArgCollectingInvocation = createConvertingInvocation(collectArguments(fixTarget,
                    argsLen), linkerServices, callSiteType);

            // Is call site type assignable from an array type (e.g. Object:int[], or Object[]:String[])
            final boolean isAssignableFromArray = callSiteLastArgType.isAssignableFrom(varArgType);
            // Do we have a custom conversion that can potentially convert the call site type to an array?
            final boolean isCustomConvertible = linkerServices.canConvert(callSiteLastArgType, varArgType);
            if(!isAssignableFromArray && !isCustomConvertible) {
                // Call site signature guarantees the argument can definitely not be converted to an array (i.e. it is
                // primitive), and no conversion can help with it either. Link immediately to a vararg-packing method
                // handle.
                return varArgCollectingInvocation;
            }

            // This method handle employs language-specific conversions to convert the last argument into an array of
            // vararg type.
            final MethodHandle arrayConvertingInvocation = createConvertingInvocation(MethodHandles.filterArguments(
                    fixTarget, fixParamsLen, linkerServices.getTypeConverter(callSiteLastArgType, varArgType)),
                    linkerServices, callSiteType);

            // This method handle determines whether the value can be converted to the array of vararg type using a
            // language-specific conversion.
            final MethodHandle canConvertArgToArray = MethodHandles.insertArguments(CAN_CONVERT_TO, 0, linkerServices,
                    varArgType);

            // This one adjusts the previous one for the location of the argument and the call site type.
            final MethodHandle canConvertLastArgToArray = MethodHandles.dropArguments(canConvertArgToArray, 0,
                    MethodType.genericMethodType(fixParamsLen).parameterList()).asType(callSiteType.changeReturnType(boolean.class));

            // This one takes the previous ones and combines them into a method handle that converts the argument into
            // a vararg array when it can, otherwise falls back to the vararg packer.
            final MethodHandle convertToArrayWhenPossible = MethodHandles.guardWithTest(canConvertLastArgToArray,
                    arrayConvertingInvocation, varArgCollectingInvocation);

            if(isAssignableFromArray) {
                return MethodHandles.guardWithTest(
                        // Is incoming parameter already a compatible array?
                        Guards.isInstance(varArgType, fixParamsLen, callSiteType),
                        // Yes: just pass it to the method
                        createConvertingInvocation(fixTarget, linkerServices, callSiteType),
                        // No: either go through a custom conversion, or if it is not possible, go directly to the
                        // vararg packer.
                        isCustomConvertible ? convertToArrayWhenPossible : varArgCollectingInvocation);
            }

            // Just do the custom conversion with fallback to the vararg packer logic.
            assert isCustomConvertible;
            return convertToArrayWhenPossible;
        }

        // Remaining case: more than one vararg.
        return createConvertingInvocation(collectArguments(fixTarget, argsLen), linkerServices, callSiteType);
    }

    @SuppressWarnings("unused")
    private static boolean canConvertTo(final LinkerServices linkerServices, Class<?> to, Object obj) {
        return obj == null ? false : linkerServices.canConvert(obj.getClass(), to);
    }

    /**
     * Creates a method handle out of the original target that will collect the varargs for the exact component type of
     * the varArg array. Note that this will nicely trigger language-specific type converters for exactly those varargs
     * for which it is necessary when later passed to linkerServices.convertArguments().
     *
     * @param target the original method handle
     * @param parameterCount the total number of arguments in the new method handle
     * @return a collecting method handle
     */
    static MethodHandle collectArguments(MethodHandle target, final int parameterCount) {
        final MethodType methodType = target.type();
        final int fixParamsLen = methodType.parameterCount() - 1;
        final Class<?> arrayType = methodType.parameterType(fixParamsLen);
        return target.asCollector(arrayType, parameterCount - fixParamsLen);
    }

    private static MethodHandle createConvertingInvocation(final MethodHandle sizedMethod,
            final LinkerServices linkerServices, final MethodType callSiteType) {
        return linkerServices.asType(sizedMethod, callSiteType);
    }

    private static boolean typeMatchesDescription(String paramTypes, MethodType type) {
        final StringTokenizer tok = new StringTokenizer(paramTypes, ", ");
        for(int i = 1; i < type.parameterCount(); ++i) { // i = 1 as we ignore the receiver
            if(!(tok.hasMoreTokens() && typeNameMatches(tok.nextToken(), type.parameterType(i)))) {
                return false;
            }
        }
        return !tok.hasMoreTokens();
    }

    private static boolean typeNameMatches(String typeName, Class<?> type) {
        return  typeName.equals(typeName.indexOf('.') == -1 ? type.getSimpleName() : type.getCanonicalName());
    }
}
