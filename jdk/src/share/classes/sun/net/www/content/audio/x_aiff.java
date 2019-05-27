/**
 * Basic .aiff audio handler.
 */
package sun.net.www.content.audio;

import java.net.*;
import java.io.IOException;
import sun.applet.AppletAudioClip;

/**
 * Returns an AppletAudioClip object.
 */
public class x_aiff extends ContentHandler {
    public Object getContent(URLConnection uc) throws IOException {
        return new AppletAudioClip(uc);
    }
}
