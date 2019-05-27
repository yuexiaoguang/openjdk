package jdk.nashorn.internal.objects;

import jdk.nashorn.internal.objects.annotations.Attribute;
import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Property;
import jdk.nashorn.internal.objects.annotations.ScriptClass;
import jdk.nashorn.internal.objects.annotations.Setter;
import jdk.nashorn.internal.runtime.JSType;
import jdk.nashorn.internal.runtime.PropertyMap;
import jdk.nashorn.internal.runtime.regexp.RegExpResult;
import jdk.nashorn.internal.runtime.ScriptObject;
import jdk.nashorn.internal.runtime.arrays.ArrayData;

/**
 * Objects of this class are used to represent return values from
 * RegExp.prototype.exec method.
 */
@ScriptClass("RegExpExecResult")
public final class NativeRegExpExecResult extends ScriptObject {
    /** index property */
    @Property
    public Object index;

    /** input property */
    @Property
    public Object input;

    // initialized by nasgen
    private static PropertyMap $nasgenmap$;

    static PropertyMap getInitialMap() {
        return $nasgenmap$;
    }

    NativeRegExpExecResult(final RegExpResult result, final Global global) {
        super(global.getArrayPrototype(), global.getRegExpExecResultMap());
        setIsArray();
        this.setArray(ArrayData.allocate(result.getGroups().clone()));
        this.index = result.getIndex();
        this.input = result.getInput();
    }

    @Override
    public String getClassName() {
        return "Array";
    }

    /**
     * Length getter
     * @param self self reference
     * @return length property value
     */
    @Getter(attributes = Attribute.NOT_ENUMERABLE | Attribute.NOT_CONFIGURABLE)
    public static Object length(final Object self) {
        if (self instanceof ScriptObject) {
            return ((ScriptObject)self).getArray().length() & JSType.MAX_UINT;
        }

        return 0;
    }

    /**
     * Length setter
     * @param self self reference
     * @param length property value
     */
    @Setter(attributes = Attribute.NOT_ENUMERABLE | Attribute.NOT_CONFIGURABLE)
    public static void length(final Object self, final Object length) {
        if (self instanceof ScriptObject) {
            ((ScriptObject)self).setLength(NativeArray.validLength(length, true));
        }
    }
}
