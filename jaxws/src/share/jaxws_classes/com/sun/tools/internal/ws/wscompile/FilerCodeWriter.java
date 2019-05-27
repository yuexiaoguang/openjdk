package com.sun.tools.internal.ws.wscompile;

import com.sun.codemodel.internal.JPackage;

import javax.annotation.processing.Filer;
import java.io.File;
import java.io.IOException;
import java.io.Writer;

/**
 * Writes all the source files using the specified Filer.
 */
public class FilerCodeWriter extends WSCodeWriter {

    /** The Filer used to create files. */
    private final Filer filer;

    private Writer w;

    public FilerCodeWriter(File outDir, Options options) throws IOException {
        super(outDir, options);
        this.filer = options.filer;
    }

    public Writer openSource(JPackage pkg, String fileName) throws IOException {
        String tmp = fileName.substring(0, fileName.length()-5);
        if (pkg.name() != null && ! "".equals(pkg.name())) {
                w = filer.createSourceFile(pkg.name() + "." + tmp).openWriter();
        } else {
                w = filer.createSourceFile(tmp).openWriter();
        }
        return w;
    }


    public void close() throws IOException {
        super.close();
        if (w != null)
            w.close();
        w = null;
    }
}
