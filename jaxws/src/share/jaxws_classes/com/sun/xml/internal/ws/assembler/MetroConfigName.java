package com.sun.xml.internal.ws.assembler;

/**
 * This interface is used to get the file name used for the metro configuration file.
 * This allows multiple configurations of metro in a single VM.
 */
public interface MetroConfigName {
    public String getDefaultFileName();

    public String getAppFileName();

}
