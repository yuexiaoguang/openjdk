package jdk.nashorn.internal.ir;

import jdk.nashorn.internal.ir.annotations.Immutable;

/**
 * IR base for accessing/indexing nodes.
 */
@Immutable
public abstract class BaseNode extends Expression implements FunctionCall {

    /** Base Node. */
    protected final Expression base;

    private final boolean isFunction;

    /**
     * Constructor
     *
     * @param token  token
     * @param finish finish
     * @param base   base node
     * @param isFunction is this a function
     */
    public BaseNode(final long token, final int finish, final Expression base, final boolean isFunction) {
        super(token, base.getStart(), finish);
        this.base            = base;
        this.isFunction      = isFunction;
    }

    /**
     * Copy constructor for immutable nodes
     * @param baseNode node to inherit from
     * @param base base
     * @param isFunction is this a function
     */
    protected BaseNode(final BaseNode baseNode, final Expression base, final boolean isFunction) {
        super(baseNode);
        this.base            = base;
        this.isFunction      = isFunction;
    }

    /**
     * Get the base node for this access
     * @return the base node
     */
    public Expression getBase() {
        return base;
    }

    @Override
    public boolean isFunction() {
        return isFunction;
    }

    /**
     * Mark this node as being the callee operand of a {@link CallNode}.
     * @return a base node identical to this one in all aspects except with its function flag set.
     */
    public abstract BaseNode setIsFunction();

}
