package com.sun.tools.internal.xjc.generator.annotation.spec;

import javax.xml.bind.annotation.XmlSeeAlso;
import com.sun.codemodel.internal.JAnnotationWriter;
import com.sun.codemodel.internal.JType;

/**
 * <p><b>
 *     Auto-generated, do not edit.
 * </b></p>
 */
public interface XmlSeeAlsoWriter
    extends JAnnotationWriter<XmlSeeAlso>
{


    XmlSeeAlsoWriter value(Class value);

    XmlSeeAlsoWriter value(JType value);

}
