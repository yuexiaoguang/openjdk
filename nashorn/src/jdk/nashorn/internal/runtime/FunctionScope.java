package jdk.nashorn.internal.runtime;

/**
 * This is the base class for function scopes.  Subclasses of this class are
 * produced by the ObjectClassGenerator along with additional fields for storing
 * local vars.  The number of fields required is determined by ObjectCreator.
 *
 * The scope is also responsible for handling the var arg 'arguments' object,
 * though most of the access is via generated code.
 *
 * The constructor of this class is responsible for any function prologue
 * involving the scope.
 *
 * TODO see NASHORN-715.
 */
public class FunctionScope extends ScriptObject implements Scope {

    /** Area to store scope arguments. (public for access from scripts.) */
    public final ScriptObject arguments;

    /** Flag to indicate that a split method issued a return statement */
    private int splitState = -1;

    /**
     * Constructor
     *
     * @param map         property map
     * @param callerScope caller scope
     * @param arguments   arguments
     */
    public FunctionScope(final PropertyMap map, final ScriptObject callerScope, final ScriptObject arguments) {
        super(callerScope, map);
        this.arguments = arguments;
        setIsScope();
    }

    /**
     * Constructor
     *
     * @param map         property map
     * @param callerScope caller scope
     */
    public FunctionScope(final PropertyMap map, final ScriptObject callerScope) {
        super(callerScope, map);
        this.arguments = null;
        setIsScope();
    }

    /**
     * Get the current split state.
     * @return current split state
     */
    @Override
    public int getSplitState() {
        return splitState;
    }

    /**
     * Set the current split state.
     * @param state current split state
     */
    @Override
    public void setSplitState(final int state) {
        splitState = state;
    }
}
