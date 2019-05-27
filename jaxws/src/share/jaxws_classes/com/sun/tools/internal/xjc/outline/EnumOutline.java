package com.sun.tools.internal.xjc.outline;

import java.util.ArrayList;
import java.util.List;

import com.sun.codemodel.internal.JDefinedClass;
import com.sun.tools.internal.xjc.model.CEnumLeafInfo;
import com.sun.istack.internal.NotNull;

/**
 * Outline object that provides per-{@link CEnumLeafInfo} information
 * for filling in methods/fields for a bean.
 *
 * This object can be obtained from {@link Outline}
 */
public abstract class EnumOutline {

    /**
     * This {@link EnumOutline} holds information about this {@link CEnumLeafInfo}.
     */
    public final CEnumLeafInfo target;

    /**
     * The generated enum class.
     */
    public final JDefinedClass clazz;

    /**
     * Constants.
     */
    public final List<EnumConstantOutline> constants = new ArrayList<EnumConstantOutline>();

    /**
     * {@link PackageOutline} that contains this class.
     */
    public @NotNull
    PackageOutline _package() {
        return parent().getPackageContext(clazz._package());
    }

    /**
     * A {@link Outline} that encloses all the class outlines.
     */
    public abstract @NotNull Outline parent();

    protected EnumOutline(CEnumLeafInfo target, JDefinedClass clazz) {
        this.target = target;
        this.clazz = clazz;
    }
}
