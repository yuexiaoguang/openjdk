package com.sun.tools.internal.xjc.reader.relaxng;

import java.util.HashSet;
import java.util.Set;

import com.sun.xml.internal.rngom.digested.DDefine;
import com.sun.xml.internal.rngom.digested.DGrammarPattern;
import com.sun.xml.internal.rngom.digested.DPatternWalker;
import com.sun.xml.internal.rngom.digested.DRefPattern;

/**
 * Recursively find all {@link DDefine}s in the grammar.
 */
final class DefineFinder extends DPatternWalker {

    public final Set<DDefine> defs = new HashSet<DDefine>();

    public Void onGrammar(DGrammarPattern p) {
        for( DDefine def : p ) {
            defs.add(def);
            def.getPattern().accept(this);
        }

        return p.getStart().accept(this);
    }

    /**
     * We visit all {@link DDefine}s from {@link DGrammarPattern},
     * so no point in resolving refs.
     */
    public Void onRef(DRefPattern p) {
        return null;
    }
}
