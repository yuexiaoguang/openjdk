package com.sun.tools.internal.xjc.generator.util;

import com.sun.codemodel.internal.JBlock;

/**
 * Lazy block reference.
 */
public abstract class LazyBlockReference implements BlockReference {

    private JBlock block = null;

    /**
     * Called when a block needs to be created.
     * Only called once in the whole life time of this object.
     */
    protected abstract JBlock create();

    public JBlock get(boolean create) {
        if(!create)     return block;
        if(block==null)
            block = create();
        return block;
    }

}
