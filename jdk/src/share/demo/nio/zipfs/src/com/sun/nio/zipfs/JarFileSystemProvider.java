/*
 * This source code is provided to illustrate the usage of a given feature
 * or technique and has been deliberately simplified. Additional steps
 * required for a production-quality application, such as security checks,
 * input validation and proper error handling, might not be present in
 * this sample code.
 */
package com.sun.nio.zipfs;

import java.nio.file.*;
import java.nio.file.spi.*;
import java.nio.file.attribute.*;
import java.nio.file.spi.FileSystemProvider;

import java.net.URI;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.channels.FileChannel;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class JarFileSystemProvider extends ZipFileSystemProvider
{

    @Override
    public String getScheme() {
        return "jar";
    }

    @Override
    protected Path uriToPath(URI uri) {
        String scheme = uri.getScheme();
        if ((scheme == null) || !scheme.equalsIgnoreCase(getScheme())) {
            throw new IllegalArgumentException("URI scheme is not '" + getScheme() + "'");
        }
        try {
            String uristr = uri.toString();
            int end = uristr.indexOf("!/");
            uristr = uristr.substring(4, (end == -1) ? uristr.length() : end);
            uri = new URI(uristr);
            return Paths.get(new URI("file", uri.getHost(), uri.getPath(), null))
                        .toAbsolutePath();
        } catch (URISyntaxException e) {
            throw new AssertionError(e); //never thrown
        }
    }

    @Override
    public Path getPath(URI uri) {
        FileSystem fs = getFileSystem(uri);
        String path = uri.getFragment();
        if (path == null) {
            String uristr = uri.toString();
            int off = uristr.indexOf("!/");
            if (off != -1)
                path = uristr.substring(off + 2);
        }
        if (path != null)
            return fs.getPath(path);
        throw new IllegalArgumentException("URI: "
            + uri
            + " does not contain path fragment ex. jar:///c:/foo.zip!/BAR");
    }
}
