package com.sun.tools.internal.xjc.outline;

import com.sun.codemodel.internal.JEnumConstant;
import com.sun.tools.internal.xjc.generator.bean.BeanGenerator;
import com.sun.tools.internal.xjc.model.CEnumConstant;
import com.sun.tools.internal.xjc.model.CEnumLeafInfo;

/**
 * Outline object that provides per-{@link CEnumConstant} information.
 *
 * This object can be obtained from {@link EnumOutline}
 */
public abstract class EnumConstantOutline {
    /**
     * This {@link EnumOutline} holds information about this {@link CEnumLeafInfo}.
     */
    public final CEnumConstant target;

    /**
     * The generated enum constant.
     */
    public final JEnumConstant constRef;

    /**
     * Reserved for {@link BeanGenerator}.
     */
    protected EnumConstantOutline(CEnumConstant target, JEnumConstant constRef) {
        this.target = target;
        this.constRef = constRef;
    }
}
