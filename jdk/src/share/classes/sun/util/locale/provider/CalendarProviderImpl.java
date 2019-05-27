package sun.util.locale.provider;

import java.util.Calendar;
import java.util.Calendar.Builder;
import java.util.Locale;
import java.util.Set;
import java.util.TimeZone;
import sun.util.spi.CalendarProvider;

/**
 * Concrete implementation of the  {@link sun.util.spi.CalendarProvider
 * CalendarProvider} class for the JRE LocaleProviderAdapter.
 */
public class CalendarProviderImpl extends CalendarProvider implements AvailableLanguageTags {
    private final LocaleProviderAdapter.Type type;
    private final Set<String> langtags;

    public CalendarProviderImpl(LocaleProviderAdapter.Type type, Set<String> langtags) {
        this.type = type;
        this.langtags = langtags;
    }

    /**
     * Returns an array of all locales for which this locale service provider
     * can provide localized objects or names.
     *
     * @return An array of all locales for which this locale service provider
     * can provide localized objects or names.
     */
    @Override
    public Locale[] getAvailableLocales() {
        return LocaleProviderAdapter.toLocaleArray(langtags);
    }

    @Override
    public boolean isSupportedLocale(Locale locale) {
        // Support any locales.
        return true;
    }

    /**
     * Returns a new <code>Calendar</code> instance for the
     * specified locale.
     *
     * @param zone the time zone
     * @param locale the desired locale
     * @exception NullPointerException if <code>locale</code> is null
     * @exception IllegalArgumentException if <code>locale</code> isn't
     *     one of the locales returned from
     *     {@link java.util.spi.LocaleServiceProvider#getAvailableLocales()
     *     getAvailableLocales()}.
     * @return a <code>Calendar</code> instance.
     * @see java.util.Calendar#getInstance(java.util.Locale)
     */
    @Override
    public Calendar getInstance(TimeZone zone, Locale locale) {
        return new Calendar.Builder()
                     .setLocale(locale)
                     .setTimeZone(zone)
                     .setInstant(System.currentTimeMillis())
                     .build();
    }

    @Override
    public Set<String> getAvailableLanguageTags() {
        return langtags;
    }
}
