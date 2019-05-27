package com.sun.tools.javah;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.Locale;
import java.util.Set;
import javax.lang.model.SourceVersion;
import javax.tools.DiagnosticListener;
import javax.tools.JavaFileManager;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;

/*
 * <p><b>This is NOT part of any supported API.
 * If you write code that depends on this, you do so at your own
 * risk.  This code and its internal interfaces are subject to change
 * or deletion without notice.</b></p>
 */
public class JavahTool implements NativeHeaderTool {

    public NativeHeaderTask getTask(Writer out,
            JavaFileManager fileManager,
            DiagnosticListener<? super JavaFileObject> diagnosticListener,
            Iterable<String> options,
            Iterable<String> classes) {
        return new JavahTask(out, fileManager, diagnosticListener, options, classes);
    }

    public StandardJavaFileManager getStandardFileManager(DiagnosticListener<? super JavaFileObject> diagnosticListener, Locale locale, Charset charset) {
        return JavahTask.getDefaultFileManager(diagnosticListener, null);
    }

    public int run(InputStream in, OutputStream out, OutputStream err, String... arguments) {
        JavahTask t = new JavahTask(
                JavahTask.getPrintWriterForStream(out),
                null,
                null,
                Arrays.asList(arguments),
                null);
        return (t.run() ? 0 : 1);
    }

    public Set<SourceVersion> getSourceVersions() {
        return EnumSet.allOf(SourceVersion.class);
    }

    public int isSupportedOption(String option) {
        JavahTask.Option[] options = JavahTask.recognizedOptions;
        for (int i = 0; i < options.length; i++) {
            if (options[i].matches(option))
                return (options[i].hasArg ? 1 : 0);
        }
        return -1;
    }
}
