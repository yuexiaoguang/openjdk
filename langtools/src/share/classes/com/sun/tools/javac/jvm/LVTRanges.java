package com.sun.tools.javac.jvm;

import java.util.Map;
import java.util.Map.Entry;
import java.util.WeakHashMap;

import com.sun.tools.javac.code.Symbol.MethodSymbol;
import com.sun.tools.javac.code.Symbol.VarSymbol;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.util.Context;
import com.sun.tools.javac.util.List;

/** This class contains a one to many relation between a tree and a set of variables.
 *  The relation implies that the given tree closes the DA (definite assignment)
 *  range for the set of variables.
 *
 *  <p><b>This is NOT part of any supported API.
 *  If you write code that depends on this, you do so at your own risk.
 *  This code and its internal interfaces are subject to change or
 *  deletion without notice.</b>
 */
public class LVTRanges {
    /** The context key for the LVT ranges. */
    protected static final Context.Key<LVTRanges> lvtRangesKey = new Context.Key<>();

    /** Get the LVTRanges instance for this context. */
    public static LVTRanges instance(Context context) {
        LVTRanges instance = context.get(lvtRangesKey);
        if (instance == null) {
            instance = new LVTRanges(context);
        }
        return instance;
    }

    private static final long serialVersionUID = 1812267524140424433L;

    protected Context context;

    protected Map<MethodSymbol, Map<JCTree, List<VarSymbol>>>
            aliveRangeClosingTrees = new WeakHashMap<>();

    public LVTRanges(Context context) {
        this.context = context;
        context.put(lvtRangesKey, this);
    }

    public List<VarSymbol> getVars(MethodSymbol method, JCTree tree) {
        Map<JCTree, List<VarSymbol>> varMap = aliveRangeClosingTrees.get(method);
        return (varMap != null) ? varMap.get(tree) : null;
    }

    public boolean containsKey(MethodSymbol method, JCTree tree) {
        Map<JCTree, List<VarSymbol>> varMap = aliveRangeClosingTrees.get(method);
        if (varMap == null) {
            return false;
        }
        return varMap.containsKey(tree);
    }

    public void setEntry(MethodSymbol method, JCTree tree, List<VarSymbol> vars) {
        Map<JCTree, List<VarSymbol>> varMap = aliveRangeClosingTrees.get(method);
        if (varMap != null) {
            varMap.put(tree, vars);
        } else {
            varMap = new WeakHashMap<>();
            varMap.put(tree, vars);
            aliveRangeClosingTrees.put(method, varMap);
        }
    }

    public List<VarSymbol> removeEntry(MethodSymbol method, JCTree tree) {
        Map<JCTree, List<VarSymbol>> varMap = aliveRangeClosingTrees.get(method);
        if (varMap != null) {
            List<VarSymbol> result = varMap.remove(tree);
            if (varMap.isEmpty()) {
                aliveRangeClosingTrees.remove(method);
            }
            return result;
        }
        return null;
    }

    /* This method should be used for debugging LVT related issues.
     */
    @Override
    public String toString() {
        String result = "";
        for (Entry<MethodSymbol, Map<JCTree, List<VarSymbol>>> mainEntry: aliveRangeClosingTrees.entrySet()) {
            result += "Method: \n" + mainEntry.getKey().flatName() + "\n";
            int i = 1;
            for (Entry<JCTree, List<VarSymbol>> treeEntry: mainEntry.getValue().entrySet()) {
                result += "    Tree " + i + ": \n" + treeEntry.getKey().toString() + "\n";
                result += "        Variables closed:\n";
                for (VarSymbol var: treeEntry.getValue()) {
                    result += "            " + var.toString();
                }
                result += "\n";
                i++;
            }
        }
        return result;
    }

}
