package com.sun.tools.doclets.formats.html.markup;

import java.io.IOException;
import java.io.Writer;

import com.sun.tools.doclets.internal.toolkit.Content;
import com.sun.tools.doclets.internal.toolkit.util.*;

/**
 * Class for generating string content for HTML tags of javadoc output.
 *
 *  <p><b>This is NOT part of any supported API.
 *  If you write code that depends on this, you do so at your own risk.
 *  This code and its internal interfaces are subject to change or
 *  deletion without notice.</b>
 */
public class StringContent extends Content {

    private StringBuilder stringContent;

    /**
     * Constructor to construct StringContent object.
     */
    public StringContent() {
        stringContent = new StringBuilder();
    }

    /**
     * Constructor to construct StringContent object with some initial content.
     *
     * @param initialContent initial content for the object
     */
    public StringContent(String initialContent) {
        stringContent = new StringBuilder();
        appendChars(initialContent);
    }

    /**
     * This method is not supported by the class.
     *
     * @param content content that needs to be added
     * @throws DocletAbortException this method will always throw a
     *                              DocletAbortException because it
     *                              is not supported.
     */
    @Override
    public void addContent(Content content) {
        throw new DocletAbortException("not supported");
    }

    /**
     * Adds content for the StringContent object.  The method escapes
     * HTML characters for the string content that is added.
     *
     * @param strContent string content to be added
     */
    @Override
    public void addContent(String strContent) {
        appendChars(strContent);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEmpty() {
        return (stringContent.length() == 0);
    }

    @Override
    public int charCount() {
        return RawHtml.charCount(stringContent.toString());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return stringContent.toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean write(Writer out, boolean atNewline) throws IOException {
        String s = stringContent.toString();
        out.write(s);
        return s.endsWith(DocletConstants.NL);
    }

    private void appendChars(String s) {
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            switch (ch) {
                case '<': stringContent.append("&lt;");  break;
                case '>': stringContent.append("&gt;");  break;
                case '&': stringContent.append("&amp;"); break;
                default:  stringContent.append(ch);      break;
            }
        }
    }
}
