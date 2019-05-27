package com.sun.tools.javap;

import java.io.PrintWriter;

/**
 *  Main entry point.
 *
 *  <p><b>This is NOT part of any supported API.
 *  If you write code that depends on this, you do so at your own risk.
 *  This code and its internal interfaces are subject to change or
 *  deletion without notice.</b>
 */
public class Main {
    /**
     * Main entry point for the launcher.
     * Note: This method calls System.exit.
     * @param args command line arguments
     */
    public static void main(String[] args) {
        JavapTask t = new JavapTask();
        int rc = t.run(args);
        System.exit(rc);
    }

    /**
     * Entry point that does <i>not</i> call System.exit.
     * @param args command line arguments
     * @param out output stream
     * @return an exit code. 0 means success, non-zero means an error occurred.
     */
    public static int run(String[] args, PrintWriter out) {
        JavapTask t = new JavapTask();
        t.setLog(out);
        return t.run(args);
    }
}
