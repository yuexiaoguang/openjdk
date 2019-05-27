package com.sun.tools.internal.jxc.api;

import com.sun.tools.internal.xjc.api.JavaCompiler;
import com.sun.tools.internal.jxc.api.impl.j2s.JavaCompilerImpl;

public class JXC {
    /**
     * Gets a fresh {@link JavaCompiler}.
     *
     * @return
     *      always return non-null object.
     */
    public static JavaCompiler createJavaCompiler() {
        return new JavaCompilerImpl();
    }
}
