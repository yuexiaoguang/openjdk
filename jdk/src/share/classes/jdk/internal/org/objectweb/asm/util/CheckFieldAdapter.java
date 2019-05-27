package jdk.internal.org.objectweb.asm.util;

import jdk.internal.org.objectweb.asm.AnnotationVisitor;
import jdk.internal.org.objectweb.asm.Attribute;
import jdk.internal.org.objectweb.asm.FieldVisitor;
import jdk.internal.org.objectweb.asm.Opcodes;
import jdk.internal.org.objectweb.asm.TypePath;
import jdk.internal.org.objectweb.asm.TypeReference;

/**
 * A {@link FieldVisitor} that checks that its methods are properly used.
 */
public class CheckFieldAdapter extends FieldVisitor {

    private boolean end;

    /**
     * Constructs a new {@link CheckFieldAdapter}. <i>Subclasses must not use
     * this constructor</i>. Instead, they must use the
     * {@link #CheckFieldAdapter(int, FieldVisitor)} version.
     *
     * @param fv
     *            the field visitor to which this adapter must delegate calls.
     * @throws IllegalStateException
     *             If a subclass calls this constructor.
     */
    public CheckFieldAdapter(final FieldVisitor fv) {
        this(Opcodes.ASM5, fv);
        if (getClass() != CheckFieldAdapter.class) {
            throw new IllegalStateException();
        }
    }

    /**
     * Constructs a new {@link CheckFieldAdapter}.
     *
     * @param api
     *            the ASM API version implemented by this visitor. Must be one
     *            of {@link Opcodes#ASM4} or {@link Opcodes#ASM5}.
     * @param fv
     *            the field visitor to which this adapter must delegate calls.
     */
    protected CheckFieldAdapter(final int api, final FieldVisitor fv) {
        super(api, fv);
    }

    @Override
    public AnnotationVisitor visitAnnotation(final String desc,
            final boolean visible) {
        checkEnd();
        CheckMethodAdapter.checkDesc(desc, false);
        return new CheckAnnotationAdapter(super.visitAnnotation(desc, visible));
    }

    @Override
    public AnnotationVisitor visitTypeAnnotation(final int typeRef,
            final TypePath typePath, final String desc, final boolean visible) {
        checkEnd();
        int sort = typeRef >>> 24;
        if (sort != TypeReference.FIELD) {
            throw new IllegalArgumentException("Invalid type reference sort 0x"
                    + Integer.toHexString(sort));
        }
        CheckClassAdapter.checkTypeRefAndPath(typeRef, typePath);
        CheckMethodAdapter.checkDesc(desc, false);
        return new CheckAnnotationAdapter(super.visitTypeAnnotation(typeRef,
                typePath, desc, visible));
    }

    @Override
    public void visitAttribute(final Attribute attr) {
        checkEnd();
        if (attr == null) {
            throw new IllegalArgumentException(
                    "Invalid attribute (must not be null)");
        }
        super.visitAttribute(attr);
    }

    @Override
    public void visitEnd() {
        checkEnd();
        end = true;
        super.visitEnd();
    }

    private void checkEnd() {
        if (end) {
            throw new IllegalStateException(
                    "Cannot call a visit method after visitEnd has been called");
        }
    }
}
