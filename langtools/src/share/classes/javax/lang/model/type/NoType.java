package javax.lang.model.type;

import javax.lang.model.element.ExecutableElement;


/**
 * A pseudo-type used where no actual type is appropriate.
 * The kinds of {@code NoType} are:
 * <ul>
 * <li>{@link TypeKind#VOID VOID} - corresponds to the keyword {@code void}.
 * <li>{@link TypeKind#PACKAGE PACKAGE} - the pseudo-type of a package element.
 * <li>{@link TypeKind#NONE NONE} - used in other cases
 *   where no actual type is appropriate; for example, the superclass
 *   of {@code java.lang.Object}.
 * </ul>
 */
public interface NoType extends TypeMirror {
}
