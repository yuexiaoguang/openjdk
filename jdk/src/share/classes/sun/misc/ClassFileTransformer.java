package sun.misc;

import java.util.ArrayList;
import java.util.List;

/**
 * This is an abstract base class originally intended to be called by
 * {@code java.lang.ClassLoader} when {@code ClassFormatError} is
 * thrown inside {@code defineClass()}. It is no longer hooked into
 * {@code ClassLoader} and will be removed in a future release.
 */
@Deprecated
public abstract class ClassFileTransformer {

    private static final List<ClassFileTransformer> transformers
        = new ArrayList<ClassFileTransformer>();

    /**
     * Add the class file transformer object.
     *
     * @param t Class file transformer instance
     */
    public static void add(ClassFileTransformer t) {
        synchronized (transformers) {
            transformers.add(t);
        }
    }

    /**
     * Get the array of ClassFileTransformer object.
     *
     * @return ClassFileTransformer object array
     */
    public static ClassFileTransformer[] getTransformers() {
        synchronized (transformers) {
            ClassFileTransformer[] result = new ClassFileTransformer[transformers.size()];
            return transformers.toArray(result);
        }
    }


    /**
     * Transform a byte array from one to the other.
     *
     * @param b Byte array
     * @param off Offset
     * @param len Length of byte array
     * @return Transformed byte array
     */
    public abstract byte[] transform(byte[] b, int off, int len)
        throws ClassFormatError;
}
