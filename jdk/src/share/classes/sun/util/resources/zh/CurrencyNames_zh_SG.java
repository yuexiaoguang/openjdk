package sun.util.resources.zh;

import java.util.Locale;
import java.util.ResourceBundle;
import sun.util.locale.provider.LocaleProviderAdapter;
import sun.util.locale.provider.ResourceBundleBasedAdapter;
import sun.util.resources.OpenListResourceBundle;

public final class CurrencyNames_zh_SG extends OpenListResourceBundle {

    // reparent to zh_CN for simplified Chinese names
    public CurrencyNames_zh_SG() {
        ResourceBundle bundle = ((ResourceBundleBasedAdapter)LocaleProviderAdapter.forJRE()).getLocaleData().getCurrencyNames(Locale.CHINA);
        setParent(bundle);
    }

    @Override
    protected Object[][] getContents() {
        return new Object[][] {
            {"CNY", "CNY"},
            {"SGD", "S$"},
        };
    }
}
