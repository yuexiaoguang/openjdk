package com.sun.tools.doclets.internal.toolkit.taglets;

import com.sun.javadoc.*;
import com.sun.tools.doclets.internal.toolkit.Content;

/**
 * An inline Taglet representing {&#064;docRoot}.  This taglet is
 * used to get the relative path to the document's root output
 * directory.
 *
 *  <p><b>This is NOT part of any supported API.
 *  If you write code that depends on this, you do so at your own risk.
 *  This code and its internal interfaces are subject to change or
 *  deletion without notice.</b>
 */
public class DocRootTaglet extends BaseInlineTaglet {


    /**
     * Construct a new DocRootTaglet.
     */
    public DocRootTaglet() {
        name = "docRoot";
    }

    /**
     * Given a <code>Doc</code> object, check if it holds any tags of
     * this type.  If it does, return the string representing the output.
     * If it does not, return null.
     * @param tag a tag representing the custom tag.
     * @param writer a {@link TagletWriter} Taglet writer.
     * @return the string representation of this <code>Tag</code>.
     */
    public Content getTagletOutput(Tag tag, TagletWriter writer) {
        return writer.getDocRootOutput();
    }
}
