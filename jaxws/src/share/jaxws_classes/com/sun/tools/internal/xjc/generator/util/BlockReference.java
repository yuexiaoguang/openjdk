package com.sun.tools.internal.xjc.generator.util;

import com.sun.codemodel.internal.JBlock;

/**
 * Holds a reference to a {@link JBlock} object.
 *
 * <p>
 * This interface is usually used when one wants to create
 * a new JBlock object lazily.
 */
public interface BlockReference {
    /**
     * @param create
     *      If false, the method will return null if the block is
     *      not yet created.
     */
    JBlock get(boolean create);
}
