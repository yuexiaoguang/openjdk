package com.sun.tools.internal.xjc.api.util;

import javax.tools.StandardLocation;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;

import com.sun.codemodel.internal.CodeWriter;
import com.sun.codemodel.internal.JPackage;
import javax.annotation.processing.Filer;

import static javax.tools.StandardLocation.CLASS_PATH;
import static javax.tools.StandardLocation.SOURCE_PATH;

/**
 * {@link CodeWriter} that generates source code to {@link Filer}.
 */
public final class FilerCodeWriter extends CodeWriter {

    private final Filer filer;

    public FilerCodeWriter(Filer filer) {
        this.filer = filer;
    }

    public OutputStream openBinary(JPackage pkg, String fileName) throws IOException {
        StandardLocation loc;
        if(fileName.endsWith(".java")) {
            // Annotation Processing doesn't do the proper Unicode escaping on Java source files,
            // so we can't rely on Filer.createSourceFile.
            loc = SOURCE_PATH;
        } else {
            // put non-Java files directly to the output folder
            loc = CLASS_PATH;
        }
        return filer.createResource(loc, pkg.name(), fileName).openOutputStream();
    }

    public Writer openSource(JPackage pkg, String fileName) throws IOException {
        String name;
        if(pkg.isUnnamed())
            name = fileName;
        else
            name = pkg.name()+'.'+fileName;

        name = name.substring(0,name.length()-5);   // strip ".java"

        return filer.createSourceFile(name).openWriter();
    }

    public void close() {
        ; // noop
    }
}
