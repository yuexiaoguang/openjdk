package com.sun.codemodel.internal.writer;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.Writer;

import com.sun.codemodel.internal.CodeWriter;
import com.sun.codemodel.internal.JPackage;

/**
 * Filter CodeWriter that writes a progress message to the specified
 * PrintStream.
 */
public class ProgressCodeWriter extends FilterCodeWriter {
    public ProgressCodeWriter( CodeWriter output, PrintStream progress ) {
        super(output);
        this.progress = progress;
        if(progress==null)
            throw new IllegalArgumentException();
    }

    private final PrintStream progress;

    public OutputStream openBinary(JPackage pkg, String fileName) throws IOException {
        report(pkg, fileName);
        return super.openBinary(pkg,fileName);
    }

    public Writer openSource(JPackage pkg, String fileName) throws IOException {
        report(pkg, fileName);
        return super.openSource(pkg,fileName);
    }

    private void report(JPackage pkg, String fileName) {
        if(pkg.isUnnamed()) progress.println(fileName);
        else
            progress.println(
                pkg.name().replace('.',File.separatorChar)
                    +File.separatorChar+fileName);
    }

}
