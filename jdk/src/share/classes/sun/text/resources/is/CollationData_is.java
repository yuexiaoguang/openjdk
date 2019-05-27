package sun.text.resources.is;

import java.util.ListResourceBundle;

public class CollationData_is extends ListResourceBundle {

    protected final Object[][] getContents() {
        return new Object[][] {
            { "Rule",
                /* for is, accents sorted backwards plus the following: */

                "@"                                           /* sort accents bkwd */
                /* assuming that in the default collation we add:                   */
                /*  thorn, ae ligature, o-diaeresis, and o-slash                    */
                /*  ....in this order...and ditto for the uppercase of these....    */
                /* to be treated as characters (not accented characters) after z    */
                /* then we don't have to add anything here. I've just added it here */
                /* just in case it gets overlooked.                                 */
                + "& A < a\u0301, A\u0301 "       // nt : A < a-acute
                + "& D < \u00f0, \u00d0"          // nt : d < eth
                + "& E < e\u0301, E\u0301 "       // nt : e < e-acute
                + "& I < i\u0301, I\u0301 "       // nt : i < i-acute
                + "& O < o\u0301, O\u0301 "       // nt : o < o-acute
                + "& U < u\u0301, U\u0301 "       // nt : u < u-acute
                + "& Y < y\u0301, Y\u0301 "       // nt : y < y-acute
                + "& Z < \u00fe, \u00de < \u00e6, \u00c6" // nt : z < thron < a-e-ligature
                + "< o\u0308, O\u0308 ; \u00f8, \u00d8" // nt : o-umlaut ; o-stroke
            }
        };
    }
}
