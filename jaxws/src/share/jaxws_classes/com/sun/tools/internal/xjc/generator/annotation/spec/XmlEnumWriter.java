package com.sun.tools.internal.xjc.generator.annotation.spec;

import javax.xml.bind.annotation.XmlEnum;
import com.sun.codemodel.internal.JAnnotationWriter;
import com.sun.codemodel.internal.JType;

/**
 * <p><b>
 *     Auto-generated, do not edit.
 * </b></p>
 */
public interface XmlEnumWriter
    extends JAnnotationWriter<XmlEnum>
{


    XmlEnumWriter value(Class value);

    XmlEnumWriter value(JType value);

}
