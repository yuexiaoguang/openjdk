package jdk.nashorn.internal.objects;

import java.util.Arrays;
import jdk.nashorn.internal.objects.annotations.Attribute;
import jdk.nashorn.internal.objects.annotations.Constructor;
import jdk.nashorn.internal.objects.annotations.Function;
import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.ScriptClass;
import jdk.nashorn.internal.runtime.JSType;
import jdk.nashorn.internal.runtime.PropertyMap;
import jdk.nashorn.internal.runtime.ScriptObject;
import jdk.nashorn.internal.runtime.ScriptRuntime;

@ScriptClass("ArrayBuffer")
final class NativeArrayBuffer extends ScriptObject {
    private final byte[] buffer;

    // initialized by nasgen
    private static PropertyMap $nasgenmap$;

    static PropertyMap getInitialMap() {
        return $nasgenmap$;
    }

    @Constructor(arity = 1)
    public static Object constructor(final boolean newObj, final Object self, final Object... args) {
        if (args.length == 0) {
            throw new RuntimeException("missing length argument");
        }

        return new NativeArrayBuffer(JSType.toInt32(args[0]));
    }

    protected NativeArrayBuffer(final byte[] byteArray, final Global global) {
        super(global.getArrayBufferPrototype(), global.getArrayBufferMap());
        this.buffer = byteArray;
    }

    protected NativeArrayBuffer(final byte[] byteArray) {
        this(byteArray, Global.instance());
    }

    protected NativeArrayBuffer(final int byteLength) {
        this(new byte[byteLength]);
    }

    protected NativeArrayBuffer(final NativeArrayBuffer other, final int begin, final int end) {
        this(Arrays.copyOfRange(other.buffer, begin, end));
    }

    @Override
    public String getClassName() {
        return "ArrayBuffer";
    }

    @Getter(attributes = Attribute.NOT_ENUMERABLE | Attribute.NOT_WRITABLE | Attribute.NOT_CONFIGURABLE)
    public static Object byteLength(final Object self) {
        return ((NativeArrayBuffer)self).buffer.length;
    }

    @Function(attributes = Attribute.NOT_ENUMERABLE)
    public static Object slice(final Object self, final Object begin0, final Object end0) {
        final NativeArrayBuffer arrayBuffer = (NativeArrayBuffer)self;
        int begin = JSType.toInt32(begin0);
        int end = end0 != ScriptRuntime.UNDEFINED ? JSType.toInt32(end0) : arrayBuffer.getByteLength();
        begin = adjustIndex(begin, arrayBuffer.getByteLength());
        end = adjustIndex(end, arrayBuffer.getByteLength());
        return new NativeArrayBuffer((NativeArrayBuffer) self, begin, Math.max(end, begin));
    }

    /**
     * If index is negative, it refers to an index from the end of the array, as
     * opposed to from the beginning. The index is clamped to the valid index
     * range for the array.
     *
     * @param index  The index.
     * @param length The length of the array.
     * @return valid index index in the range [0, length).
     */
    static int adjustIndex(final int index, final int length) {
        if (index < 0) {
            return clamp(index + length, length);
        }
        return clamp(index, length);
    }

    /**
     * Clamp index into the range [0, length).
     */
    private static int clamp(final int index, final int length) {
        if (index < 0) {
            return 0;
        } else if (index > length) {
            return length;
        }
        return index;
    }

    public byte[] getByteArray() {
        return buffer;
    }

    public int getByteLength() {
        return buffer.length;
    }
}
