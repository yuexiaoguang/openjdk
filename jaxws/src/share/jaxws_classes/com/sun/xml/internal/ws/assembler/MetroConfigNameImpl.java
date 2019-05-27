package com.sun.xml.internal.ws.assembler;

/**
* TODO: Write some description here ...
*/
public class MetroConfigNameImpl implements MetroConfigName {

    private final String defaultFileName;
    private final String appFileName;

    public MetroConfigNameImpl(String defaultFileName, String appFileName) {
        this.defaultFileName = defaultFileName;
        this.appFileName = appFileName;
    }

    @Override
    public String getDefaultFileName() {
        return defaultFileName;
    }

    @Override
    public String getAppFileName() {
        return appFileName;
    }
}
