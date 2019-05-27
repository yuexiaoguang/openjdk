package jdk.nashorn.internal.ir;

import static jdk.nashorn.internal.parser.TokenType.BIT_NOT;
import static jdk.nashorn.internal.parser.TokenType.DECPOSTFIX;
import static jdk.nashorn.internal.parser.TokenType.INCPOSTFIX;

import jdk.nashorn.internal.codegen.types.Type;
import jdk.nashorn.internal.ir.annotations.Immutable;
import jdk.nashorn.internal.ir.visitor.NodeVisitor;
import jdk.nashorn.internal.parser.Token;
import jdk.nashorn.internal.parser.TokenType;

/**
 * UnaryNode nodes represent single operand operations.
 */
@Immutable
public final class UnaryNode extends Expression implements Assignment<Expression> {
    /** Right hand side argument. */
    private final Expression rhs;

    /**
     * Constructor
     *
     * @param token  token
     * @param rhs    expression
     */
    public UnaryNode(final long token, final Expression rhs) {
        this(token, Math.min(rhs.getStart(), Token.descPosition(token)), Math.max(Token.descPosition(token) + Token.descLength(token), rhs.getFinish()), rhs);
    }

    /**
     * Constructor
     *
     * @param token  token
     * @param start  start
     * @param finish finish
     * @param rhs    expression
     */
    public UnaryNode(final long token, final int start, final int finish, final Expression rhs) {
        super(token, start, finish);
        this.rhs = rhs;
    }


    private UnaryNode(final UnaryNode unaryNode, final Expression rhs) {
        super(unaryNode);
        this.rhs = rhs;
    }

    /**
     * Is this an assignment - i.e. that mutates something such as a++
     *
     * @return true if assignment
     */
    @Override
    public boolean isAssignment() {
        switch (tokenType()) {
        case DECPOSTFIX:
        case DECPREFIX:
        case INCPOSTFIX:
        case INCPREFIX:
            return true;
        default:
            return false;
        }
    }

    @Override
    public boolean isSelfModifying() {
        return isAssignment();
    }

    @Override
    public Type getWidestOperationType() {
        return isAssignment() ? Type.NUMBER : Type.OBJECT;
    }

    @Override
    public Expression getAssignmentDest() {
        return isAssignment() ? rhs() : null;
    }

    @Override
    public UnaryNode setAssignmentDest(Expression n) {
        return setRHS(n);
    }

    @Override
    public Expression getAssignmentSource() {
        return getAssignmentDest();
    }

    /**
     * Assist in IR navigation.
     * @param visitor IR navigating visitor.
     */
    @Override
    public Node accept(final NodeVisitor<? extends LexicalContext> visitor) {
        if (visitor.enterUnaryNode(this)) {
            return visitor.leaveUnaryNode(setRHS((Expression)rhs.accept(visitor)));
        }

        return this;
    }

    @Override
    public boolean isLocal() {
        switch (tokenType()) {
            case NEW:
                return false;
            case ADD:
            case SUB:
            case NOT:
            case BIT_NOT:
                return rhs.isLocal() && rhs.getType().isJSPrimitive();
            case DECPOSTFIX:
            case DECPREFIX:
            case INCPOSTFIX:
            case INCPREFIX:
                return rhs instanceof IdentNode && rhs.isLocal() && rhs.getType().isJSPrimitive();
            default:
                return rhs.isLocal();
        }
    }

    @Override
    public void toString(final StringBuilder sb) {
        toString(sb, new Runnable() {
            @Override
            public void run() {
                sb.append(rhs().toString());
            }
        });
    }

    /**
     * Creates the string representation of this unary node, delegating the creation of the string representation of its
     * operand to a specified runnable.
     * @param sb the string builder to use
     * @param rhsStringBuilder the runnable that appends the string representation of the operand to the string builder
     * when invoked.
     */
    public void toString(final StringBuilder sb, final Runnable rhsStringBuilder) {
        final TokenType type      = tokenType();
        final String    name      = type.getName();
        final boolean   isPostfix = type == DECPOSTFIX || type == INCPOSTFIX;

        boolean rhsParen   = type.needsParens(rhs().tokenType(), false);

        if (!isPostfix) {
            if (name == null) {
                sb.append(type.name());
                rhsParen = true;
            } else {
                sb.append(name);

                if (type.ordinal() > BIT_NOT.ordinal()) {
                    sb.append(' ');
                }
            }
        }

        if (rhsParen) {
            sb.append('(');
        }
        rhsStringBuilder.run();
        if (rhsParen) {
            sb.append(')');
        }

        if (isPostfix) {
            sb.append(type == DECPOSTFIX ? "--" : "++");
        }
    }

    /**
     * Get the right hand side of this if it is inherited by a binary expression,
     * or just the expression itself if still Unary
     *
     * @see BinaryNode
     *
     * @return right hand side or expression node
     */
    public Expression rhs() {
        return rhs;
    }

    /**
     * Reset the right hand side of this if it is inherited by a binary expression,
     * or just the expression itself if still Unary
     *
     * @see BinaryNode
     *
     * @param rhs right hand side or expression node
     * @return a node equivalent to this one except for the requested change.
     */
    public UnaryNode setRHS(final Expression rhs) {
        if (this.rhs == rhs) {
            return this;
        }
        return new UnaryNode(this, rhs);
    }
}
