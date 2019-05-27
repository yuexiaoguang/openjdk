package javax.lang.model.type;

import java.util.List;

/**
 * Represents a union type.
 *
 * As of the {@link javax.lang.model.SourceVersion#RELEASE_7
 * RELEASE_7} source version, union types can appear as the type
 * of a multi-catch exception parameter.
 */
public interface UnionType extends TypeMirror {

    /**
     * Return the alternatives comprising this union type.
     *
     * @return the alternatives comprising this union type.
     */
    List<? extends TypeMirror> getAlternatives();
}
