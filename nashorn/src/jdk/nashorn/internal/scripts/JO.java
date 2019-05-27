package jdk.nashorn.internal.scripts;

import jdk.nashorn.internal.runtime.PropertyMap;
import jdk.nashorn.internal.runtime.ScriptObject;

/**
 * Empty object class.
 */
public class JO extends ScriptObject {

    private static final PropertyMap map$ = PropertyMap.newMap().setIsShared();

    /**
     * Returns the initial property map to be used.
     * @return the initial property map.
     */
    public static PropertyMap getInitialMap() {
        return map$;
    }

    /**
     * Constructor given an initial property map
     *
     * @param map the property map
     */
    public JO(final PropertyMap map) {
        super(map);
    }

    /**
     * Constructor given an initial prototype and an initial property map.
     *
     * @param proto the prototype object
     * @param map the property map
     */
    public JO(final ScriptObject proto, final PropertyMap map) {
        super(proto, map);
    }

    /**
     * A method handle of this method is passed to the ScriptFunction constructor.
     *
     * @param map  the property map to use for allocatorMap
     *
     * @return newly allocated ScriptObject
     */
    public static ScriptObject allocate(final PropertyMap map) {
        return new JO(map);
    }
}
