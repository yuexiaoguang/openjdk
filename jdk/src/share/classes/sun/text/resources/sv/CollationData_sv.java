package sun.text.resources.sv;

import java.util.ListResourceBundle;

public class CollationData_sv extends ListResourceBundle {

    protected final Object[][] getContents() {
        return new Object[][] {
            { "Rule",
                "& Z < a\u030a , A\u030a" +  // a-ring, aa ligaure
                "< a\u0308 , A\u0308 < a\u030b, A\u030b " +  // a-umlaut, a-double-acute
                "< \u00e6 , \u00c6 " +                   //  ae ligature
                "< o\u0308 , O\u0308 " +   // o-umlaut
                "< o\u030b , O\u030b ; \u00f8 , \u00d8 " +   // o-double-acute < o-stroke
                "& V ; w , W" +
                "& Y, u\u0308 , U\u0308" + // u-double-acute
                "; u\u030b, U\u030b "
            }
        };
    }
}
