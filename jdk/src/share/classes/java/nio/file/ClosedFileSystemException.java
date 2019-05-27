package java.nio.file;

/**
 * Unchecked exception thrown when an attempt is made to invoke an operation on
 * a file and the file system is closed.
 */
public class ClosedFileSystemException
    extends IllegalStateException
{
    static final long serialVersionUID = -8158336077256193488L;

    /**
     * Constructs an instance of this class.
     */
    public ClosedFileSystemException() {
    }
}
