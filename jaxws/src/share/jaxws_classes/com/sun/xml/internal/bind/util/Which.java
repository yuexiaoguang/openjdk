package com.sun.xml.internal.bind.util;

import java.net.URL;

/**
 * Finds out where a class file is loaded from.
 */
public class Which {

    public static String which( Class clazz ) {
        return which( clazz.getName(), SecureLoader.getClassClassLoader(clazz));
    }

    /**
     * Search the specified classloader for the given classname.
     *
     * @param classname the fully qualified name of the class to search for
     * @param loader the classloader to search
     * @return the source location of the resource, or null if it wasn't found
     */
    public static String which(String classname, ClassLoader loader) {

        String classnameAsResource = classname.replace('.', '/') + ".class";

        if(loader == null) {
            loader = SecureLoader.getSystemClassLoader();
        }

        URL it = loader.getResource(classnameAsResource);
        if (it != null) {
            return it.toString();
        } else {
            return null;
        }
    }

}
