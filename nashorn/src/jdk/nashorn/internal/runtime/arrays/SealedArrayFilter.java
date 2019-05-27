package jdk.nashorn.internal.runtime.arrays;

import static jdk.nashorn.internal.runtime.ECMAErrors.typeError;

import jdk.nashorn.internal.runtime.GlobalObject;
import jdk.nashorn.internal.runtime.PropertyDescriptor;

/**
 * ArrayData after the array has been sealed by Object.seal call.
 */
class SealedArrayFilter extends ArrayFilter {
    SealedArrayFilter(final ArrayData underlying) {
        super(underlying);
    }

    @Override
    public ArrayData copy() {
        return new SealedArrayFilter(underlying.copy());
    }

    @Override
    public ArrayData slice(final long from, final long to) {
        return getUnderlying().slice(from, to);
    }

    @Override
    public boolean canDelete(final int index, final boolean strict) {
        if (strict) {
            throw typeError("cant.delete.property", Integer.toString(index), "sealed array");
        }
        return false;
    }

    @Override
    public boolean canDelete(final long fromIndex, final long toIndex, final boolean strict) {
        return canDelete((int) fromIndex, strict);
    }

    @Override
    public PropertyDescriptor getDescriptor(final GlobalObject global, final int index) {
        return global.newDataDescriptor(getObject(index), false, true, true);
    }
}
