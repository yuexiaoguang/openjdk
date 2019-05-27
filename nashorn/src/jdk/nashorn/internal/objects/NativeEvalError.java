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
 * ECMA 15.11.6.1 EvalError
 */
@ScriptClass("Error")
public final class NativeEvalError extends ScriptObject {

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

    private NativeEvalError(final Object msg, final ScriptObject proto, final PropertyMap map) {
        super(proto, map);
        if (msg != UNDEFINED) {
            this.instMessage = JSType.toString(msg);
        } else {
            this.delete(NativeError.MESSAGE, false);
        }
    }

    NativeEvalError(final Object msg, final Global global) {
        this(msg, global.getEvalErrorPrototype(), global.getEvalErrorMap());
    }

    private NativeEvalError(final Object msg) {
        this(msg, Global.instance());
    }

    @Override
    public String getClassName() {
        return "Error";
    }

    /**
     * ECMA 15.11.6.1 EvalError
     *
     * Constructor
     *
     * @param newObj was this error instantiated with the new operator
     * @param self   self reference
     * @param msg    error message
     *
     * @return new EvalError
     */
    @Constructor(name = "EvalError")
    public static Object constructor(final boolean newObj, final Object self, final Object msg) {
        return new NativeEvalError(msg);
    }
}
