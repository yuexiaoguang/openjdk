package jdk.nashorn.internal.ir;

import java.util.List;
import jdk.nashorn.internal.ir.visitor.NodeVisitor;

/**
 * Represents a block used as a statement.
 */
public class BlockStatement extends Statement {
    /** Block to execute. */
    private final Block block;

    /**
     * Constructor
     *
     * @param lineNumber line number
     * @param block the block to execute
     */
    public BlockStatement(final int lineNumber, final Block block) {
        super(lineNumber, block.getToken(), block.getFinish());
        this.block = block;
    }

    private BlockStatement(final BlockStatement blockStatement, final Block block) {
        super(blockStatement);
        this.block = block;
    }

    /**
     * Use this method to create a block statement meant to replace a single statement.
     * @param stmt the statement to replace
     * @param newStmts the statements for the new block statement
     * @return a block statement with the new statements. It will have the line number, token, and finish of the
     * original statement.
     */
    public static Statement createReplacement(final Statement stmt, final List<Statement> newStmts) {
        return createReplacement(stmt, stmt.getFinish(), newStmts);
    }

    /**
     * Use this method to create a block statement meant to replace a single statement.
     * @param stmt the statement to replace
     * @param finish the new finish for the block
     * @param newStmts the statements for the new block statement
     * @return a block statement with the new statements. It will have the line number, and token of the
     * original statement.
     */
    public static Statement createReplacement(final Statement stmt, int finish, final List<Statement> newStmts) {
        return new BlockStatement(stmt.getLineNumber(), new Block(stmt.getToken(), finish, newStmts));
    }

    @Override
    public boolean isTerminal() {
        return block.isTerminal();
    }

    @Override
    public Node accept(final NodeVisitor<? extends LexicalContext> visitor) {
        if (visitor.enterBlockStatement(this)) {
            return visitor.leaveBlockStatement(setBlock((Block)block.accept(visitor)));
        }

        return this;
    }

    @Override
    public void toString(final StringBuilder sb) {
        block.toString(sb);
    }

    /**
     * Return the block to be executed
     * @return the block
     */
    public Block getBlock() {
        return block;
    }

    /**
     * Reset the block to be executed
     * @param block the block
     * @return new or same execute node
     */
    public BlockStatement setBlock(final Block block) {
        if (this.block == block) {
            return this;
        }
        return new BlockStatement(this, block);
    }
}
