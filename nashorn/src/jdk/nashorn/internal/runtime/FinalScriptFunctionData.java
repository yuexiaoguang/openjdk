package jdk.nashorn.internal.runtime;

import static jdk.nashorn.internal.lookup.Lookup.MH;

import java.lang.invoke.MethodHandle;

/**
 * This is a subclass that represents a script function that may not be regenerated.
 * This is used for example for bound functions and builtins.
 */
final class FinalScriptFunctionData extends ScriptFunctionData {

    /**
     * Constructor - used for bind
     *
     * @param name          name
     * @param arity         arity
     * @param functions     precompiled code
     * @param isStrict      strict
     * @param isBuiltin     builtin
     * @param isConstructor constructor
     */
    FinalScriptFunctionData(final String name, int arity, CompiledFunctions functions, final boolean isStrict, final boolean isBuiltin, final boolean isConstructor) {
        super(name, arity, isStrict, isBuiltin, isConstructor);
        code.addAll(functions);
    }

    /**
     * Constructor - used from ScriptFunction. This assumes that we have code alraedy for the
     * method (typically a native method) and possibly specializations.
     *
     * @param name           name
     * @param mh             method handle for generic version of method
     * @param specs          specializations
     * @param isStrict       strict
     * @param isBuiltin      builtin
     * @param isConstructor  constructor
     */
    FinalScriptFunctionData(final String name, final MethodHandle mh, final MethodHandle[] specs, final boolean isStrict, final boolean isBuiltin, final boolean isConstructor) {
        super(name, arity(mh), isStrict, isBuiltin, isConstructor);

        addInvoker(mh);
        if (specs != null) {
            for (final MethodHandle spec : specs) {
                addInvoker(spec);
            }
        }
    }

    private void addInvoker(final MethodHandle mh) {
        if (isConstructor(mh)) {
            // only nasgen constructors: (boolean, self, args) are subject to binding a boolean newObj. isConstructor
            // is too conservative a check. However, isConstructor(mh) always implies isConstructor param
            assert isConstructor();
            final MethodHandle invoker = MH.insertArguments(mh, 0, false);
            final MethodHandle constructor = composeConstructor(MH.insertArguments(mh, 0, true));
            code.add(new CompiledFunction(mh.type(), invoker, constructor));
        } else {
            code.add(new CompiledFunction(mh.type(), mh));
        }
    }

    private static int arity(final MethodHandle mh) {
        if (isVarArg(mh)) {
            return -1;
        }

        //drop self, callee and boolean constructor flag to get real arity
        return mh.type().parameterCount() - 1 - (needsCallee(mh) ? 1 : 0) - (isConstructor(mh) ? 1 : 0);
    }

    private static boolean isConstructor(final MethodHandle mh) {
        return mh.type().parameterCount() >= 1 && mh.type().parameterType(0) == boolean.class;
    }

}
