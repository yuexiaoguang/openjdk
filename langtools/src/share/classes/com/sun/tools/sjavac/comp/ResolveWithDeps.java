package com.sun.tools.sjavac.comp;

import com.sun.tools.javac.comp.Resolve;
import com.sun.tools.javac.util.Context;
import com.sun.tools.javac.code.Symbol;

/** Subclass to Resolve that overrides collect.
 *
 * <p><b>This is NOT part of any supported API.
 * If you write code that depends on this, you do so at your own
 * risk.  This code and its internal interfaces are subject to change
 * or deletion without notice.</b></p>
 */
public class ResolveWithDeps extends Resolve {

    /** The dependency database
     */
    protected Dependencies deps;

    protected ResolveWithDeps(Context context) {
        super(context);
        deps = Dependencies.instance(context);
    }

    public static void preRegister(Context context) {
        context.put(resolveKey, new Context.Factory<Resolve>() {
            public Resolve make(Context c) {
                Resolve instance = new ResolveWithDeps(c);
                c.put(Resolve.class, instance);
                return instance;
            }
        });
    }
    /** Collect dependencies in the enclosing class
     * @param from The enclosing class sym
     * @param to   The enclosing classes references this sym.
     * */
    @Override
    public void reportDependence(Symbol from, Symbol to) {
        // Capture dependencies between the packages.
        deps.collect(from.packge().fullname, to.packge().fullname);
    }
}
