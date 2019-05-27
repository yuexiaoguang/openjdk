package jdk.nashorn.internal.ir;

import java.util.List;
import jdk.nashorn.internal.codegen.Label;

/**
 * This class represents a node from which control flow can execute
 * a {@code break} statement
 */
public interface BreakableNode extends LexicalContextNode {
    /**
     * Ensure that any labels in this breakable node are unique so
     * that new jumps won't go to old parts of the tree. Used for
     * example for cloning finally blocks
     *
     * @param lc the lexical context
     * @return node after labels have been made unique
     */
    public abstract Node ensureUniqueLabels(final LexicalContext lc);

    /**
     * Check whether this can be broken out from without using a label,
     * e.g. everything but Blocks, basically
     * @return true if breakable without label
     */
    public boolean isBreakableWithoutLabel();

    /**
     * Return the break label, i.e. the location to go to on break.
     * @return the break label
     */
    public Label getBreakLabel();

    /**
     * Return the labels associated with this node. Breakable nodes that
     * aren't LoopNodes only have a break label - the location immediately
     * afterwards the node in code
     * @return list of labels representing locations around this node
     */
    public List<Label> getLabels();
}
