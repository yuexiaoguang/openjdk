package jdk.internal.org.objectweb.asm.commons;

import jdk.internal.org.objectweb.asm.AnnotationVisitor;
import jdk.internal.org.objectweb.asm.FieldVisitor;
import jdk.internal.org.objectweb.asm.Opcodes;
import jdk.internal.org.objectweb.asm.TypePath;

/**
 * A {@link FieldVisitor} adapter for type remapping.
 */
public class RemappingFieldAdapter extends FieldVisitor {

    private final Remapper remapper;

    public RemappingFieldAdapter(final FieldVisitor fv, final Remapper remapper) {
        this(Opcodes.ASM5, fv, remapper);
    }

    protected RemappingFieldAdapter(final int api, final FieldVisitor fv,
            final Remapper remapper) {
        super(api, fv);
        this.remapper = remapper;
    }

    @Override
    public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
        AnnotationVisitor av = fv.visitAnnotation(remapper.mapDesc(desc),
                visible);
        return av == null ? null : new RemappingAnnotationAdapter(av, remapper);
    }

    @Override
    public AnnotationVisitor visitTypeAnnotation(int typeRef,
            TypePath typePath, String desc, boolean visible) {
        AnnotationVisitor av = super.visitTypeAnnotation(typeRef, typePath,
                remapper.mapDesc(desc), visible);
        return av == null ? null : new RemappingAnnotationAdapter(av, remapper);
    }
}
