package jdk.nashorn.internal.runtime.regexp.joni;

import jdk.nashorn.internal.runtime.regexp.joni.ast.CClassNode;
import jdk.nashorn.internal.runtime.regexp.joni.ast.ConsAltNode;
import jdk.nashorn.internal.runtime.regexp.joni.ast.StringNode;

final class ApplyCaseFold {

    // i_apply_case_fold
    public void apply(int from, int[]to, int length, Object o) {
        ApplyCaseFoldArg arg = (ApplyCaseFoldArg)o;

        ScanEnvironment env = arg.env;
        CClassNode cc = arg.cc;
        BitSet bs = cc.bs;

        if (length == 1) {
            boolean inCC = cc.isCodeInCC(from);

            if (Config.CASE_FOLD_IS_APPLIED_INSIDE_NEGATIVE_CCLASS) {
                if ((inCC && !cc.isNot()) || (!inCC && cc.isNot())) {
                    if (to[0] >= BitSet.SINGLE_BYTE_SIZE) {
                        cc.addCodeRange(env, to[0], to[0]);
                    } else {
                        /* /(?i:[^A-C])/.match("a") ==> fail. */
                        bs.set(to[0]);
                    }
                }
            } else {
                if (inCC) {
                    if (to[0] >= BitSet.SINGLE_BYTE_SIZE) {
                        if (cc.isNot()) cc.clearNotFlag();
                        cc.addCodeRange(env, to[0], to[0]);
                    } else {
                        if (cc.isNot()) {
                            bs.clear(to[0]);
                        } else {
                            bs.set(to[0]);
                        }
                    }
                }
            } // CASE_FOLD_IS_APPLIED_INSIDE_NEGATIVE_CCLASS

        } else {
            if (cc.isCodeInCC(from) && (!Config.CASE_FOLD_IS_APPLIED_INSIDE_NEGATIVE_CCLASS || !cc.isNot())) {
                StringNode node = null;
                for (int i=0; i<length; i++) {
                    if (i == 0) {
                        node = new StringNode();
                        /* char-class expanded multi-char only
                        compare with string folded at match time. */
                        node.setAmbig();
                    }
                    node.catCode(to[i]);
                }

                ConsAltNode alt = ConsAltNode.newAltNode(node, null);

                if (arg.tail == null) {
                    arg.altRoot = alt;
                } else {
                    arg.tail.setCdr(alt);
                }
                arg.tail = alt;
            }

        }

    }

    static final ApplyCaseFold INSTANCE = new ApplyCaseFold();
}
