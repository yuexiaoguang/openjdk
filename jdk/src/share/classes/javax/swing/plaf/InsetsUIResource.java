package javax.swing.plaf;

import java.awt.Insets;
import javax.swing.plaf.UIResource;


/*
 * A subclass of Insets that implements UIResource.  UI
 * classes that use Insets values for default properties
 * should use this class.
 * <p>
 * <strong>Warning:</strong>
 * Serialized objects of this class will not be compatible with
 * future Swing releases. The current serialization support is
 * appropriate for short term storage or RMI between applications running
 * the same version of Swing.  As of 1.4, support for long term storage
 * of all JavaBeans&trade;
 * has been added to the <code>java.beans</code> package.
 * Please see {@link java.beans.XMLEncoder}.
 */
public class InsetsUIResource extends Insets implements UIResource
{
    public InsetsUIResource(int top, int left, int bottom, int right) {
        super(top, left, bottom, right);
    }
}
