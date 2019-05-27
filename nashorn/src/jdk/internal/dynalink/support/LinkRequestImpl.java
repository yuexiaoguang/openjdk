package jdk.internal.dynalink.support;

import jdk.internal.dynalink.CallSiteDescriptor;
import jdk.internal.dynalink.linker.LinkRequest;

/**
 * Default implementation of the {@link LinkRequest}, representing a link request to a call site that passes no language
 * runtime specific native context arguments on the stack.
 */
public class LinkRequestImpl implements LinkRequest {

    private final CallSiteDescriptor callSiteDescriptor;
    private final Object[] arguments;
    private final boolean callSiteUnstable;

    /**
     * Creates a new link request.
     *
     * @param callSiteDescriptor the descriptor for the call site being linked
     * @param callSiteUnstable true if the call site being linked is considered unstable
     * @param arguments the arguments for the invocation
     */
    public LinkRequestImpl(CallSiteDescriptor callSiteDescriptor, boolean callSiteUnstable, Object... arguments) {
        this.callSiteDescriptor = callSiteDescriptor;
        this.callSiteUnstable = callSiteUnstable;
        this.arguments = arguments;
    }

    @Override
    public Object[] getArguments() {
        return arguments != null ? arguments.clone() : null;
    }

    @Override
    public Object getReceiver() {
        return arguments != null && arguments.length > 0 ? arguments[0] : null;
    }

    @Override
    public CallSiteDescriptor getCallSiteDescriptor() {
        return callSiteDescriptor;
    }

    @Override
    public boolean isCallSiteUnstable() {
        return callSiteUnstable;
    }

    @Override
    public LinkRequest withoutRuntimeContext() {
        return this;
    }

    @Override
    public LinkRequest replaceArguments(CallSiteDescriptor newCallSiteDescriptor, Object[] newArguments) {
        return new LinkRequestImpl(newCallSiteDescriptor, callSiteUnstable, newArguments);
    }
}
