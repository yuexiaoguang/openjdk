package javax.lang.model.element;

/**
 * A mixin interface for an element that has a qualified name.
 */
public interface QualifiedNameable extends Element {
    /**
     * Returns the fully qualified name of an element.
     *
     * @return the fully qualified name of an element
     */
    Name getQualifiedName();
}
