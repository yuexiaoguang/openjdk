package jdk.nashorn.internal.objects;

import static jdk.nashorn.internal.runtime.ScriptRuntime.UNDEFINED;

import jdk.nashorn.internal.objects.annotations.Attribute;
import jdk.nashorn.internal.objects.annotations.Constructor;
import jdk.nashorn.internal.objects.annotations.Property;
import jdk.nashorn.internal.objects.annotations.ScriptClass;
import jdk.nashorn.internal.objects.annotations.Where;
import jdk.nashorn.internal.runtime.JSType;
import jdk.nashorn.internal.runtime.PropertyMap;
import jdk.nashorn.internal.runtime.ScriptObject;

/**
 * ECMA 15.11.6.5 TypeError
 */
@ScriptClass("Error")
public final class NativeTypeError extends ScriptObject {

    /** message property in instance */
    @Property(name = NativeError.MESSAGE)
    public Object instMessage;

    /** error name property */
    @Property(attributes = Attribute.NOT_ENUMERABLE, where = Where.PROTOTYPE)
    public Object name;

    /** ECMA 15.1.1.1 message property */
    @Property(attributes = Attribute.NOT_ENUMERABLE, where = Where.PROTOTYPE)
    public Object message;

    // initialized by nasgen
    private static PropertyMap $nasgenmap$;

    static PropertyMap getInitialMap() {
        return $nasgenmap$;
    }

    NativeTypeError(final Object msg, final Global global) {
        super(global.getTypeErrorPrototype(), global.getTypeErrorMap());
        if (msg != UNDEFINED) {
            this.instMessage = JSType.toString(msg);
        } else {
            delete(NativeError.MESSAGE, false);
        }
    }

    private NativeTypeError(final Object msg) {
        this(msg, Global.instance());
    }

    @Override
    public String getClassName() {
        return "Error";
    }

    /**
     * ECMA 15.11.6.5 TypeError
     *
     * Constructor
     *
     * @param newObj was this error instantiated with the new operator
     * @param self   self reference
     * @param msg    error message
     *
     * @return new TypeError
     */
    @Constructor(name = "TypeError")
    public static Object constructor(final boolean newObj, final Object self, final Object msg) {
        return new NativeTypeError(msg);
    }
}
