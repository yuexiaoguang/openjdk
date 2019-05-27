package javax.swing.plaf.synth;

import javax.swing.JComponent;
import java.awt.Graphics;
import javax.swing.plaf.ComponentUI;


/**
 * Provides the Synth L&amp;F UI delegate for
 * {@link javax.swing.JCheckBox}.
 */
public class SynthCheckBoxUI extends SynthRadioButtonUI {

    // ********************************
    //            Create PLAF
    // ********************************
    /**
     * Creates a new UI object for the given component.
     *
     * @param b component to create UI object for
     * @return the UI object
     */
    public static ComponentUI createUI(JComponent b) {
        return new SynthCheckBoxUI();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getPropertyPrefix() {
        return "CheckBox.";
    }

    @Override
    void paintBackground(SynthContext context, Graphics g, JComponent c) {
        context.getPainter().paintCheckBoxBackground(context, g, 0, 0,
                                                  c.getWidth(), c.getHeight());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void paintBorder(SynthContext context, Graphics g, int x,
                            int y, int w, int h) {
        context.getPainter().paintCheckBoxBorder(context, g, x, y, w, h);
    }
}
