package com.sun.tools.internal.jxc;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * CLI entry point to schemagen that checks for JDK 5.0
 */
public class SchemaGeneratorFacade {

    public static void main(String[] args) throws Throwable {
        try {
            ClassLoader cl = SecureLoader.getClassClassLoader(SchemaGeneratorFacade.class);
            if(cl==null)    cl = SecureLoader.getSystemClassLoader();

            Class driver = cl.loadClass("com.sun.tools.internal.jxc.SchemaGenerator");
            Method mainMethod = driver.getDeclaredMethod("main", new Class[]{String[].class});
            try {
                mainMethod.invoke(null,new Object[]{args});
            } catch (IllegalAccessException e) {
                throw e;
            } catch (InvocationTargetException e) {
                if(e.getTargetException()!=null)
                    throw e.getTargetException();
            }
        } catch (UnsupportedClassVersionError e) {
            System.err.println("schemagen requires JDK 6.0 or later. Please download it from http://www.oracle.com/technetwork/java/javase/downloads");
        }
    }
}
