package sun.nio.cs.ext;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CoderResult;
import sun.nio.cs.HistoricallyNamedCharset;

public class EUC_JP_LINUX
    extends Charset
    implements HistoricallyNamedCharset
{
    public EUC_JP_LINUX() {
        super("x-euc-jp-linux", ExtendedCharsets.aliasesFor("x-euc-jp-linux"));
    }

    public String historicalName() {
        return "EUC_JP_LINUX";
    }

    public boolean contains(Charset cs) {
        return ((cs instanceof JIS_X_0201)
               || (cs.name().equals("US-ASCII"))
               || (cs instanceof EUC_JP_LINUX));
    }

    public CharsetDecoder newDecoder() {
        return new Decoder(this);
    }

    public CharsetEncoder newEncoder() {
        return new Encoder(this);
    }

    private static class Decoder extends EUC_JP.Decoder {
        private Decoder(Charset cs) {
            super(cs, 1.0f, 1.0f, DEC0201, DEC0208, null);
        }
    }

    private static class Encoder extends EUC_JP.Encoder {
        private Encoder(Charset cs) {
            super(cs, 2.0f, 2.0f, ENC0201, ENC0208, null);
        }
    }
}
