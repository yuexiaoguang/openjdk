package com.sun.xml.internal.bind.v2.model.core;

/**
 * Either {@link BuiltinLeafInfo} or {@link EnumLeafInfo}.
 *
 * <p>
 * Those Java types are all mapped to a chunk of text, so we call
 * them "leaves".
 * This interface represents the mapping information for those
 * special Java types.
 */
public interface LeafInfo<T,C> extends MaybeElement<T,C> {
}
