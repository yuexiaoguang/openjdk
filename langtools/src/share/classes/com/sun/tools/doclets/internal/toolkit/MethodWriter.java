package com.sun.tools.doclets.internal.toolkit;

import java.io.*;
import com.sun.javadoc.*;

/**
 * The interface for writing method output.
 *
 *  <p><b>This is NOT part of any supported API.
 *  If you write code that depends on this, you do so at your own risk.
 *  This code and its internal interfaces are subject to change or
 *  deletion without notice.</b>
 */
public interface MethodWriter {

    /**
     * Get the method details tree header.
     *
     * @param classDoc the class being documented
     * @param memberDetailsTree the content tree representing member details
     * @return content tree for the method details header
     */
    public Content getMethodDetailsTreeHeader(ClassDoc classDoc,
            Content memberDetailsTree);

    /**
     * Get the method documentation tree header.
     *
     * @param method the method being documented
     * @param methodDetailsTree the content tree representing method details
     * @return content tree for the method documentation header
     */
    public Content getMethodDocTreeHeader(MethodDoc method,
            Content methodDetailsTree);

    /**
     * Get the signature for the given method.
     *
     * @param method the method being documented
     * @return content tree for the method signature
     */
    public Content getSignature(MethodDoc method);

    /**
     * Add the deprecated output for the given method.
     *
     * @param method the method being documented
     * @param methodDocTree content tree to which the deprecated information will be added
     */
    public void addDeprecated(MethodDoc method, Content methodDocTree);

    /**
     * Add the comments for the given method.
     *
     * @param holder the holder type (not erasure) of the method
     * @param method the method being documented
     * @param methodDocTree the content tree to which the comments will be added
     */
    public void addComments(Type holder, MethodDoc method, Content methodDocTree);

    /**
     * Add the tags for the given method.
     *
     * @param method the method being documented
     * @param methodDocTree the content tree to which the tags will be added
     */
    public void addTags(MethodDoc method, Content methodDocTree);

    /**
     * Get the method details tree.
     *
     * @param methodDetailsTree the content tree representing method details
     * @return content tree for the method details
     */
    public Content getMethodDetails(Content methodDetailsTree);

    /**
     * Get the method documentation.
     *
     * @param methodDocTree the content tree representing method documentation
     * @param isLastContent true if the content to be added is the last content
     * @return content tree for the method documentation
     */
    public Content getMethodDoc(Content methodDocTree, boolean isLastContent);

    /**
     * Close the writer.
     */
    public void close() throws IOException;
}
