package javax.lang.model.type;


/**
 * Represents a wildcard type argument.
 * Examples include:    <pre><tt>
 *   ?
 *   ? extends Number
 *   ? super T
 * </tt></pre>
 *
 * <p> A wildcard may have its upper bound explicitly set by an
 * {@code extends} clause, its lower bound explicitly set by a
 * {@code super} clause, or neither (but not both).
 */
public interface WildcardType extends TypeMirror {

    /**
     * Returns the upper bound of this wildcard.
     * If no upper bound is explicitly declared,
     * {@code null} is returned.
     *
     * @return the upper bound of this wildcard
     */
    TypeMirror getExtendsBound();

    /**
     * Returns the lower bound of this wildcard.
     * If no lower bound is explicitly declared,
     * {@code null} is returned.
     *
     * @return the lower bound of this wildcard
     */
    TypeMirror getSuperBound();
}
