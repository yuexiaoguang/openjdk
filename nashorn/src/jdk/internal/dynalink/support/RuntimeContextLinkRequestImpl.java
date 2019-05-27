package jdk.internal.dynalink.support;

import jdk.internal.dynalink.CallSiteDescriptor;
import jdk.internal.dynalink.linker.LinkRequest;

/**
 * A link request implementation for call sites that pass language runtime specific context arguments on the stack. The
 * context specific arguments should be the first "n" arguments.
 */
public class RuntimeContextLinkRequestImpl extends LinkRequestImpl {

    private final int runtimeContextArgCount;
    private LinkRequestImpl contextStrippedRequest;

    /**
     * Creates a new link request.
     *
     * @param callSiteDescriptor the descriptor for the call site being linked
     * @param arguments the arguments for the invocation
     * @param callSiteUnstable true if the call site being linked is considered unstable
     * @param runtimeContextArgCount the number of the leading arguments on the stack that represent the language
     * runtime specific context arguments.
     * @throws IllegalArgumentException if runtimeContextArgCount is less than 1.
     */
    public RuntimeContextLinkRequestImpl(CallSiteDescriptor callSiteDescriptor, boolean callSiteUnstable,
            Object[] arguments, int runtimeContextArgCount) {
        super(callSiteDescriptor, callSiteUnstable, arguments);
        if(runtimeContextArgCount < 1) {
            throw new IllegalArgumentException("runtimeContextArgCount < 1");
        }
        this.runtimeContextArgCount = runtimeContextArgCount;
    }

    @Override
    public LinkRequest withoutRuntimeContext() {
        if(contextStrippedRequest == null) {
            contextStrippedRequest =
                    new LinkRequestImpl(CallSiteDescriptorFactory.dropParameterTypes(getCallSiteDescriptor(), 1,
                            runtimeContextArgCount + 1), isCallSiteUnstable(), getTruncatedArguments());
        }
        return contextStrippedRequest;
    }

    @Override
    public LinkRequest replaceArguments(CallSiteDescriptor callSiteDescriptor, Object[] arguments) {
        return new RuntimeContextLinkRequestImpl(callSiteDescriptor, isCallSiteUnstable(), arguments,
                runtimeContextArgCount);
    }

    private Object[] getTruncatedArguments() {
        final Object[] args = getArguments();
        final Object[] newargs = new Object[args.length - runtimeContextArgCount];
        newargs[0] = args[0]; // "this" remains at the 0th position
        System.arraycopy(args, runtimeContextArgCount + 1, newargs, 1, newargs.length - 1);
        return newargs;
    }
}
