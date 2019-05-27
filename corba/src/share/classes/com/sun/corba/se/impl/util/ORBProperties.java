package com.sun.corba.se.impl.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class ORBProperties {

    public static final String ORB_CLASS =
        "org.omg.CORBA.ORBClass=com.sun.corba.se.impl.orb.ORBImpl";
    public static final String ORB_SINGLETON_CLASS =
        "org.omg.CORBA.ORBSingletonClass=com.sun.corba.se.impl.orb.ORBSingleton";

    public static void main (String[] args) {

        try {
            // Check if orb.properties exists
            String javaHome = System.getProperty("java.home");
            File propFile = new File(javaHome + File.separator
                                     + "lib" + File.separator
                                     + "orb.properties");

            if (propFile.exists())
                return;

            // Write properties to orb.properties
            FileOutputStream out = new FileOutputStream(propFile);
            PrintWriter pw = new PrintWriter(out);

            try {
                pw.println(ORB_CLASS);
                pw.println(ORB_SINGLETON_CLASS);
            } finally {
                pw.close();
                out.close();
            }

        } catch (Exception ex) { }

    }
}
