package jdk.nashorn.internal.ir;

import jdk.nashorn.internal.ir.annotations.Immutable;
import jdk.nashorn.internal.ir.visitor.NodeVisitor;

/**
 * IR representation of a property access (period operator.)
 */
@Immutable
public final class AccessNode extends BaseNode {
    /** Property ident. */
    private final IdentNode property;

    /**
     * Constructor
     *
     * @param token     token
     * @param finish    finish
     * @param base      base node
     * @param property  property
     */
    public AccessNode(final long token, final int finish, final Expression base, final IdentNode property) {
        super(token, finish, base, false);
        this.property = property.setIsPropertyName();
    }

    private AccessNode(final AccessNode accessNode, final Expression base, final IdentNode property, final boolean isFunction) {
        super(accessNode, base, isFunction);
        this.property = property;
    }

    /**
     * Assist in IR navigation.
     * @param visitor IR navigating visitor.
     */
    @Override
    public Node accept(final NodeVisitor<? extends LexicalContext> visitor) {
        if (visitor.enterAccessNode(this)) {
            return visitor.leaveAccessNode(
                setBase((Expression)base.accept(visitor)).
                setProperty((IdentNode)property.accept(visitor)));
        }
        return this;
    }

    @Override
    public void toString(final StringBuilder sb) {
        final boolean needsParen = tokenType().needsParens(getBase().tokenType(), true);

        if (needsParen) {
            sb.append('(');
        }

        base.toString(sb);

        if (needsParen) {
            sb.append(')');
        }
        sb.append('.');

        sb.append(property.getName());
    }

    /**
     * Get the property
     *
     * @return the property IdentNode
     */
    public IdentNode getProperty() {
        return property;
    }

    private AccessNode setBase(final Expression base) {
        if (this.base == base) {
            return this;
        }
        return new AccessNode(this, base, property, isFunction());
    }

    private AccessNode setProperty(final IdentNode property) {
        if (this.property == property) {
            return this;
        }
        return new AccessNode(this, base, property, isFunction());
    }

    @Override
    public BaseNode setIsFunction() {
        if (isFunction()) {
            return this;
        }
        return new AccessNode(this, base, property, true);
    }

}
