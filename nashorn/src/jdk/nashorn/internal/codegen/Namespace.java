package jdk.nashorn.internal.codegen;

import java.util.HashMap;

/**
 * A name space hierarchy, where each level holds a name directory with
 * names that may be unique for each level.
 */
public class Namespace {
    /** Parent namespace. */
    private final Namespace parent;

    /** Name directory - version count for each name */
    private final HashMap<String, Integer> directory;

    /**
     * Constructor
     */
    public Namespace() {
        this(null);
    }

    /**
     * Constructor
     *
     * @param parent parent name space
     */
    public Namespace(final Namespace parent) {
        this.parent    = parent;
        this.directory = new HashMap<>();
    }

    /**
     * Return the parent Namespace of this space.
     *
     * @return parent name space
     */
    public Namespace getParent() {
        return parent;
    }

    /**
     * Create a uniqueName name in the namespace in the form base$n where n varies
     * .
     * @param base Base of name.  Base will be returned if uniqueName.
     *
     * @return Generated uniqueName name.
     */
    public String uniqueName(final String base) {
        for (Namespace namespace = this; namespace != null; namespace = namespace.getParent()) {
            final HashMap<String, Integer> namespaceDirectory = namespace.directory;
            final Integer                  counter            = namespaceDirectory.get(base);

            if (counter != null) {
                final int count = counter + 1;
                namespaceDirectory.put(base, count);

                return base + '-' + count;
            }
        }

        directory.put(base, 0);

        return base;
    }

    @Override
    public String toString() {
        return directory.toString();
    }
}
