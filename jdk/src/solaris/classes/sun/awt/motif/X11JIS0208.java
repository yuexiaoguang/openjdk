package sun.awt.motif;

import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CharsetDecoder;
import sun.nio.cs.ext.JIS_X_0208;

public class X11JIS0208 extends Charset {

    private static Charset jis0208 = new JIS_X_0208();

    public X11JIS0208 () {
        super("X11JIS0208", null);
    }

    public CharsetEncoder newEncoder() {
        return jis0208.newEncoder();
    }

    public CharsetDecoder newDecoder() {
        return jis0208.newDecoder();
    }

    public boolean contains(Charset cs) {
        return cs instanceof X11JIS0208;
    }
}
