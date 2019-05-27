package jdk.nashorn.internal.objects;

import static jdk.nashorn.internal.runtime.ScriptRuntime.UNDEFINED;
import static jdk.nashorn.internal.lookup.Lookup.MH;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import jdk.nashorn.internal.runtime.AccessorProperty;
import jdk.nashorn.internal.runtime.Property;
import jdk.nashorn.internal.runtime.PropertyMap;
import jdk.nashorn.internal.runtime.ScriptFunction;
import jdk.nashorn.internal.runtime.ScriptObject;

/**
 * Instances of this class serve as "prototype" object for script functions.
 * The purpose is to expose "constructor" property from "prototype". Also, nasgen
 * generated prototype classes extend from this class.
 */
public class PrototypeObject extends ScriptObject {
    private static final PropertyMap map$;

    private Object constructor;

    private static final MethodHandle GET_CONSTRUCTOR = findOwnMH("getConstructor", Object.class, Object.class);
    private static final MethodHandle SET_CONSTRUCTOR = findOwnMH("setConstructor", void.class, Object.class, Object.class);

    static {
        final ArrayList<Property> properties = new ArrayList<>(1);
        properties.add(AccessorProperty.create("constructor", Property.NOT_ENUMERABLE, GET_CONSTRUCTOR, SET_CONSTRUCTOR));
        map$ = PropertyMap.newMap(properties).setIsShared();
    }

    static PropertyMap getInitialMap() {
        return map$;
    }

    private PrototypeObject(final Global global, final PropertyMap map) {
        super(map != map$? map.addAll(global.getPrototypeObjectMap()) : global.getPrototypeObjectMap());
        setProto(global.getObjectPrototype());
    }

    PrototypeObject() {
        this(Global.instance(), map$);
    }

    /**
     * PropertyObject constructor
     *
     * @param map property map
     */
    PrototypeObject(final PropertyMap map) {
        this(Global.instance(), map);
    }

    PrototypeObject(final ScriptFunction func) {
        this(Global.instance(), map$);
        this.constructor = func;
    }

    /**
     * Get the constructor for this {@code PrototypeObject}
     * @param self self reference
     * @return constructor, probably, but not necessarily, a {@link ScriptFunction}
     */
    static Object getConstructor(final Object self) {
        return (self instanceof PrototypeObject) ?
            ((PrototypeObject)self).getConstructor() :
            UNDEFINED;
    }

    /**
     * Reset the constructor for this {@code PrototypeObject}
     * @param self self reference
     * @param constructor constructor, probably, but not necessarily, a {@link ScriptFunction}
     */
    static void setConstructor(final Object self, final Object constructor) {
        if (self instanceof PrototypeObject) {
            ((PrototypeObject)self).setConstructor(constructor);
        }
    }

    private Object getConstructor() {
        return constructor;
    }

    private void setConstructor(final Object constructor) {
        this.constructor = constructor;
    }

    private static MethodHandle findOwnMH(final String name, final Class<?> rtype, final Class<?>... types) {
        return MH.findStatic(MethodHandles.lookup(), PrototypeObject.class, name, MH.type(rtype, types));
    }
}
