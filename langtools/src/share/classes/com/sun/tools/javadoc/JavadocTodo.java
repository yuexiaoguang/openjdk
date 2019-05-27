package com.sun.tools.javadoc;

import com.sun.tools.javac.comp.*;
import com.sun.tools.javac.util.*;

/**
 *  Javadoc's own todo queue doesn't queue its inputs, as javadoc
 *  doesn't perform attribution of method bodies or semantic checking.
 *
 *  <p><b>This is NOT part of any supported API.
 *  If you write code that depends on this, you do so at your own risk.
 *  This code and its internal interfaces are subject to change or
 *  deletion without notice.</b>
 */
public class JavadocTodo extends Todo {
    public static void preRegister(Context context) {
        context.put(todoKey, new Context.Factory<Todo>() {
               public Todo make(Context c) {
                   return new JavadocTodo(c);
               }
        });
    }

    protected JavadocTodo(Context context) {
        super(context);
    }

    @Override
    public void append(Env<AttrContext> e) {
        // do nothing; Javadoc doesn't perform attribution.
    }

    @Override
    public boolean offer(Env<AttrContext> e) {
        return false;
    }
}
