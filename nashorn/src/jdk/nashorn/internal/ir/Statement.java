package jdk.nashorn.internal.ir;

/**
 * Statement is something that becomes code and can be stepped past. A block is
 * made up of statements. The only node subclass that needs to keep token and
 * location information is the Statement
 */
public abstract class Statement extends Node {

    private final int lineNumber;

    /**
     * Constructor
     *
     * @param lineNumber line number
     * @param token      token
     * @param finish     finish
     */
    public Statement(final int lineNumber, final long token, final int finish) {
        super(token, finish);
        this.lineNumber = lineNumber;
    }

    /**
     * Constructor
     *
     * @param lineNumber line number
     * @param token      token
     * @param start      start
     * @param finish     finish
     */
    protected Statement(final int lineNumber, final long token, final int start, final int finish) {
        super(token, start, finish);
        this.lineNumber = lineNumber;
    }

    /**
     * Copy constructor
     *
     * @param node source node
     */
    protected Statement(final Statement node) {
        super(node);
        this.lineNumber = node.lineNumber;
    }

    /**
     * Return the line number
     * @return line number
     */
    public int getLineNumber() {
        return lineNumber;
    }

}
