package com.sun.xml.internal.bind.v2.runtime.property;

import javax.xml.namespace.QName;

import com.sun.xml.internal.bind.v2.runtime.JaxBeanInfo;
import com.sun.xml.internal.bind.v2.runtime.Name;

/**
 * Pair of {@link QName} and {@link JaxBeanInfo}.
 */
class TagAndType {
    final Name tagName;
    final JaxBeanInfo beanInfo;
    TagAndType(Name tagName, JaxBeanInfo beanInfo) {
        this.tagName = tagName;
        this.beanInfo = beanInfo;
    }
}
