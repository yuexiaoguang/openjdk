package com.sun.tools.internal.xjc.generator.annotation.spec;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import com.sun.codemodel.internal.JAnnotationWriter;
import com.sun.codemodel.internal.JType;

/**
 * <p><b>
 *     Auto-generated, do not edit.
 * </b></p>
 */
public interface XmlJavaTypeAdapterWriter
    extends JAnnotationWriter<XmlJavaTypeAdapter>
{


    XmlJavaTypeAdapterWriter type(Class value);

    XmlJavaTypeAdapterWriter type(JType value);

    XmlJavaTypeAdapterWriter value(Class value);

    XmlJavaTypeAdapterWriter value(JType value);

}
