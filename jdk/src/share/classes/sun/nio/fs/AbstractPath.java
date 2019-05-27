package sun.nio.fs;

import java.nio.file.*;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Base implementation class of {@code Path}.
 */
abstract class AbstractPath implements Path {
    protected AbstractPath() { }

    @Override
    public final boolean startsWith(String other) {
        return startsWith(getFileSystem().getPath(other));
    }

    @Override
    public final boolean endsWith(String other) {
        return endsWith(getFileSystem().getPath(other));
    }

    @Override
    public final Path resolve(String other) {
        return resolve(getFileSystem().getPath(other));
    }

    @Override
    public final Path resolveSibling(Path other) {
        if (other == null)
            throw new NullPointerException();
        Path parent = getParent();
        return (parent == null) ? other : parent.resolve(other);
    }

    @Override
    public final Path resolveSibling(String other) {
        return resolveSibling(getFileSystem().getPath(other));
    }

    @Override
    public final Iterator<Path> iterator() {
        return new Iterator<Path>() {
            private int i = 0;
            @Override
            public boolean hasNext() {
                return (i < getNameCount());
            }
            @Override
            public Path next() {
                if (i < getNameCount()) {
                    Path result = getName(i);
                    i++;
                    return result;
                } else {
                    throw new NoSuchElementException();
                }
            }
            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    @Override
    public final File toFile() {
        return new File(toString());
    }

    @Override
    public final WatchKey register(WatchService watcher,
                                   WatchEvent.Kind<?>... events)
        throws IOException
    {
        return register(watcher, events, new WatchEvent.Modifier[0]);
    }
}
