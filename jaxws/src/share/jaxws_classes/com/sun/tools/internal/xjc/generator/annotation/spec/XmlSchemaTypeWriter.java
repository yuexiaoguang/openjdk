package com.sun.tools.internal.xjc.generator.annotation.spec;

import javax.xml.bind.annotation.XmlSchemaType;
import com.sun.codemodel.internal.JAnnotationWriter;
import com.sun.codemodel.internal.JType;

/**
 * <p><b>
 *     Auto-generated, do not edit.
 * </b></p>
 */
public interface XmlSchemaTypeWriter
    extends JAnnotationWriter<XmlSchemaType>
{


    XmlSchemaTypeWriter name(String value);

    XmlSchemaTypeWriter type(Class value);

    XmlSchemaTypeWriter type(JType value);

    XmlSchemaTypeWriter namespace(String value);

}
