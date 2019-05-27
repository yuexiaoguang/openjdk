package java.beans.beancontext;

import java.awt.Container;

/**
 * <p>
 * This interface is implemented by BeanContexts' that have an AWT Container
 * associated with them.
 * </p>
 */
public interface BeanContextContainerProxy {

    /**
     * Gets the <code>java.awt.Container</code> associated
     * with this <code>BeanContext</code>.
     * @return the <code>java.awt.Container</code> associated
     * with this <code>BeanContext</code>.
     */
    Container getContainer();
}
