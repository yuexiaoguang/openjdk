/*
 * This source code is provided to illustrate the usage of a given feature
 * or technique and has been deliberately simplified. Additional steps
 * required for a production-quality application, such as security checks,
 * input validation and proper error handling, might not be present in
 * this sample code.
 */
package com.sun.demo.scripting.jconsole;

import javax.swing.text.*;

/** This class implements a special type of document in which edits
 * can only be performed at the end, from "mark" to the end of the
 * document. This is used in ScriptShellPanel class as document for editor.
 */
public class EditableAtEndDocument extends PlainDocument {

    private static final long serialVersionUID = 5358116444851502167L;
    private int mark;

    @Override
    public void insertString(int offset, String text, AttributeSet a)
        throws BadLocationException {
        int len = getLength();
        super.insertString(len, text, a);
    }

    @Override
    public void remove(int offs, int len) throws BadLocationException {
        int start = offs;
        int end = offs + len;

        int markStart = mark;
        int markEnd = getLength();

        if ((end < markStart) || (start > markEnd)) {
            // no overlap
            return;
        }

        // Determine interval intersection
        int cutStart = Math.max(start, markStart);
        int cutEnd = Math.min(end, markEnd);
        super.remove(cutStart, cutEnd - cutStart);
    }

    public void setMark() {
        mark = getLength();
    }

    public String getMarkedText() throws BadLocationException {
        return getText(mark, getLength() - mark);
    }

    /** Used to reset the contents of this document */
    public void clear() {
        try {
            super.remove(0, getLength());
            setMark();
        } catch (BadLocationException e) {
        }
    }
}
