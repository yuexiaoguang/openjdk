package com.sun.tools.internal.xjc.generator.annotation.spec;

import javax.xml.bind.annotation.XmlElementRef;
import com.sun.codemodel.internal.JAnnotationWriter;
import com.sun.codemodel.internal.JType;

/**
 * <p><b>
 *     Auto-generated, do not edit.
 * </b></p>
 */
public interface XmlElementRefWriter
    extends JAnnotationWriter<XmlElementRef>
{


    XmlElementRefWriter name(String value);

    XmlElementRefWriter type(Class value);

    XmlElementRefWriter type(JType value);

    XmlElementRefWriter namespace(String value);

    XmlElementRefWriter required(boolean value);

}
