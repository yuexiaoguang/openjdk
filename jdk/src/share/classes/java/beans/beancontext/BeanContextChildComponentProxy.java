package java.beans.beancontext;

import java.awt.Component;

/**
 * <p>
 * This interface is implemented by
 * <code>BeanContextChildren</code> that have an AWT <code>Component</code>
 * associated with them.
 * </p>
 */
public interface BeanContextChildComponentProxy {

    /**
     * Gets the <code>java.awt.Component</code> associated with
     * this <code>BeanContextChild</code>.
     * @return the AWT <code>Component</code> associated with
     * this <code>BeanContextChild</code>
     */

    Component getComponent();
}
