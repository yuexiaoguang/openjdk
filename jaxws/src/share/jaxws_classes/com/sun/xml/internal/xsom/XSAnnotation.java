package com.sun.xml.internal.xsom;

import org.xml.sax.Locator;
import com.sun.xml.internal.xsom.parser.AnnotationParser;

/**
 * <a href="http://www.w3.org/TR/xmlschema-1/#Annotation_details">
 * XML Schema annotation</a>.
 */
public interface XSAnnotation
{
    /**
     * Obtains the application-parsed annotation.
     * <p>
     * annotations are parsed by the user-specified
     * {@link AnnotationParser}.
     *
     * @return may return null
     */
    Object getAnnotation();

    /**
     * Sets the value to be returned by {@link #getAnnotation()}.
     *
     * @param o
     *      can be null.
     * @return
     *      old value that was replaced by the <tt>o</tt>.
     */
    Object setAnnotation(Object o);

    /**
     * Returns a location information of the annotation.
     */
    Locator getLocator();
}
