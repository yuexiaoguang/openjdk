package sun.awt.motif;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.*;
import sun.nio.cs.*;
import sun.nio.cs.ext.JIS_X_0201;
import static sun.nio.cs.CharsetMapping.*;

public class X11JIS0201 extends Charset {

    private static Charset jis0201 = new JIS_X_0201();
    private static SingleByte.Encoder enc =
        (SingleByte.Encoder)jis0201.newEncoder();

    public X11JIS0201 () {
        super("X11JIS0201", null);
    }

    public CharsetEncoder newEncoder() {
        return new Encoder(this);
    }

    public CharsetDecoder newDecoder() {
        return jis0201.newDecoder();
    }

    public boolean contains(Charset cs) {
        return cs instanceof X11JIS0201;
    }

    private class Encoder extends CharsetEncoder {

        public Encoder(Charset cs) {
            super(cs, 1.0f, 1.0f);
        }

        public boolean canEncode(char c){
            if ((c >= 0xff61 && c <= 0xff9f)
                || c == 0x203e
                || c == 0xa5) {
                return true;
            }
            return false;
        }

        private Surrogate.Parser sgp;
        protected CoderResult encodeLoop(CharBuffer src, ByteBuffer dst) {
            char[] sa = src.array();
            int sp = src.arrayOffset() + src.position();
            int sl = src.arrayOffset() + src.limit();

            byte[] da = dst.array();
            int dp = dst.arrayOffset() + dst.position();
            int dl = dst.arrayOffset() + dst.limit();
            CoderResult cr = CoderResult.UNDERFLOW;
            if ((dl - dp) < (sl - sp)) {
                sl = sp + (dl - dp);
                cr = CoderResult.OVERFLOW;
            }
            try {
                while (sp < sl) {
                    char c = sa[sp];
                    int b = enc.encode(c);
                    if (b == UNMAPPABLE_ENCODING) {
                        if (Character.isSurrogate(c)) {
                            if (sgp == null)
                                sgp = new Surrogate.Parser();
                            if (sgp.parse(c, sa, sp, sl) >= 0)
                                return CoderResult.unmappableForLength(2);
                        }
                        return CoderResult.unmappableForLength(1);
                    }
                    da[dp++] = (byte)b;
                    sp++;
                }
                return cr;
            } finally {
                src.position(sp - src.arrayOffset());
                dst.position(dp - dst.arrayOffset());
            }
        }
    }
}
