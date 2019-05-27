package jdk.nashorn.internal.codegen;

import static jdk.nashorn.internal.codegen.CompilerConstants.SCOPE;

import java.util.List;
import jdk.nashorn.internal.ir.Symbol;
import jdk.nashorn.internal.runtime.PropertyMap;

/**
 * Base class for object creation code generation.
 */
public abstract class ObjectCreator {

    /** List of keys to initiate in this ObjectCreator */
    protected final List<String>  keys;

    /** List of symbols to initiate in this ObjectCreator */
    protected final List<Symbol>  symbols;

    /** Code generator */
    protected final CodeGenerator codegen;

    /** Property map */
    protected PropertyMap   propertyMap;

    private final boolean       isScope;
    private final boolean       hasArguments;

    /**
     * Constructor
     *
     * @param codegen      the code generator
     * @param keys         the keys
     * @param symbols      the symbols corresponding to keys, same index
     * @param isScope      is this object scope
     * @param hasArguments does the created object have an "arguments" property
     */
    protected ObjectCreator(final CodeGenerator codegen, final List<String> keys, final List<Symbol> symbols, final boolean isScope, final boolean hasArguments) {
        this.codegen       = codegen;
        this.keys          = keys;
        this.symbols       = symbols;
        this.isScope       = isScope;
        this.hasArguments  = hasArguments;
    }

    /**
     * Generate code for making the object.
     * @param method Script method.
     */
    protected abstract void makeObject(final MethodEmitter method);

    /**
     * Construct the property map appropriate for the object.
     * @return the newly created property map
     */
    protected abstract PropertyMap makeMap();

    /**
     * Create a new MapCreator
     * @param clazz type of MapCreator
     * @return map creator instantiated by type
     */
    protected MapCreator newMapCreator(final Class<?> clazz) {
        return new MapCreator(clazz, keys, symbols);
    }

    /**
     * Loads the scope on the stack through the passed method emitter.
     * @param method the method emitter to use
     */
    protected void loadScope(final MethodEmitter method) {
        method.loadCompilerConstant(SCOPE);
    }

    /**
     * Emit the correct map for the object.
     * @param method method emitter
     * @return the method emitter
     */
    protected MethodEmitter loadMap(final MethodEmitter method) {
        codegen.loadConstant(propertyMap);
        return method;
    }

    /**
     * Is this a scope object
     * @return true if scope
     */
    protected boolean isScope() {
        return isScope;
    }

    /**
     * Does the created object have an "arguments" property
     * @return true if has an "arguments" property
     */
    protected boolean hasArguments() {
        return hasArguments;
    }
}
