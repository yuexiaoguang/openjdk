package jdk.nashorn.internal.ir;

import java.util.Collections;
import java.util.List;
import jdk.nashorn.internal.ir.annotations.Immutable;
import jdk.nashorn.internal.ir.visitor.NodeVisitor;

/**
 * IR representation of an object literal.
 */
@Immutable
public final class ObjectNode extends Expression {

    /** Literal elements. */
    private final List<PropertyNode> elements;

    /**
     * Constructor
     *
     * @param token    token
     * @param finish   finish
     * @param elements the elements used to initialize this ObjectNode
     */
    public ObjectNode(final long token, final int finish, final List<PropertyNode> elements) {
        super(token, finish);
        this.elements = elements;
    }

    private ObjectNode(final ObjectNode objectNode, final List<PropertyNode> elements) {
        super(objectNode);
        this.elements = elements;
    }

    @Override
    public Node accept(final NodeVisitor<? extends LexicalContext> visitor) {
        if (visitor.enterObjectNode(this)) {
            return visitor.leaveObjectNode(setElements(Node.accept(visitor, PropertyNode.class, elements)));
        }

        return this;
    }

    @Override
    public void toString(final StringBuilder sb) {
        sb.append('{');

        if (!elements.isEmpty()) {
            sb.append(' ');

            boolean first = true;
            for (final Node element : elements) {
                if (!first) {
                    sb.append(", ");
                }
                first = false;

                element.toString(sb);
            }
            sb.append(' ');
        }

        sb.append('}');
    }

    /**
     * Get the elements of this literal node
     * @return a list of elements
     */
    public List<PropertyNode> getElements() {
        return Collections.unmodifiableList(elements);
    }

    private ObjectNode setElements(final List<PropertyNode> elements) {
        if (this.elements == elements) {
            return this;
        }
        return new ObjectNode(this, elements);
    }
}
