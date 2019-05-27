package sun.awt.X11;

import sun.awt.PlatformFont;
import java.awt.GraphicsEnvironment;

public class XFontPeer extends PlatformFont {

    /*
     * XLFD name for XFontSet.
     */
    private String xfsname;

    static {
        if (!GraphicsEnvironment.isHeadless()) {
            initIDs();
        }
    }

    /**
     * Initialize JNI field and method IDs for fields that may be
       accessed from C.
     */
    private static native void initIDs();

    public XFontPeer(String name, int style){
        super(name, style);
    }

    protected char getMissingGlyphCharacter() {
        return '\u274F';
    }
}
