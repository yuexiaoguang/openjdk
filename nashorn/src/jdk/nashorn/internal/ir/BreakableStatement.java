package jdk.nashorn.internal.ir;

import java.util.Collections;
import java.util.List;
import jdk.nashorn.internal.codegen.Label;
import jdk.nashorn.internal.ir.annotations.Immutable;

@Immutable
abstract class BreakableStatement extends LexicalContextStatement implements BreakableNode {

    /** break label. */
    protected final Label breakLabel;

    /**
     * Constructor
     *
     * @param lineNumber line number
     * @param token      token
     * @param finish     finish
     * @param breakLabel break label
     */
    protected BreakableStatement(final int lineNumber, final long token, final int finish, final Label breakLabel) {
        super(lineNumber, token, finish);
        this.breakLabel = breakLabel;
    }

    /**
     * Copy constructor
     *
     * @param breakableNode source node
     */
    protected BreakableStatement(final BreakableStatement breakableNode) {
        super(breakableNode);
        this.breakLabel = new Label(breakableNode.getBreakLabel());
    }

    /**
     * Check whether this can be broken out from without using a label,
     * e.g. everything but Blocks, basically
     * @return true if breakable without label
     */
    @Override
    public boolean isBreakableWithoutLabel() {
        return true;
    }

    /**
     * Return the break label, i.e. the location to go to on break.
     * @return the break label
     */
    @Override
    public Label getBreakLabel() {
        return breakLabel;
    }

    /**
     * Return the labels associated with this node. Breakable nodes that
     * aren't LoopNodes only have a break label - the location immediately
     * afterwards the node in code
     * @return list of labels representing locations around this node
     */
    @Override
    public List<Label> getLabels() {
        return Collections.singletonList(breakLabel);
    }
}
