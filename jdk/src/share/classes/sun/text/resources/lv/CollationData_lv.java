package sun.text.resources.lv;

import java.util.ListResourceBundle;

public class CollationData_lv extends ListResourceBundle {

    protected final Object[][] getContents() {
        return new Object[][] {
            { "Rule",
                /* for lv, accents sorted backwards plus the following: */

                "@" /* sort accents bkwd */
                + "& C < c\u030c , C\u030c "  // C < c-caron
                + "& G < g\u0327 , G\u0327 "  // G < g-cedilla
                + "& I < y, Y"                // tal : i < y
                + "& K < k\u0327 , K\u0327 "  // K < k-cedilla
                + "& L < l\u0327 , L\u0327 "  // L < l-cedilla
                + "& N < n\u0327 , N\u0327 "  // N < n-cedilla
                + "& S < s\u030c , S\u030c "  // S < s-caron
                + "& Z < z\u030c , Z\u030c "  // Z < z-caron
            }
        };
    }
}
