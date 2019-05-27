package sun.swing;

import javax.swing.ImageIcon;
import javax.swing.plaf.UIResource;
import java.awt.Image;

/**
 * A subclass of <code>ImageIcon</code> that implements UIResource.
 */
public class ImageIconUIResource extends ImageIcon implements UIResource {

    /**
     * Calls the superclass constructor with the same parameter.
     *
     * @param imageData an array of pixels
     * @see javax.swing.ImageIcon#ImageIcon(byte[])
     */
    public ImageIconUIResource(byte[] imageData) {
        super(imageData);
    }

    /**
     * Calls the superclass constructor with the same parameter.
     *
     * @param image an image
     * @see javax.swing.ImageIcon#ImageIcon(Image)
     */
    public ImageIconUIResource(Image image) {
        super(image);
    }
}
