package com.sun.codemodel.internal.writer;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

import com.sun.codemodel.internal.CodeWriter;
import com.sun.codemodel.internal.JPackage;

/**
 * Writes all the source files under the specified file folder and
 * inserts a file prolog comment in each java source file.
 */
public class PrologCodeWriter extends FilterCodeWriter {

    /** prolog comment */
    private final String prolog;

    /**
     * @param core
     *      This CodeWriter will be used to actually create a storage for files.
     *      PrologCodeWriter simply decorates this underlying CodeWriter by
     *      adding prolog comments.
     * @param prolog
     *      Strings that will be added as comments.
     *      This string may contain newlines to produce multi-line comments.
     *      '//' will be inserted at the beginning of each line to make it
     *      a valid Java comment, so the caller can just pass strings like
     *      "abc\ndef"
     */
    public PrologCodeWriter( CodeWriter core, String prolog ) {
        super(core);
        this.prolog = prolog;
    }


    public Writer openSource(JPackage pkg, String fileName) throws IOException {
        Writer w = super.openSource(pkg,fileName);

        PrintWriter out = new PrintWriter(w);

        // write prolog if this is a java source file
        if( prolog != null ) {
            out.println( "//" );

            String s = prolog;
            int idx;
            while( (idx=s.indexOf('\n'))!=-1 ) {
                out.println("// "+ s.substring(0,idx) );
                s = s.substring(idx+1);
            }
            out.println("//");
            out.println();
        }
        out.flush();    // we can't close the stream for that would close the undelying stream.

        return w;
    }
}
