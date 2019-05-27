package sun.util.resources.zh;

import java.util.Locale;
import java.util.ResourceBundle;
import sun.util.locale.provider.LocaleProviderAdapter;
import sun.util.locale.provider.ResourceBundleBasedAdapter;
import sun.util.resources.OpenListResourceBundle;

public final class CurrencyNames_zh_HK extends OpenListResourceBundle {

    // reparent to zh_TW for traditional Chinese names
    public CurrencyNames_zh_HK() {
        ResourceBundle bundle = ((ResourceBundleBasedAdapter)LocaleProviderAdapter.forJRE()).getLocaleData().getCurrencyNames(Locale.TAIWAN);
        setParent(bundle);
    }

    @Override
    protected Object[][] getContents() {
        return new Object[][] {
            {"HKD", "HK$"},
            {"TWD", "TWD"},
        };
    }
}
