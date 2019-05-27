package sun.misc;

import java.net.URLClassLoader;

public interface JavaNetAccess {
    /**
     * return the URLClassPath belonging to the given loader
     */
    URLClassPath getURLClassPath (URLClassLoader u);
}
