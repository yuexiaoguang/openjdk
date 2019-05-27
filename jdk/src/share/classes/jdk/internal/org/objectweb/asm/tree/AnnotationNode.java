package jdk.internal.org.objectweb.asm.tree;

import java.util.ArrayList;
import java.util.List;

import jdk.internal.org.objectweb.asm.AnnotationVisitor;
import jdk.internal.org.objectweb.asm.Opcodes;

/**
 * A node that represents an annotationn.
 */
public class AnnotationNode extends AnnotationVisitor {

    /**
     * The class descriptor of the annotation class.
     */
    public String desc;

    /**
     * The name value pairs of this annotation. Each name value pair is stored
     * as two consecutive elements in the list. The name is a {@link String},
     * and the value may be a {@link Byte}, {@link Boolean}, {@link Character},
     * {@link Short}, {@link Integer}, {@link Long}, {@link Float},
     * {@link Double}, {@link String} or {@link jdk.internal.org.objectweb.asm.Type}, or an
     * two elements String array (for enumeration values), a
     * {@link AnnotationNode}, or a {@link List} of values of one of the
     * preceding types. The list may be <tt>null</tt> if there is no name value
     * pair.
     */
    public List<Object> values;

    /**
     * Constructs a new {@link AnnotationNode}. <i>Subclasses must not use this
     * constructor</i>. Instead, they must use the
     * {@link #AnnotationNode(int, String)} version.
     *
     * @param desc
     *            the class descriptor of the annotation class.
     * @throws IllegalStateException
     *             If a subclass calls this constructor.
     */
    public AnnotationNode(final String desc) {
        this(Opcodes.ASM5, desc);
        if (getClass() != AnnotationNode.class) {
            throw new IllegalStateException();
        }
    }

    /**
     * Constructs a new {@link AnnotationNode}.
     *
     * @param api
     *            the ASM API version implemented by this visitor. Must be one
     *            of {@link Opcodes#ASM4} or {@link Opcodes#ASM5}.
     * @param desc
     *            the class descriptor of the annotation class.
     */
    public AnnotationNode(final int api, final String desc) {
        super(api);
        this.desc = desc;
    }

    /**
     * Constructs a new {@link AnnotationNode} to visit an array value.
     *
     * @param values
     *            where the visited values must be stored.
     */
    AnnotationNode(final List<Object> values) {
        super(Opcodes.ASM5);
        this.values = values;
    }

    // ------------------------------------------------------------------------
    // Implementation of the AnnotationVisitor abstract class
    // ------------------------------------------------------------------------

    @Override
    public void visit(final String name, final Object value) {
        if (values == null) {
            values = new ArrayList<Object>(this.desc != null ? 2 : 1);
        }
        if (this.desc != null) {
            values.add(name);
        }
        values.add(value);
    }

    @Override
    public void visitEnum(final String name, final String desc,
            final String value) {
        if (values == null) {
            values = new ArrayList<Object>(this.desc != null ? 2 : 1);
        }
        if (this.desc != null) {
            values.add(name);
        }
        values.add(new String[] { desc, value });
    }

    @Override
    public AnnotationVisitor visitAnnotation(final String name,
            final String desc) {
        if (values == null) {
            values = new ArrayList<Object>(this.desc != null ? 2 : 1);
        }
        if (this.desc != null) {
            values.add(name);
        }
        AnnotationNode annotation = new AnnotationNode(desc);
        values.add(annotation);
        return annotation;
    }

    @Override
    public AnnotationVisitor visitArray(final String name) {
        if (values == null) {
            values = new ArrayList<Object>(this.desc != null ? 2 : 1);
        }
        if (this.desc != null) {
            values.add(name);
        }
        List<Object> array = new ArrayList<Object>();
        values.add(array);
        return new AnnotationNode(array);
    }

    @Override
    public void visitEnd() {
    }

    // ------------------------------------------------------------------------
    // Accept methods
    // ------------------------------------------------------------------------

    /**
     * Checks that this annotation node is compatible with the given ASM API
     * version. This methods checks that this node, and all its nodes
     * recursively, do not contain elements that were introduced in more recent
     * versions of the ASM API than the given version.
     *
     * @param api
     *            an ASM API version. Must be one of {@link Opcodes#ASM4} or
     *            {@link Opcodes#ASM5}.
     */
    public void check(final int api) {
        // nothing to do
    }

    /**
     * Makes the given visitor visit this annotation.
     *
     * @param av
     *            an annotation visitor. Maybe <tt>null</tt>.
     */
    public void accept(final AnnotationVisitor av) {
        if (av != null) {
            if (values != null) {
                for (int i = 0; i < values.size(); i += 2) {
                    String name = (String) values.get(i);
                    Object value = values.get(i + 1);
                    accept(av, name, value);
                }
            }
            av.visitEnd();
        }
    }

    /**
     * Makes the given visitor visit a given annotation value.
     *
     * @param av
     *            an annotation visitor. Maybe <tt>null</tt>.
     * @param name
     *            the value name.
     * @param value
     *            the actual value.
     */
    static void accept(final AnnotationVisitor av, final String name,
            final Object value) {
        if (av != null) {
            if (value instanceof String[]) {
                String[] typeconst = (String[]) value;
                av.visitEnum(name, typeconst[0], typeconst[1]);
            } else if (value instanceof AnnotationNode) {
                AnnotationNode an = (AnnotationNode) value;
                an.accept(av.visitAnnotation(name, an.desc));
            } else if (value instanceof List) {
                AnnotationVisitor v = av.visitArray(name);
                List<?> array = (List<?>) value;
                for (int j = 0; j < array.size(); ++j) {
                    accept(v, null, array.get(j));
                }
                v.visitEnd();
            } else {
                av.visit(name, value);
            }
        }
    }
}
