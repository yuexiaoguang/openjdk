/*
 * This source code is provided to illustrate the usage of a given feature
 * or technique and has been deliberately simplified. Additional steps
 * required for a production-quality application, such as security checks,
 * input validation and proper error handling, might not be present in
 * this sample code.
 */
import java.awt.Graphics;


@SuppressWarnings("serial")
public class GraphApplet extends java.applet.Applet {

    double f(double x) {
        return (Math.cos(x / 5) + Math.sin(x / 7) + 2) * getSize().height / 4;
    }

    @Override
    public void paint(Graphics g) {
        for (int x = 0; x < getSize().width; x++) {
            g.drawLine(x, (int) f(x), x + 1, (int) f(x + 1));
        }
    }

    @Override
    public String getAppletInfo() {
        return "Draws a sin graph.";
    }
}
