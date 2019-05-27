package com.sun.tools.doclets.internal.toolkit;

import java.io.*;

import com.sun.javadoc.*;

/**
 * The interface for writing package summary output.
 *
 *  <p><b>This is NOT part of any supported API.
 *  If you write code that depends on this, you do so at your own risk.
 *  This code and its internal interfaces are subject to change or
 *  deletion without notice.</b>
 */
public interface PackageSummaryWriter {

    /**
     * Get the header for the summary.
     *
     * @param heading Package name.
     * @return the header to be added to the content tree
     */
    public abstract Content getPackageHeader(String heading);

    /**
     * Get the header for the package content.
     *
     * @return a content tree for the package content header
     */
    public abstract Content getContentHeader();

    /**
     * Get the header for the package summary.
     *
     * @return a content tree with the package summary header
     */
    public abstract Content getSummaryHeader();

    /**
     * Adds the table of classes to the documentation tree.
     *
     * @param classes the array of classes to document.
     * @param label the label for this table.
     * @param tableSummary the summary string for the table
     * @param tableHeader array of table headers
     * @param summaryContentTree the content tree to which the summaries will be added
     */
    public abstract void addClassesSummary(ClassDoc[] classes, String label,
            String tableSummary, String[] tableHeader, Content summaryContentTree);

    /**
     * Adds the package description from the "packages.html" file to the documentation
     * tree.
     *
     * @param packageContentTree the content tree to which the package description
     *                           will be added
     */
    public abstract void addPackageDescription(Content packageContentTree);

    /**
     * Adds the tag information from the "packages.html" file to the documentation
     * tree.
     *
     * @param packageContentTree the content tree to which the package tags will
     *                           be added
     */
    public abstract void addPackageTags(Content packageContentTree);

    /**
     * Adds the footer to the documentation tree.
     *
     * @param contentTree the tree to which the footer will be added
     */
    public abstract void addPackageFooter(Content contentTree);

    /**
     * Print the package summary document.
     *
     * @param contentTree the content tree that will be printed
     */
    public abstract void printDocument(Content contentTree) throws IOException;

    /**
     * Close the writer.
     */
    public abstract void close() throws IOException;

}
