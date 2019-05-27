package com.sun.istack.internal.localization;

/**
 * {@link Localizable} that wraps a non-localizable string.
 */
public final class NullLocalizable implements Localizable {
    private final String msg;

    public NullLocalizable(String msg) {
        if(msg==null)
            throw new IllegalArgumentException();
        this.msg = msg;
    }

    public String getKey() {
        return Localizable.NOT_LOCALIZABLE;
    }
    public Object[] getArguments() {
        return new Object[]{msg};
    }
    public String getResourceBundleName() {
        return "";
    }
}
