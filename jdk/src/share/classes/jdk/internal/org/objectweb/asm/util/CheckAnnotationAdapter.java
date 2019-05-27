package jdk.internal.org.objectweb.asm.util;

import jdk.internal.org.objectweb.asm.AnnotationVisitor;
import jdk.internal.org.objectweb.asm.Opcodes;
import jdk.internal.org.objectweb.asm.Type;

/**
 * An {@link AnnotationVisitor} that checks that its methods are properly used.
 */
public class CheckAnnotationAdapter extends AnnotationVisitor {

    private final boolean named;

    private boolean end;

    public CheckAnnotationAdapter(final AnnotationVisitor av) {
        this(av, true);
    }

    CheckAnnotationAdapter(final AnnotationVisitor av, final boolean named) {
        super(Opcodes.ASM5, av);
        this.named = named;
    }

    @Override
    public void visit(final String name, final Object value) {
        checkEnd();
        checkName(name);
        if (!(value instanceof Byte || value instanceof Boolean
                || value instanceof Character || value instanceof Short
                || value instanceof Integer || value instanceof Long
                || value instanceof Float || value instanceof Double
                || value instanceof String || value instanceof Type
                || value instanceof byte[] || value instanceof boolean[]
                || value instanceof char[] || value instanceof short[]
                || value instanceof int[] || value instanceof long[]
                || value instanceof float[] || value instanceof double[])) {
            throw new IllegalArgumentException("Invalid annotation value");
        }
        if (value instanceof Type) {
            int sort = ((Type) value).getSort();
            if (sort != Type.OBJECT && sort != Type.ARRAY) {
                throw new IllegalArgumentException("Invalid annotation value");
            }
        }
        if (av != null) {
            av.visit(name, value);
        }
    }

    @Override
    public void visitEnum(final String name, final String desc,
            final String value) {
        checkEnd();
        checkName(name);
        CheckMethodAdapter.checkDesc(desc, false);
        if (value == null) {
            throw new IllegalArgumentException("Invalid enum value");
        }
        if (av != null) {
            av.visitEnum(name, desc, value);
        }
    }

    @Override
    public AnnotationVisitor visitAnnotation(final String name,
            final String desc) {
        checkEnd();
        checkName(name);
        CheckMethodAdapter.checkDesc(desc, false);
        return new CheckAnnotationAdapter(av == null ? null
                : av.visitAnnotation(name, desc));
    }

    @Override
    public AnnotationVisitor visitArray(final String name) {
        checkEnd();
        checkName(name);
        return new CheckAnnotationAdapter(av == null ? null
                : av.visitArray(name), false);
    }

    @Override
    public void visitEnd() {
        checkEnd();
        end = true;
        if (av != null) {
            av.visitEnd();
        }
    }

    private void checkEnd() {
        if (end) {
            throw new IllegalStateException(
                    "Cannot call a visit method after visitEnd has been called");
        }
    }

    private void checkName(final String name) {
        if (named && name == null) {
            throw new IllegalArgumentException(
                    "Annotation value name must not be null");
        }
    }
}
