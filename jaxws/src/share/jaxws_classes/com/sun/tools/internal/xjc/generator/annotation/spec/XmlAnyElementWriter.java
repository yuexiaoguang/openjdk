package com.sun.tools.internal.xjc.generator.annotation.spec;

import javax.xml.bind.annotation.XmlAnyElement;
import com.sun.codemodel.internal.JAnnotationWriter;
import com.sun.codemodel.internal.JType;

/**
 * <p><b>
 *     Auto-generated, do not edit.
 * </b></p>
 */
public interface XmlAnyElementWriter
    extends JAnnotationWriter<XmlAnyElement>
{


    XmlAnyElementWriter value(Class value);

    XmlAnyElementWriter value(JType value);

    XmlAnyElementWriter lax(boolean value);

}
