package sun.misc;

import java.io.File;
import java.io.FilenameFilter;

/**
 * <p>
 * This class checks that only jar and zip files are included in the file list.
 * This class is used in extension installation support (ExtensionDependency).
 * <p>
 */
public class JarFilter implements FilenameFilter {

    public boolean accept(File dir, String name) {
        String lower = name.toLowerCase();
        return lower.endsWith(".jar") || lower.endsWith(".zip");
    }
}
