package jdk.nashorn.internal.codegen;

import jdk.nashorn.internal.codegen.types.Type;
import jdk.nashorn.internal.runtime.Debug;

/**
 * Abstraction for labels, separating a label from the underlying
 * byte code emitter. Also augmenting label with e.g. a name
 * for easier debugging and reading code
 *
 * see -Dnashorn.codegen.debug, --log=codegen
 */
public final class Label {
    //byte code generation evaluation type stack for consistency check
    //and correct opcode selection. one per label as a label may be a
    //join point
    static final class Stack {
        Type[] data = new Type[8];
        int sp = 0;

        Stack() {
        }

        private Stack(final Type[] type, final int sp) {
            this();
            this.data = new Type[type.length];
            this.sp   = sp;
            for (int i = 0; i < sp; i++) {
                data[i] = type[i];
            }
        }

        boolean isEmpty() {
            return sp == 0;
        }

        int size() {
            return sp;
        }

        boolean isEquivalentTo(final Stack other) {
            if (sp != other.sp) {
                return false;
            }
            for (int i = 0; i < sp; i++) {
                if (!data[i].isEquivalentTo(other.data[i])) {
                    return false;
                }
            }
            return true;
        }

        void clear() {
            sp = 0;
        }

        void push(final Type type) {
            if (data.length == sp) {
                final Type[] newData = new Type[sp * 2];
                for (int i = 0; i < sp; i++) {
                    newData[i] = data[i];
                }
                data = newData;
            }
            data[sp++] = type;
        }

        Type peek() {
            return peek(0);
        }

        Type peek(final int n) {
            int pos = sp - 1 - n;
            return pos < 0 ? null : data[pos];
        }

        Type pop() {
            return data[--sp];
        }

        Stack copy() {
            return new Stack(data, sp);
        }

        @Override
        public String toString() {
            final StringBuilder builder = new StringBuilder("[");
            for (int i = 0; i < sp; i++) {
                builder.append(data[i]);
                if (i < sp - 1) {
                    builder.append(", ");
                }
            }
            return builder.append("]").toString();
        }
    }

    /** Name of this label */
    private final String name;

    /** Type stack at this label */
    private Label.Stack stack;

    /** ASM representation of this label */
    private jdk.internal.org.objectweb.asm.Label label;

    /**
     * Constructor
     *
     * @param name name of this label
     */
    public Label(final String name) {
        super();
        this.name = name;
    }

    /**
     * Copy constructor
     *
     * @param label a label to clone
     */
    public Label(final Label label) {
        super();
        this.name = label.name;
    }


    jdk.internal.org.objectweb.asm.Label getLabel() {
        if (this.label == null) {
            this.label = new jdk.internal.org.objectweb.asm.Label();
        }
        return label;
    }

    Label.Stack getStack() {
        return stack;
    }

    void setStack(final Label.Stack stack) {
        this.stack = stack;
    }

    @Override
    public String toString() {
        return name + '_' + Debug.id(this);
    }
}
