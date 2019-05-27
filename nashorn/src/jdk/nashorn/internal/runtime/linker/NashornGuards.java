package jdk.nashorn.internal.runtime.linker;

import static jdk.nashorn.internal.lookup.Lookup.MH;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import jdk.nashorn.internal.runtime.PropertyMap;
import jdk.nashorn.internal.runtime.ScriptFunction;
import jdk.nashorn.internal.runtime.ScriptObject;

/**
 * Constructor of method handles used to guard call sites.
 */
public final class NashornGuards {
    private static final MethodHandle IS_SCRIPTOBJECT          = findOwnMH("isScriptObject", boolean.class, Object.class);
    private static final MethodHandle IS_SCRIPTFUNCTION        = findOwnMH("isScriptFunction", boolean.class, Object.class);
    private static final MethodHandle IS_MAP                   = findOwnMH("isMap", boolean.class, Object.class, PropertyMap.class);
    private static final MethodHandle IS_INSTANCEOF_2          = findOwnMH("isInstanceOf2", boolean.class, Object.class, Class.class, Class.class);

    // don't create me!
    private NashornGuards() {
    }

    /**
     * Get the guard that checks if an item is a {@code ScriptObject}
     * @return method handle for guard
     */
    public static MethodHandle getScriptObjectGuard() {
        return IS_SCRIPTOBJECT;
    }

    /**
     * Get the guard that checks if an item is a {@code ScriptFunction}
     * @return method handle for guard
     */
    public static MethodHandle getScriptFunctionGuard() {
        return IS_SCRIPTFUNCTION;
    }

    /**
     * Get the guard that checks if a {@link PropertyMap} is equal to
     * a known map, using reference comparison
     *
     * @param map The map to check against. This will be bound to the guard method handle
     *
     * @return method handle for guard
     */
    public static MethodHandle getMapGuard(final PropertyMap map) {
        return MH.insertArguments(IS_MAP, 1, map);
    }

    /**
     * Get a guard that checks if in item is an instance of either of two classes.
     *
     * @param class1 the first class
     * @param class2 the second class
     * @return method handle for guard
     */
    public static MethodHandle getInstanceOf2Guard(final Class<?> class1, final Class<?> class2) {
        return MH.insertArguments(IS_INSTANCEOF_2, 1, class1, class2);
    }

    @SuppressWarnings("unused")
    private static boolean isScriptObject(final Object self) {
        return self instanceof ScriptObject;
    }

    @SuppressWarnings("unused")
    private static boolean isScriptFunction(final Object self) {
        return self instanceof ScriptFunction;
    }

    @SuppressWarnings("unused")
    private static boolean isMap(final Object self, final PropertyMap map) {
        return self instanceof ScriptObject && ((ScriptObject)self).getMap() == map;
    }

    @SuppressWarnings("unused")
    private static boolean isInstanceOf2(final Object self, final Class<?> class1, final Class<?> class2) {
        return class1.isInstance(self) || class2.isInstance(self);
    }

    private static MethodHandle findOwnMH(final String name, final Class<?> rtype, final Class<?>... types) {
        return MH.findStatic(MethodHandles.lookup(), NashornGuards.class, name, MH.type(rtype, types));
    }

}
