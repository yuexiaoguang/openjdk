package com.sun.org.apache.xml.internal.serializer.utils;

import java.util.Hashtable;

/**
 * This class contains utilities used by the serializer.
 *
 * This class is not a public API, it is only public because it is
 * used by com.sun.org.apache.xml.internal.serializer.
 */
public final class Utils
{
    /**
     * A singleton Messages object is used to load the
     * given resource bundle just once, it is
     * used by multiple transformations as long as the JVM stays up.
     */
    public static final com.sun.org.apache.xml.internal.serializer.utils.Messages messages=
        new com.sun.org.apache.xml.internal.serializer.utils.Messages(
            "com.sun.org.apache.xml.internal.serializer.utils.SerializerMessages");
}
