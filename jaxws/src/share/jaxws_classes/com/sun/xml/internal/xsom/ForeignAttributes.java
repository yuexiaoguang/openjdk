package com.sun.xml.internal.xsom;

import org.xml.sax.Attributes;
import org.xml.sax.Locator;
import org.relaxng.datatype.ValidationContext;

/**
 * Foreign attributes on schema elements.
 *
 * <p>
 * This is not a schema component as defined in the spec,
 * but this is often useful for a schema processing application.
 */
public interface ForeignAttributes extends Attributes {
    /**
     * Returns context information of the element to which foreign attributes
     * are attached.
     *
     * <p>
     * For example, this can be used to resolve relative references to other resources
     * (by using {@link ValidationContext#getBaseUri()}) or to resolve
     * namespace prefixes in the attribute values (by using {@link ValidationContext#resolveNamespacePrefix(String)}.
     *
     * @return
     *      always non-null.
     */
    ValidationContext getContext();

    /**
     * Returns the location of the element to which foreign attributes
     * are attached.
     */
    Locator getLocator();
}
