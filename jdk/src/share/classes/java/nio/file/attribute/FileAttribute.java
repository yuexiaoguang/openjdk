package java.nio.file.attribute;

/**
 * An object that encapsulates the value of a file attribute that can be set
 * atomically when creating a new file or directory by invoking the {@link
 * java.nio.file.Files#createFile createFile} or {@link
 * java.nio.file.Files#createDirectory createDirectory} methods.
 *
 * @param <T> The type of the file attribute value
 */
public interface FileAttribute<T> {
    /**
     * Returns the attribute name.
     *
     * @return The attribute name
     */
    String name();

    /**
     * Returns the attribute value.
     *
     * @return The attribute value
     */
    T value();
}
