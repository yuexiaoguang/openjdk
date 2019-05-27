package com.sun.tools.doclets.internal.toolkit;

import java.io.*;
import com.sun.javadoc.*;

/**
 * The interface for writing field output.
 *
 *  <p><b>This is NOT part of any supported API.
 *  If you write code that depends on this, you do so at your own risk.
 *  This code and its internal interfaces are subject to change or
 *  deletion without notice.</b>
 */
public interface FieldWriter {

    /**
     * Get the field details tree header.
     *
     * @param classDoc the class being documented
     * @param memberDetailsTree the content tree representing member details
     * @return content tree for the field details header
     */
    public Content getFieldDetailsTreeHeader(ClassDoc classDoc,
            Content memberDetailsTree);

    /**
     * Get the field documentation tree header.
     *
     * @param field the constructor being documented
     * @param fieldDetailsTree the content tree representing field details
     * @return content tree for the field documentation header
     */
    public Content getFieldDocTreeHeader(FieldDoc field,
            Content fieldDetailsTree);

    /**
     * Get the signature for the given field.
     *
     * @param field the field being documented
     * @return content tree for the field signature
     */
    public Content getSignature(FieldDoc field);

    /**
     * Add the deprecated output for the given field.
     *
     * @param field the field being documented
     * @param fieldDocTree content tree to which the deprecated information will be added
     */
    public void addDeprecated(FieldDoc field, Content fieldDocTree);

    /**
     * Add the comments for the given field.
     *
     * @param field the field being documented
     * @param fieldDocTree the content tree to which the comments will be added
     */
    public void addComments(FieldDoc field, Content fieldDocTree);

    /**
     * Add the tags for the given field.
     *
     * @param field the field being documented
     * @param fieldDocTree the content tree to which the tags will be added
     */
    public void addTags(FieldDoc field, Content fieldDocTree);

    /**
     * Get the field details tree.
     *
     * @param memberDetailsTree the content tree representing member details
     * @return content tree for the field details
     */
    public Content getFieldDetails(Content memberDetailsTree);

    /**
     * Get the field documentation.
     *
     * @param fieldDocTree the content tree representing field documentation
     * @param isLastContent true if the content to be added is the last content
     * @return content tree for the field documentation
     */
    public Content getFieldDoc(Content fieldDocTree, boolean isLastContent);

    /**
     * Close the writer.
     */
    public void close() throws IOException;
}
