package sun.util.locale.provider;

import java.util.Locale;

/**
 * Singletons for the well-known JRE-specific Locales. (th_TH isn't JRE specific,
 * but it's treated as a special Locale because of the Thai Buddhist calendar
 * support.)
 */
public class JRELocaleConstants {
    public static final Locale JA_JP_JP = new Locale("ja", "JP", "JP");
    public static final Locale NO_NO_NY = new Locale("no", "NO", "NY");
    public static final Locale TH_TH    = new Locale("th", "TH");
    public static final Locale TH_TH_TH = new Locale("th", "TH", "TH");

    private JRELocaleConstants() {
    }
}
