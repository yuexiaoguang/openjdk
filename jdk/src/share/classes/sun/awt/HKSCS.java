package sun.awt;

import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CharsetDecoder;

/* 2d/XMap and WFontConfiguration implementation need access HKSCS,
   make a subclass here to avoid expose HKSCS to the public in
   ExtendedCharsets class, because if we want to have a public HKSCS,
   it probably should be HKSCS_2001 not HKSCS.
*/
public class HKSCS extends sun.nio.cs.ext.MS950_HKSCS_XP {
    public HKSCS () {
        super();
    }
    public boolean contains(Charset cs) {
        return (cs instanceof HKSCS);
    }
}
