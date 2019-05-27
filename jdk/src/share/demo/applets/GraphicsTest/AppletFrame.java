/*
 * This source code is provided to illustrate the usage of a given feature
 * or technique and has been deliberately simplified. Additional steps
 * required for a production-quality application, such as security checks,
 * input validation and proper error handling, might not be present in
 * this sample code.
 */
import java.awt.Frame;
import java.awt.Event;
import java.awt.Dimension;
import java.applet.Applet;
import java.awt.AWTEvent;


/**
 * Applet to Application Frame window
 */
@SuppressWarnings("serial")
class AppletFrame extends Frame {

    public static void startApplet(String className,
            String title,
            String args[]) {
        // local variables
        Applet a;
        Dimension appletSize;

        try {
            // create an instance of your applet class
            a = (Applet) Class.forName(className).newInstance();
        } catch (ClassNotFoundException e) {
            return;
        } catch (InstantiationException e) {
            return;
        } catch (IllegalAccessException e) {
            return;
        }

        // initialize the applet
        a.init();
        a.start();

        // create new application frame window
        AppletFrame f = new AppletFrame(title);

        // add applet to frame window
        f.add("Center", a);

        // resize frame window to fit applet
        // assumes that the applet sets its own size
        // otherwise, you should set a specific size here.
        appletSize = a.getSize();
        f.pack();
        f.setSize(appletSize);

        // show the window
        f.setVisible(true);

    }  // end startApplet()

    // constructor needed to pass window title to class Frame
    public AppletFrame(String name) {
        // call java.awt.Frame(String) constructor
        super(name);
    }

    // needed to allow window close
    @Override
    public void processEvent(AWTEvent e) {
        // Window Destroy event
        if (e.getID() == Event.WINDOW_DESTROY) {
            // exit the program
            System.exit(0);
        }
    }  // end handleEvent()
}   // end class AppletFrame

