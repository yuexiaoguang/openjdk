package jdk.internal.org.objectweb.asm.util;

import jdk.internal.org.objectweb.asm.AnnotationVisitor;
import jdk.internal.org.objectweb.asm.Opcodes;

/**
 * An {@link AnnotationVisitor} that prints the annotations it visits with a
 * {@link Printer}.
 */
public final class TraceAnnotationVisitor extends AnnotationVisitor {

    private final Printer p;

    public TraceAnnotationVisitor(final Printer p) {
        this(null, p);
    }

    public TraceAnnotationVisitor(final AnnotationVisitor av, final Printer p) {
        super(Opcodes.ASM5, av);
        this.p = p;
    }

    @Override
    public void visit(final String name, final Object value) {
        p.visit(name, value);
        super.visit(name, value);
    }

    @Override
    public void visitEnum(final String name, final String desc,
            final String value) {
        p.visitEnum(name, desc, value);
        super.visitEnum(name, desc, value);
    }

    @Override
    public AnnotationVisitor visitAnnotation(final String name,
            final String desc) {
        Printer p = this.p.visitAnnotation(name, desc);
        AnnotationVisitor av = this.av == null ? null : this.av
                .visitAnnotation(name, desc);
        return new TraceAnnotationVisitor(av, p);
    }

    @Override
    public AnnotationVisitor visitArray(final String name) {
        Printer p = this.p.visitArray(name);
        AnnotationVisitor av = this.av == null ? null : this.av
                .visitArray(name);
        return new TraceAnnotationVisitor(av, p);
    }

    @Override
    public void visitEnd() {
        p.visitAnnotationEnd();
        super.visitEnd();
    }
}
