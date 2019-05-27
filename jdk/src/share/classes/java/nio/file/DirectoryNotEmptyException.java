package java.nio.file;

/**
 * Checked exception thrown when a file system operation fails because a
 * directory is not empty.
 */
public class DirectoryNotEmptyException
    extends FileSystemException
{
    static final long serialVersionUID = 3056667871802779003L;

    /**
     * Constructs an instance of this class.
     *
     * @param   dir
     *          a string identifying the directory or {@code null} if not known
     */
    public DirectoryNotEmptyException(String dir) {
        super(dir);
    }
}
