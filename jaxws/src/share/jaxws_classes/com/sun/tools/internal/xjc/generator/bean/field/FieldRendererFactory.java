package com.sun.tools.internal.xjc.generator.bean.field;

import com.sun.tools.internal.xjc.Options;
import com.sun.codemodel.internal.JClass;

/**
 * Factory for {@link FieldRenderer}.
 *
 * <p>
 * This class can be overridden by a plugin to change the code generation
 * behavior of XJC. Note that such changes aren't composable; for a given
 * schema compilation, only one instance of {@link FieldRendererFactory} is
 * used.
 *
 * <p>
 * See {@link Options#fieldRendererFactory}
 *
 * <p>
 * To be more precise, since {@link FieldRenderer} is just a strategy pattern
 * and by itself is stateless, the "factory methods" don't necessarily need
 * to create new instances of {@link FieldRenderer} --- it can just return
 * a set of pre-created instances.
 */
public class FieldRendererFactory {

    public FieldRenderer getDefault() {
        return DEFAULT;
    }
    public FieldRenderer getArray() {
        return ARRAY;
    }
    public FieldRenderer getRequiredUnboxed() {
        return REQUIRED_UNBOXED;
    }
    public FieldRenderer getSingle() {
        return SINGLE;
    }
    public FieldRenderer getSinglePrimitiveAccess() {
        return SINGLE_PRIMITIVE_ACCESS;
    }
    public FieldRenderer getList(JClass coreList) {
        return new UntypedListFieldRenderer(coreList);
    }
    public FieldRenderer getContentList(JClass coreList) {
        return new UntypedListFieldRenderer(coreList, false, true);
    }
    public FieldRenderer getDummyList(JClass coreList) {
        return new UntypedListFieldRenderer(coreList, true, false);
    }
    public FieldRenderer getConst(FieldRenderer fallback) {
        return new ConstFieldRenderer(fallback);
    }

    private final FieldRenderer DEFAULT
        = new DefaultFieldRenderer(this);

    private static final FieldRenderer ARRAY
        = new GenericFieldRenderer(ArrayField.class);

    private static final FieldRenderer REQUIRED_UNBOXED
        = new GenericFieldRenderer(UnboxedField.class);

    private static final FieldRenderer SINGLE
        = new GenericFieldRenderer(SingleField.class);

    private static final FieldRenderer SINGLE_PRIMITIVE_ACCESS
        = new GenericFieldRenderer(SinglePrimitiveAccessField.class);
}
