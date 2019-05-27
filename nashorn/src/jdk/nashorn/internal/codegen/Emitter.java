package jdk.nashorn.internal.codegen;

/**
 * Interface for anything that interacts with a low level bytecode
 * generation module, for example ASM.
 * <p>
 * This is pretty generic, i.e. it can be a ClassEmitter, MethodEmitter
 * or potentially even more fine grained stuff.
 */
public interface Emitter {

    /**
     * Register the start of emission for this CodeEmitter
     */
    public void begin();

    /**
     * Register the end of emission for this CodeEmitter.
     * This is typically required before generated code can
     * be requested from it
     */
    public void end();
}
