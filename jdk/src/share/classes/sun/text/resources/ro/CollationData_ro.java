package sun.text.resources.ro;

import java.util.ListResourceBundle;

public class CollationData_ro extends ListResourceBundle {

    protected final Object[][] getContents() {
        return new Object[][] {
            { "Rule",
                "& A < a\u0306 , A\u0306 "       // a < a-breve
                + "& D < \u0111, \u0110 "        // d < d-stroke
                + "& I < i\u0302 , I\u0302 "     // i < i-circumflex
                + "& S < s\u0327 , S\u0327 "     // s < s-cedilla
                + "& \u00de < t\u0327 , T\u0327" // thorn < t-cedilla
                + "& Z < z\u0307 , Z\u0307 "     // tal : ezh-tail < z-dot-above
            }
        };
    }
}
