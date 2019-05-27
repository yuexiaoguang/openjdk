package jdk.nashorn.internal.objects;

import jdk.nashorn.internal.objects.annotations.Attribute;
import jdk.nashorn.internal.objects.annotations.Constructor;
import jdk.nashorn.internal.objects.annotations.Function;
import jdk.nashorn.internal.objects.annotations.Property;
import jdk.nashorn.internal.objects.annotations.ScriptClass;
import jdk.nashorn.internal.objects.annotations.Where;
import jdk.nashorn.internal.runtime.PropertyMap;
import jdk.nashorn.internal.runtime.ScriptObject;
import jdk.nashorn.internal.runtime.arrays.ArrayData;

/**
 * Int32 array for the TypedArray extension
 */
@ScriptClass("Int32Array")
public final class NativeInt32Array extends ArrayBufferView {
    /**
     * The size in bytes of each element in the array.
     */
    @Property(attributes = Attribute.NOT_ENUMERABLE | Attribute.NOT_WRITABLE | Attribute.NOT_CONFIGURABLE, where = Where.CONSTRUCTOR)
    public static final int BYTES_PER_ELEMENT = 4;

    // initialized by nasgen
    @SuppressWarnings("unused")
    private static PropertyMap $nasgenmap$;

    private static final Factory FACTORY = new Factory(BYTES_PER_ELEMENT) {
        @Override
        public ArrayBufferView construct(final NativeArrayBuffer buffer, final int byteOffset, final int length) {
            return new NativeInt32Array(buffer, byteOffset, length);
        }
        @Override
        public ArrayData createArrayData(final NativeArrayBuffer buffer, final int byteOffset, final int length) {
            return new Int32ArrayData(buffer, byteOffset, length);
        }
    };

    private static final class Int32ArrayData extends ArrayDataImpl {
        private Int32ArrayData(final NativeArrayBuffer buffer, final int byteOffset, final int elementLength) {
            super(buffer, byteOffset, elementLength);
        }

        @Override
        protected int byteIndex(final int index) {
            return index * BYTES_PER_ELEMENT + byteOffset;
        }

        @Override
        protected int getIntImpl(final int index) {
            final int byteIndex = byteIndex(index);
            final byte[] byteArray = buffer.getByteArray();
            return byteArray[byteIndex  ]       & 0x0000_00ff |
                   byteArray[byteIndex+1] <<  8 & 0x0000_ff00 |
                   byteArray[byteIndex+2] << 16 & 0x00ff_0000 |
                   byteArray[byteIndex+3] << 24 & 0xff00_0000 ;
        }

        @Override
        protected void setImpl(final int index, final int value) {
            final int byteIndex = byteIndex(index);
            @SuppressWarnings("MismatchedReadAndWriteOfArray")
            final byte[] byteArray = buffer.getByteArray();
            byteArray[byteIndex  ] = (byte)(value        & 0xff);
            byteArray[byteIndex+1] = (byte)(value >>>  8 & 0xff);
            byteArray[byteIndex+2] = (byte)(value >>> 16 & 0xff);
            byteArray[byteIndex+3] = (byte)(value >>> 24 & 0xff);
        }
    }

    /**
     * Constructor
     *
     * @param newObj is this typed array instantiated with the new operator
     * @param self   self reference
     * @param args   args
     *
     * @return new typed array
     */
    @Constructor(arity = 1)
    public static Object constructor(final boolean newObj, final Object self, final Object... args) {
        return constructorImpl(args, FACTORY);
    }

    NativeInt32Array(final NativeArrayBuffer buffer, final int byteOffset, final int length) {
        super(buffer, byteOffset, length);
    }

    @Override
    public String getClassName() {
        return "Int32Array";
    }

    @Override
    protected Factory factory() {
        return FACTORY;
    }

    /**
     * Set values
     * @param self   self reference
     * @param array  multiple values of array's type to set
     * @param offset optional start index, interpreted  0 if undefined
     * @return undefined
     */
    @Function(attributes = Attribute.NOT_ENUMERABLE)
    protected static Object set(final Object self, final Object array, final Object offset) {
        return ArrayBufferView.setImpl(self, array, offset);
    }

    /**
     * Returns a new TypedArray view of the ArrayBuffer store for this TypedArray,
     * referencing the elements at begin, inclusive, up to end, exclusive. If either
     * begin or end is negative, it refers to an index from the end of the array,
     * as opposed to from the beginning.
     * <p>
     * If end is unspecified, the subarray contains all elements from begin to the end
     * of the TypedArray. The range specified by the begin and end values is clamped to
     * the valid index range for the current array. If the computed length of the new
     * TypedArray would be negative, it is clamped to zero.
     * <p>
     * The returned TypedArray will be of the same type as the array on which this
     * method is invoked.
     *
     * @param self self reference
     * @param begin begin position
     * @param end end position
     *
     * @return sub array
     */
    @Function(attributes = Attribute.NOT_ENUMERABLE)
    protected static Object subarray(final Object self, final Object begin, final Object end) {
        return ArrayBufferView.subarrayImpl(self, begin, end);
    }

    @Override
    protected ScriptObject getPrototype(final Global global) {
        return global.getInt32ArrayPrototype();
    }
}
