package com.sun.codemodel.internal.writer;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import com.sun.codemodel.internal.CodeWriter;
import com.sun.codemodel.internal.JPackage;

/**
 * Output all source files into a single stream with a little
 * formatting header in front of each file.
 *
 * This is primarily for human consumption of the generated source
 * code, such as to debug/test CodeModel or to quickly inspect the result.
 */
public class SingleStreamCodeWriter extends CodeWriter {

    private final PrintStream out;

    /**
     * @param os
     *      This stream will be closed at the end of the code generation.
     */
    public SingleStreamCodeWriter( OutputStream os ) {
        out = new PrintStream(os);
    }

    public OutputStream openBinary(JPackage pkg, String fileName) throws IOException {
        String pkgName = pkg.name();
        if(pkgName.length()!=0)     pkgName += '.';

        out.println(
            "-----------------------------------" + pkgName+fileName +
            "-----------------------------------");

        return new FilterOutputStream(out) {
            public void close() {
                // don't let this stream close
            }
        };
    }

    public void close() throws IOException {
        out.close();
    }

}
