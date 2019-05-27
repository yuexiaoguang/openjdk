package javax.swing.plaf.synth;


import java.awt.*;
import javax.swing.*;
import javax.swing.plaf.*;


/**
 * Provides the Synth L&amp;F UI delegate for
 * {@link javax.swing.JCheckBoxMenuItem}.
 */
public class SynthCheckBoxMenuItemUI extends SynthMenuItemUI {

    /**
     * Creates a new UI object for the given component.
     *
     * @param c component to create UI object for
     * @return the UI object
     */
    public static ComponentUI createUI(JComponent c) {
        return new SynthCheckBoxMenuItemUI();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getPropertyPrefix() {
        return "CheckBoxMenuItem";
    }

    @Override
    void paintBackground(SynthContext context, Graphics g, JComponent c) {
        context.getPainter().paintCheckBoxMenuItemBackground(context, g, 0, 0,
                                                  c.getWidth(), c.getHeight());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void paintBorder(SynthContext context, Graphics g, int x,
                            int y, int w, int h) {
        context.getPainter().paintCheckBoxMenuItemBorder(context, g, x, y, w, h);
    }
}
