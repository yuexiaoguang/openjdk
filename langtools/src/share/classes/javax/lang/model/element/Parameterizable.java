package javax.lang.model.element;

import java.util.List;

/**
 * A mixin interface for an element that has type parameters.
 */
public interface Parameterizable extends Element {
    /**
     * Returns the formal type parameters of the type element in
     * declaration order.
     *
     * @return the formal type parameters, or an empty list
     * if there are none
     */
    List<? extends TypeParameterElement> getTypeParameters();
}
