package com.sun.codemodel.internal.fmt;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

import com.sun.codemodel.internal.JResourceFile;


/**
 * Simple text file.
 */
public class JTextFile extends JResourceFile
{
    public JTextFile( String name ) {
        super(name);
    }

    private String contents = null;

    public void setContents( String _contents ) {
        this.contents = _contents;
    }

    public void build( OutputStream out ) throws IOException {
        Writer w = new OutputStreamWriter(out);
        w.write(contents);
        w.close();
    }
}
