package com.sun.tools.internal.xjc.generator.util;

import com.sun.codemodel.internal.JBlock;

public class ExistingBlockReference implements BlockReference {
    private final JBlock block;

    public ExistingBlockReference( JBlock _block ) {
        this.block = _block;
    }

    public JBlock get(boolean create) {
        return block;
    }

}
