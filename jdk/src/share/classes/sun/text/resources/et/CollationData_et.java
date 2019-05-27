package sun.text.resources.et;

import java.util.ListResourceBundle;

public class CollationData_et extends ListResourceBundle {

    protected final Object[][] getContents() {
        return new Object[][] {
            { "Rule",
                "@"                    /* sort accents bkwd */
                + "& S < s\u030c, S\u030c "         // s < s-caron
                + "< z , Z < z\u030c , Z\u030c "    // z sorts between s and t
                + "& V ; w , W < o\u0303 , O\u0303" // v is equiv. to w b4 o-tilde
                + "< a\u0308 , A\u0308 < o\u0308 , O\u0308 "
                + "; w\u0302 , W\u0302"             // w-circumflex
                + "< u\u0308 , U\u0308"
                + "& Y < \u01b6 , \u01b5 "          // y < z-stroke
            }
        };
    }
}
