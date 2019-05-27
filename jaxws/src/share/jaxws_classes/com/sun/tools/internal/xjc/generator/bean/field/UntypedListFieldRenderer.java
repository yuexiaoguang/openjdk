package com.sun.tools.internal.xjc.generator.bean.field;

import com.sun.codemodel.internal.JClass;
import com.sun.tools.internal.xjc.generator.bean.ClassOutlineImpl;
import com.sun.tools.internal.xjc.model.CPropertyInfo;
import com.sun.tools.internal.xjc.outline.FieldOutline;

public final class UntypedListFieldRenderer implements FieldRenderer {

    private JClass coreList;
    private boolean dummy;
    private boolean content;

    protected UntypedListFieldRenderer( JClass coreList) {
        this(coreList, false, false);
    }

    protected UntypedListFieldRenderer( JClass coreList, boolean dummy, boolean content) {
        this.coreList = coreList;
        this.dummy = dummy;
        this.content = content;
    }

    public FieldOutline generate(ClassOutlineImpl context, CPropertyInfo prop) {
        if (dummy) {
            return new DummyListField(context, prop, coreList);
        }
        if (content) {
            return new ContentListField(context, prop, coreList);
        }
        return new UntypedListField(context, prop, coreList);
    }
}
