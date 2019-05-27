package com.sun.istack.internal.localization;

import java.util.Arrays;

public final class LocalizableMessage implements Localizable {

    private final String _bundlename;
    private final String _key;
    private final Object[] _args;

    public LocalizableMessage(String bundlename, String key, Object... args) {
        _bundlename = bundlename;
        _key = key;
        if(args==null)
            args = new Object[0];
        _args = args;
    }

    public String getKey() {
        return _key;
    }

    public Object[] getArguments() {
        return Arrays.copyOf(_args, _args.length);
    }

    public String getResourceBundleName() {
        return _bundlename;
    }
}
