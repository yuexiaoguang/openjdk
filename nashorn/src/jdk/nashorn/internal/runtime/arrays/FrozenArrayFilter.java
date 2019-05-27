package jdk.nashorn.internal.runtime.arrays;

import static jdk.nashorn.internal.runtime.ECMAErrors.typeError;

import jdk.nashorn.internal.runtime.GlobalObject;
import jdk.nashorn.internal.runtime.PropertyDescriptor;

/**
 * ArrayData after the array has been frozen by Object.freeze call.
 */
final class FrozenArrayFilter extends SealedArrayFilter {
    FrozenArrayFilter(final ArrayData underlying) {
        super(underlying);
    }

    @Override
    public ArrayData copy() {
        return this;
    }

    @Override
    public PropertyDescriptor getDescriptor(final GlobalObject global, final int index) {
        return global.newDataDescriptor(getObject(index), false, true, false);
    }

    @Override
    public ArrayData set(final int index, final int value, final boolean strict) {
        if (strict) {
            throw typeError("cant.set.property", Integer.toString(index), "frozen array");
        }
        return this;
    }

    @Override
    public ArrayData set(final int index, final long value, final boolean strict) {
        if (strict) {
            throw typeError("cant.set.property", Integer.toString(index), "frozen array");
        }
        return this;
    }

    @Override
    public ArrayData set(final int index, final double value, final boolean strict) {
        if (strict) {
            throw typeError("cant.set.property", Integer.toString(index), "frozen array");
        }
        return this;
    }

    @Override
    public ArrayData set(final int index, final Object value, final boolean strict) {
        if (strict) {
            throw typeError("cant.set.property", Integer.toString(index), "frozen array");
        }
        return this;
    }
}
