package com.sun.tools.internal.xjc.api.impl.s2j;

import javax.xml.namespace.QName;

import com.sun.codemodel.internal.JCodeModel;
import com.sun.codemodel.internal.JType;
import com.sun.tools.internal.xjc.api.Mapping;
import com.sun.tools.internal.xjc.api.Property;
import com.sun.tools.internal.xjc.model.CElementInfo;
import com.sun.tools.internal.xjc.outline.FieldOutline;

public /*for BSH*/ final class PropertyImpl implements Property {
    protected final FieldOutline fr;
    protected final QName elementName;
    protected final Mapping parent;
    protected final JCodeModel codeModel;

    PropertyImpl( Mapping parent, FieldOutline fr, QName elementName ) {
        this.parent = parent;
        this.fr = fr;
        this.elementName = elementName;
        this.codeModel = fr.getRawType().owner();
    }

    public final String name() {
        return fr.getPropertyInfo().getName(false);
    }

    /** Returns raw schema name for simpleType property. May return null for other types. */
    public final QName rawName() {
        if (fr instanceof ElementAdapter) {
            CElementInfo eInfo = ((ElementAdapter)fr).ei;
            if ((eInfo != null) && (eInfo.getProperty() != null)) {
                return eInfo.getProperty().getTypes().get(0).getTypeName();
            }
        }
        return null;
    }

    public final QName elementName() {
        return elementName;
    }

    public final JType type() {
        return fr.getRawType();
    }
}
