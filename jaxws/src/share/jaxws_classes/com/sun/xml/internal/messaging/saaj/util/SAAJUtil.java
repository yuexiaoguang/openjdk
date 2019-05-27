package com.sun.xml.internal.messaging.saaj.util;

import java.security.AccessControlException;

public final class SAAJUtil {

    public static boolean getSystemBoolean(String arg) {
        try {
            return Boolean.getBoolean(arg);
        } catch (AccessControlException ex) {
            return false;
        }
    }

    public static String getSystemProperty(String arg) {
        try {
            return System.getProperty(arg);
        } catch (SecurityException ex) {
            return null;
        }
    }
}
