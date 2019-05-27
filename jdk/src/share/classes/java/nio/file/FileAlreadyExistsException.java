package java.nio.file;

/**
 * Checked exception thrown when an attempt is made to create a file or
 * directory and a file of that name already exists.
 */
public class FileAlreadyExistsException
    extends FileSystemException
{
    static final long serialVersionUID = 7579540934498831181L;

    /**
     * Constructs an instance of this class.
     *
     * @param   file
     *          a string identifying the file or {@code null} if not known
     */
    public FileAlreadyExistsException(String file) {
        super(file);
    }

    /**
     * Constructs an instance of this class.
     *
     * @param   file
     *          a string identifying the file or {@code null} if not known
     * @param   other
     *          a string identifying the other file or {@code null} if not known
     * @param   reason
     *          a reason message with additional information or {@code null}
     */
    public FileAlreadyExistsException(String file, String other, String reason) {
        super(file, other, reason);
    }
}
