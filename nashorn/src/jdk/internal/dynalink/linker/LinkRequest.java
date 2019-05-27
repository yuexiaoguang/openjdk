package jdk.internal.dynalink.linker;

import jdk.internal.dynalink.CallSiteDescriptor;
import jdk.internal.dynalink.DynamicLinkerFactory;

/**
 * Represents a request to link a particular invocation at a particular call site. Instances of these requests are being
 * passed to {@link GuardingDynamicLinker}.
 */
public interface LinkRequest {
    /**
     * Returns the call site descriptor for the call site being linked.
     *
     * @return the call site descriptor for the call site being linked.
     */
    public CallSiteDescriptor getCallSiteDescriptor();

    /**
     * Returns the arguments for the invocation being linked. The returned array is a clone; modifications to it won't
     * affect the arguments in this request.
     *
     * @return the arguments for the invocation being linked.
     */
    public Object[] getArguments();

    /**
     * Returns the 0th argument for the invocation being linked; this is typically the receiver object.
     *
     * @return the receiver object.
     */
    public Object getReceiver();

    /**
     * Returns true if the call site is considered unstable, that is, it has been relinked more times than was
     * specified in {@link DynamicLinkerFactory#setUnstableRelinkThreshold(int)}. Linkers should use this as a
     * hint to prefer producing linkage that is more stable (its guard fails less frequently), even if that assumption
     * causes a less effective version of an operation to be linked. This is just a hint, of course, and linkers are
     * free to ignore this property.
     * @return true if the call site is considered unstable.
     */
    public boolean isCallSiteUnstable();

    /**
     * Returns a request stripped from runtime context arguments. Some language runtimes will include runtime-specific
     * context parameters in their call sites as few arguments between 0th argument "this" and the normal arguments. If
     * a linker does not recognize such contexts at all, or does not recognize the call site as one with its own
     * context, it can ask for the alternative link request with context parameters and arguments removed, and link
     * against it instead.
     *
     * @return the context-stripped request. If the link request does not have any language runtime specific context
     * parameters, the same link request is returned.
     */
    public LinkRequest withoutRuntimeContext();

    /**
     * Returns a request identical to this one with call site descriptor and arguments replaced with the ones specified.
     *
     * @param callSiteDescriptor the new call site descriptor
     * @param arguments the new arguments
     * @return a new request identical to this one, except with the call site descriptor and arguments replaced with the
     * specified ones.
     */
    public LinkRequest replaceArguments(CallSiteDescriptor callSiteDescriptor, Object[] arguments);
}
