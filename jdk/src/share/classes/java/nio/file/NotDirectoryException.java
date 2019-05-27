package java.nio.file;

/**
 * Checked exception thrown when a file system operation, intended for a
 * directory, fails because the file is not a directory.
 */
public class NotDirectoryException
    extends FileSystemException
{
    private static final long serialVersionUID = -9011457427178200199L;

    /**
     * Constructs an instance of this class.
     *
     * @param   file
     *          a string identifying the file or {@code null} if not known
     */
    public NotDirectoryException(String file) {
        super(file);
    }
}
