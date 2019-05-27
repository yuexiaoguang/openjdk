package jdk.internal.dynalink;

import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MutableCallSite;
import java.lang.invoke.VolatileCallSite;
import jdk.internal.dynalink.linker.GuardedInvocation;

/**
 * Interface for relinkable call sites. Language runtimes wishing to use this framework must use subclasses of
 * {@link CallSite} that also implement this interface as their call sites. There is a readily usable
 * {@link MonomorphicCallSite} subclass that implements monomorphic inline caching strategy as well as a
 * {@link ChainedCallSite} that retains a chain of already linked method handles. The reason this is defined as an
 * interface instead of a concrete, albeit abstract class is that it allows independent implementations to choose
 * between {@link MutableCallSite} and {@link VolatileCallSite} as they see fit.
 */
public interface RelinkableCallSite {
    /**
     * Initializes the relinkable call site by setting a relink-and-invoke method handle. The call site implementation
     * is supposed to set this method handle as its target.
     * @param relinkAndInvoke a relink-and-invoke method handle supplied by the {@link DynamicLinker}.
     */
    public void initialize(MethodHandle relinkAndInvoke);

    /**
     * Returns the descriptor for this call site.
     *
     * @return the descriptor for this call site.
     */
    public CallSiteDescriptor getDescriptor();

    /**
     * This method will be called by the dynamic linker every time the call site is normally relinked. It will be passed
     * a {@code GuardedInvocation} that the call site should incorporate into its target method handle. When this method
     * is called, the call site is allowed to keep other non-invalidated invocations around for implementation of
     * polymorphic inline caches and compose them with this invocation to form its final target.
     *
     * @param guardedInvocation the guarded invocation that the call site should incorporate into its target method
     * handle.
     * @param fallback the fallback method. This is a method matching the method type of the call site that is supplied
     * by the {@link DynamicLinker} to be used by this call site as a fallback when it can't invoke its target with the
     * passed arguments. The fallback method is such that when it's invoked, it'll try to discover the adequate target
     * for the invocation, subsequently invoke {@link #relink(GuardedInvocation, MethodHandle)} or
     * {@link #resetAndRelink(GuardedInvocation, MethodHandle)}, and finally invoke the target.
     */
    public void relink(GuardedInvocation guardedInvocation, MethodHandle fallback);

    /**
     * This method will be called by the dynamic linker every time the call site is relinked and the linker wishes the
     * call site to throw away any prior linkage state. It will be passed a {@code GuardedInvocation} that the call site
     * should use to build its target method handle. When this method is called, the call site is discouraged from
     * keeping previous state around, and is supposed to only link the current invocation.
     *
     * @param guardedInvocation the guarded invocation that the call site should use to build its target method handle.
     * @param fallback the fallback method. This is a method matching the method type of the call site that is supplied
     * by the {@link DynamicLinker} to be used by this call site as a fallback when it can't invoke its target with the
     * passed arguments. The fallback method is such that when it's invoked, it'll try to discover the adequate target
     * for the invocation, subsequently invoke {@link #relink(GuardedInvocation, MethodHandle)} or
     * {@link #resetAndRelink(GuardedInvocation, MethodHandle)}, and finally invoke the target.
     */
    public void resetAndRelink(GuardedInvocation guardedInvocation, MethodHandle fallback);
}
