package sun.util.locale.provider;

import java.text.DateFormatSymbols;
import java.text.spi.DateFormatSymbolsProvider;
import java.util.Locale;
import java.util.Set;

/**
 * Concrete implementation of the  {@link java.text.spi.DateFormatSymbolsProvider
 * DateFormatSymbolsProvider} class for the JRE LocaleProviderAdapter.
 */
public class DateFormatSymbolsProviderImpl extends DateFormatSymbolsProvider implements AvailableLanguageTags {
    private final LocaleProviderAdapter.Type type;
    private final Set<String> langtags;

    public DateFormatSymbolsProviderImpl(LocaleProviderAdapter.Type type, Set<String> langtags) {
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
        return LocaleProviderAdapter.isSupportedLocale(locale, type, langtags);
    }

    /**
     * Returns a new <code>DateFormatSymbols</code> instance for the
     * specified locale.
     *
     * @param locale the desired locale
     * @exception NullPointerException if <code>locale</code> is null
     * @exception IllegalArgumentException if <code>locale</code> isn't
     *     one of the locales returned from
     *     {@link java.util.spi.LocaleServiceProvider#getAvailableLocales()
     *     getAvailableLocales()}.
     * @return a <code>DateFormatSymbols</code> instance.
     * @see java.text.DateFormatSymbols#getInstance(java.util.Locale)
     */
    @Override
    public DateFormatSymbols getInstance(Locale locale) {
        if (locale == null) {
            throw new NullPointerException();
        }

        return new DateFormatSymbols(locale);
    }

    @Override
    public Set<String> getAvailableLanguageTags() {
        return langtags;
    }
}
