package com.sun.org.glassfish.external.probe.provider;

public enum PluginPoint {

    SERVER ("server", "server"),
    APPLICATIONS ("applications", "server/applications");

    String name;
    String path;

    PluginPoint(String lname, String lpath) {
        name = lname;
        path = lpath;
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }
}
