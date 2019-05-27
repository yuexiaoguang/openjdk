package jdk.nashorn.internal.runtime;

/**
 * If your ScriptObject or similar PropertyAccess implementation only provides the most
 * generic getters and setters and does nothing fancy with other, more primitive, types,
 * then it is convenient to inherit this class and just fill out the methods left
 * abstract
 */
public abstract class DefaultPropertyAccess implements PropertyAccess {

    @Override
    public int getInt(final Object key) {
        return JSType.toInt32(get(key));
    }

    @Override
    public int getInt(final double key) {
        return getInt(JSType.toObject(key));
    }

    @Override
    public int getInt(final long key) {
        return getInt(JSType.toObject(key));
    }

    @Override
    public int getInt(final int key) {
        return getInt(JSType.toObject(key));
    }

    @Override
    public long getLong(final Object key) {
        return JSType.toLong(get(key));
    }

    @Override
    public long getLong(final double key) {
        return getLong(JSType.toObject(key));
    }

    @Override
    public long getLong(final long key) {
        return getLong(JSType.toObject(key));
    }

    @Override
    public long getLong(final int key) {
        return getLong(JSType.toObject(key));
    }

    @Override
    public double getDouble(final Object key) {
        return JSType.toNumber(get(key));
    }

    @Override
    public double getDouble(final double key) {
        return getDouble(JSType.toObject(key));
    }

    @Override
    public double getDouble(final long key) {
        return getDouble(JSType.toObject(key));
    }

    @Override
    public double getDouble(final int key) {
        return getDouble(JSType.toObject(key));
    }

    @Override
    public abstract Object get(Object key);

    @Override
    public Object get(final double key) {
        return get(JSType.toObject(key));
    }

    @Override
    public Object get(final long key) {
        return get(JSType.toObject(key));
    }

    @Override
    public Object get(final int key) {
        return get(JSType.toObject(key));
    }

    @Override
    public void set(final double key, final int value, final boolean strict) {
        set(JSType.toObject(key), JSType.toObject(value), strict);
    }

    @Override
    public void set(final double key, final long value, final boolean strict) {
        set(JSType.toObject(key), JSType.toObject(value), strict);
    }

    @Override
    public void set(final double key, final double value, final boolean strict) {
        set(JSType.toObject(key), JSType.toObject(value), strict);
    }

    @Override
    public void set(final double key, final Object value, final boolean strict) {
        set(JSType.toObject(key), JSType.toObject(value), strict);
    }

    @Override
    public void set(final long key, final int value, final boolean strict) {
        set(JSType.toObject(key), JSType.toObject(value), strict);
    }

    @Override
    public void set(final long key, final long value, final boolean strict) {
        set(JSType.toObject(key), JSType.toObject(value), strict);
    }

    @Override
    public void set(final long key, final double value, final boolean strict) {
        set(JSType.toObject(key), JSType.toObject(value), strict);
    }

    @Override
    public void set(final long key, final Object value, final boolean strict) {
        set(JSType.toObject(key), value, strict);
    }

    @Override
    public void set(final int key, final int value, final boolean strict) {
        set(JSType.toObject(key), JSType.toObject(value), strict);
    }

    @Override
    public void set(final int key, final long value, final boolean strict) {
        set(JSType.toObject(key), JSType.toObject(value), strict);
    }

    @Override
    public void set(final int key, final double value, final boolean strict) {
        set(JSType.toObject(key), JSType.toObject(value), strict);
    }

    @Override
    public void set(final int key, final Object value, final boolean strict) {
        set(JSType.toObject(key), value, strict);
    }

    @Override
    public void set(final Object key, final int value, final boolean strict) {
        set(key, JSType.toObject(value), strict);
    }

    @Override
    public void set(final Object key, final long value, final boolean strict) {
        set(key, JSType.toObject(value), strict);
    }

    @Override
    public void set(final Object key, final double value, final boolean strict) {
        set(key, JSType.toObject(value), strict);
    }

    @Override
    public abstract void set(Object key, Object value, boolean strict);

    @Override
    public abstract boolean has(Object key);

    @Override
    public boolean has(final int key) {
        return has(JSType.toObject(key));
    }

    @Override
    public boolean has(final long key) {
        return has(JSType.toObject(key));
    }

    @Override
    public boolean has(final double key) {
        return has(JSType.toObject(key));
    }

    @Override
    public boolean hasOwnProperty(final int key) {
        return hasOwnProperty(JSType.toObject(key));
    }

    @Override
    public boolean hasOwnProperty(final long key) {
        return hasOwnProperty(JSType.toObject(key));
    }

    @Override
    public boolean hasOwnProperty(final double key) {
        return hasOwnProperty(JSType.toObject(key));
    }

    @Override
    public abstract boolean hasOwnProperty(Object key);

    @Override
    public boolean delete(final int key, final boolean strict) {
        return delete(JSType.toObject(key), strict);
    }

    @Override
    public boolean delete(final long key, final boolean strict) {
        return delete(JSType.toObject(key), strict);
    }

    @Override
    public boolean delete(final double key, final boolean strict) {
        return delete(JSType.toObject(key), strict);
    }

}
